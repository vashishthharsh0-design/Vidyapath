package com.example.data

import android.util.Log
import com.example.BuildConfig
import com.example.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object GeminiChatRepository {
    private const val TAG = "GeminiChatRepo"
    private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(45, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val JSON_MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()

    suspend fun generateAiResponse(
        conversationHistory: List<ChatMessage>,
        userMessage: String,
        persona: AiTutorPersona,
        isSearchGroundingEnabled: Boolean,
        grade: ClassGrade,
        board: BoardType,
        chapterContext: String? = null
    ): Result<ChatMessage> = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY.trim()

        // If API key is empty or default placeholder, immediately provide offline syllabus intelligence
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            val fallbackText = generateEducationalFallback(
                userMessage = userMessage,
                persona = persona,
                grade = grade,
                board = board,
                chapterContext = chapterContext
            )
            return@withContext Result.success(
                ChatMessage(
                    sender = ChatSender.AI_TUTOR,
                    text = fallbackText,
                    modelUsed = "Offline NCERT Engine (Lite)",
                    personaName = persona.title,
                    isSearchGrounded = false
                )
            )
        }

        // Construct System Instruction with dynamic Grade & Board context
        val systemInstructionText = buildString {
            append(persona.systemPromptTemplate)
            append("\n\nCurrent Student Context:")
            append("\n- Academic Grade: ${grade.displayName}")
            append("\n- Education Board: ${board.displayName} (${board.shortName})")
            if (!chapterContext.isNullOrBlank()) {
                append("\n- Current Chapter Under Study: $chapterContext")
            }
            append("\n- Instructions: Always emphasize keywords that fetch marks in board exam marking schemes, provide examples from standard Indian syllabus/NCERT, and format using clean Markdown headings and bullet points.")
        }

        // Tier 1: Try with user persona model and requested search settings
        val primaryModel = when (persona) {
            AiTutorPersona.FAST_REVISION -> "gemini-3.1-flash-lite-preview"
            else -> "gemini-3.5-flash"
        }

        val firstAttempt = executeGeminiCall(
            apiKey = apiKey,
            modelId = primaryModel,
            systemInstructionText = systemInstructionText,
            conversationHistory = conversationHistory,
            userMessage = userMessage,
            chapterContext = chapterContext,
            enableSearchGrounding = isSearchGroundingEnabled,
            temperature = if (persona == AiTutorPersona.STEM_PRO_SOLVER) 0.2 else 0.7
        )

        if (firstAttempt.isSuccess) {
            val (replyText, searchQueries, groundingSources, wasSearchGrounded) = firstAttempt.getOrThrow()
            return@withContext Result.success(
                ChatMessage(
                    sender = ChatSender.AI_TUTOR,
                    text = replyText,
                    modelUsed = primaryModel,
                    personaName = persona.title,
                    isSearchGrounded = wasSearchGrounded,
                    searchQueries = searchQueries,
                    groundingSources = groundingSources.distinctBy { it.url }
                )
            )
        }

        // If search grounding was requested and failed (often due to free tier quota), retry without search
        if (isSearchGroundingEnabled) {
            Log.w(TAG, "Search grounded call failed, falling back to direct model without tools")
            val noSearchAttempt = executeGeminiCall(
                apiKey = apiKey,
                modelId = primaryModel,
                systemInstructionText = systemInstructionText,
                conversationHistory = conversationHistory,
                userMessage = userMessage,
                chapterContext = chapterContext,
                enableSearchGrounding = false,
                temperature = 0.5
            )

            if (noSearchAttempt.isSuccess) {
                val (replyText, _, _, _) = noSearchAttempt.getOrThrow()
                return@withContext Result.success(
                    ChatMessage(
                        sender = ChatSender.AI_TUTOR,
                        text = replyText,
                        modelUsed = "$primaryModel (Standard)",
                        personaName = persona.title,
                        isSearchGrounded = false
                    )
                )
            }
        }

        // Tier 2: Backup model attempt with gemini-3.1-flash-lite-preview if primary was gemini-3.5-flash
        if (primaryModel != "gemini-3.1-flash-lite-preview") {
            Log.w(TAG, "Primary model call failed, trying fast lite fallback")
            val backupAttempt = executeGeminiCall(
                apiKey = apiKey,
                modelId = "gemini-3.1-flash-lite-preview",
                systemInstructionText = systemInstructionText,
                conversationHistory = conversationHistory,
                userMessage = userMessage,
                chapterContext = chapterContext,
                enableSearchGrounding = false,
                temperature = 0.5
            )

            if (backupAttempt.isSuccess) {
                val (replyText, _, _, _) = backupAttempt.getOrThrow()
                return@withContext Result.success(
                    ChatMessage(
                        sender = ChatSender.AI_TUTOR,
                        text = replyText,
                        modelUsed = "gemini-3.1-flash-lite-preview",
                        personaName = persona.title,
                        isSearchGrounded = false
                    )
                )
            }
        }

        // Tier 3: Offline Curriculum Knowledge Base Fallback
        // Guaranteed response so the user never receives a dead-end error
        Log.w(TAG, "Online Gemini endpoints unreachable or quota exhausted. Serving offline curriculum fallback.")
        val fallbackText = generateEducationalFallback(
            userMessage = userMessage,
            persona = persona,
            grade = grade,
            board = board,
            chapterContext = chapterContext
        )

        Result.success(
            ChatMessage(
                sender = ChatSender.AI_TUTOR,
                text = fallbackText,
                modelUsed = "Vidya Offline Knowledge Engine ⚡",
                personaName = persona.title,
                isSearchGrounded = false
            )
        )
    }

    private data class CallResult(
        val replyText: String,
        val searchQueries: List<String>,
        val groundingSources: List<GroundingSource>,
        val wasSearchUsed: Boolean
    )

    private fun executeGeminiCall(
        apiKey: String,
        modelId: String,
        systemInstructionText: String,
        conversationHistory: List<ChatMessage>,
        userMessage: String,
        chapterContext: String?,
        enableSearchGrounding: Boolean,
        temperature: Double
    ): Result<CallResult> {
        return try {
            val url = "$BASE_URL$modelId:generateContent?key=$apiKey"
            val requestJson = JSONObject()

            // System Instruction
            val systemInstructionObj = JSONObject()
            val systemPartsArray = JSONArray()
            systemPartsArray.put(JSONObject().put("text", systemInstructionText))
            systemInstructionObj.put("parts", systemPartsArray)
            requestJson.put("systemInstruction", systemInstructionObj)

            // Contents array
            val contentsArray = JSONArray()
            val recentHistory = conversationHistory
                .filter { !it.isLoading && !it.isError && it.text.isNotBlank() }
                .takeLast(8)

            for (msg in recentHistory) {
                val turnObj = JSONObject()
                turnObj.put("role", if (msg.sender == ChatSender.USER) "user" else "model")
                val partsArray = JSONArray()
                partsArray.put(JSONObject().put("text", msg.text))
                turnObj.put("parts", partsArray)
                contentsArray.put(turnObj)
            }

            // Current message
            val currentTurn = JSONObject()
            currentTurn.put("role", "user")
            val currentParts = JSONArray()
            val promptWithContext = if (!chapterContext.isNullOrBlank() && conversationHistory.isEmpty()) {
                "Regarding Chapter '$chapterContext': $userMessage"
            } else {
                userMessage
            }
            currentParts.put(JSONObject().put("text", promptWithContext))
            currentTurn.put("parts", currentParts)
            contentsArray.put(currentTurn)
            requestJson.put("contents", contentsArray)

            // Google Search tool if requested
            if (enableSearchGrounding) {
                val toolsArray = JSONArray()
                toolsArray.put(JSONObject().put("googleSearch", JSONObject()))
                requestJson.put("tools", toolsArray)
            }

            // Generation config
            val genConfig = JSONObject()
            genConfig.put("temperature", temperature)
            requestJson.put("generationConfig", genConfig)

            val body = requestJson.toString().toRequestBody(JSON_MEDIA_TYPE)
            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            val response = client.newCall(request).execute()
            val responseBodyString = response.body?.string()

            if (!response.isSuccessful || responseBodyString.isNullOrBlank()) {
                Log.w(TAG, "Gemini $modelId call failed HTTP ${response.code}: $responseBodyString")
                return Result.failure(Exception("HTTP ${response.code}"))
            }

            val responseJson = JSONObject(responseBodyString)
            val candidates = responseJson.optJSONArray("candidates")
            if (candidates == null || candidates.length() == 0) {
                return Result.failure(Exception("Empty candidates"))
            }

            val candidate = candidates.getJSONObject(0)
            val contentObj = candidate.optJSONObject("content")
            val parts = contentObj?.optJSONArray("parts")

            val replyText = buildString {
                if (parts != null) {
                    for (i in 0 until parts.length()) {
                        val part = parts.getJSONObject(i)
                        val text = part.optString("text")
                        if (text.isNotBlank()) {
                            append(text)
                        }
                    }
                }
            }.trim().ifBlank { "No text returned." }

            // Grounding metadata
            val searchQueries = mutableListOf<String>()
            val groundingSources = mutableListOf<GroundingSource>()
            var wasSearchUsed = enableSearchGrounding

            val groundingMetadata = candidate.optJSONObject("groundingMetadata")
            if (groundingMetadata != null) {
                wasSearchUsed = true
                val webQueries = groundingMetadata.optJSONArray("webSearchQueries")
                if (webQueries != null) {
                    for (i in 0 until webQueries.length()) {
                        searchQueries.add(webQueries.getString(i))
                    }
                }

                val chunks = groundingMetadata.optJSONArray("groundingChunks")
                if (chunks != null) {
                    for (i in 0 until chunks.length()) {
                        val chunk = chunks.getJSONObject(i)
                        val web = chunk.optJSONObject("web")
                        if (web != null) {
                            val title = web.optString("title").ifBlank { "NCERT/CBSE Reference" }
                            val sourceUrl = web.optString("uri")
                            if (sourceUrl.isNotBlank()) {
                                groundingSources.add(GroundingSource(title, sourceUrl))
                            }
                        }
                    }
                }
            }

            Result.success(CallResult(replyText, searchQueries, groundingSources, wasSearchUsed))
        } catch (e: Exception) {
            Log.w(TAG, "Exception during Gemini call to $modelId", e)
            Result.failure(e)
        }
    }

    private fun generateEducationalFallback(
        userMessage: String,
        persona: AiTutorPersona,
        grade: ClassGrade,
        board: BoardType,
        chapterContext: String?
    ): String {
        val lower = userMessage.lowercase()
        val contextTag = if (!chapterContext.isNullOrBlank()) " for **$chapterContext**" else ""

        return when {
            // Marking scheme & exam tactics
            lower.contains("marking scheme") || lower.contains("marking") || lower.contains("score") || lower.contains("rubric") -> {
                """
                ### 📋 CBSE Official Step-Marking Scheme Guide (${grade.displayName})
                
                Examiners award marks in strict **step-wise increments** as per standard Board evaluation keys$contextTag:
                
                1. **Principle / Formula / Law (1 Mark)**:
                   - Explicitly write the fundamental law, theorem, or standard formula.
                   - State standard SI units and variable definitions clearly.
                   
                2. **Working Steps & Value Substitution (2 to 3 Marks)**:
                   - **In Science & Mathematics**: Always show substitution with algebraic signs (e.g. Cartesian sign convention in optics or negative work in physics).
                   - **In Commerce (Accounts & BST)**: Journal entry format (`Date | Particulars | L.F. | Debit | Credit`) carries marks. Working notes are mandatory for full credit!
                   
                3. **Diagram / Schematic Representation (1 Mark)**:
                   - Draw neat, pencil-labeled diagrams with directional arrows.
                   
                4. **Final Conclusion & Units (0.5 to 1 Mark)**:
                   - Box your final calculated numerical answer (e.g., `Ans: +15 cm` or `Goodwill = ₹1,20,000`).
                   
                💡 **Topper Hack**: For 5-mark subjective questions, structure your answer into: **(a) Definition/Principle**, **(b) Essential Key Points (Numbered)**, **(c) Practical Application/Example**.
                """.trimIndent()
            }

            // Accounts & Commerce
            lower.contains("partnership") || lower.contains("goodwill") || lower.contains("share capital") || lower.contains("debit") || lower.contains("credit") || lower.contains("journal") || lower.contains("balance sheet") -> {
                """
                ### 📊 Accountancy & Commerce Step-by-Step Master Guide (${grade.displayName})
                
                **Key NCERT Rules for Partnership & Share Capital ($board):**
                
                1. **Profit & Loss Appropriation Account Format:**
                   - **Debit side**: Interest on Capital (if provided in deed), Partner Salaries/Commissions, Transfer to General Reserve.
                   - **Credit side**: Net Profit transferred from P&L Account, Interest on Drawings.
                   
                2. **Goodwill Valuation Recap:**
                   - **Average Profit Method**: (Adjusted Normal Profit) × (Number of Years' Purchase)
                   - **Super Profit Method**: (Actual Normal Profit - Normal Profit) × Years' Purchase
                     *(Normal Profit = Capital Employed × Normal Rate of Return / 100)*
                   - **Capitalisation Method**: (Super Profit / NRR) × 100
                   
                3. **Pro-Rata Allotment Standard Journal Flow:**
                   - `Bank A/c Dr.` (Application money received on applied shares)
                   - `To Share Application A/c`
                   - `Share Application A/c Dr.`
                   - `To Share Capital A/c` (Allotted shares × Application face value)
                   - `To Share Allotment A/c` (Excess application adjusted to allotment)
                   - `To Bank A/c` (Direct refund for rejected applications)
                   
                📌 *Tip: Tap "Save to Notes" below to turn this solution directly into an active recall Cornell revision card!*
                """.trimIndent()
            }

            // Physics / Optics / Electricity
            lower.contains("optics") || lower.contains("light") || lower.contains("ray") || lower.contains("mirror") || lower.contains("lens") || lower.contains("ohm") || lower.contains("electricity") || lower.contains("magnetic") -> {
                """
                ### 🔬 Physics High-Yield Core Revision (${grade.displayName})
                
                **1. Fundamental Formulae & Laws:**
                - **Mirror Formula**: `1/f = 1/v + 1/u` (Magnification: `m = -v/u = h_i / h_o`)
                - **Lens Formula**: `1/f = 1/v - 1/u` (Magnification: `m = +v/u = h_i / h_o`)
                - **Power of a Lens**: `P = 1/f (in meters)` (SI unit: Dioptre, D)
                - **Ohm's Law**: `V = I · R` (Potential difference is directly proportional to current at constant temperature)
                - **Joule's Law of Heating**: `H = I² · R · t`
                
                **2. Cartesian Sign Conventions (Crucial for Numericals):**
                - Object distance `u` is **always negative** (`u < 0`).
                - Concave mirror / Concave lens: Focal length `f` is **always negative**.
                - Convex mirror / Convex lens: Focal length `f` is **always positive**.
                - Real and inverted image: `m < 0`; Virtual and erect image: `m > 0`.
                
                **3. Common Board Exam Trap:**
                Never omit arrows showing ray paths or circuit current directions (+ to -). Evaluators deduct 0.5 marks per diagram without directional arrows.
                """.trimIndent()
            }

            // Mathematics (Calculus, Quadratic, Trigonometry)
            lower.contains("quadratic") || lower.contains("trigonometry") || lower.contains("derivative") || lower.contains("integral") || lower.contains("matrix") || lower.contains("calculus") -> {
                """
                ### 📐 Mathematics Essential Formulae & Methods (${grade.displayName})
                
                **1. Core Mathematical Theorems:**
                - **Quadratic Formula**: For `ax² + bx + c = 0`, roots are `x = (-b ± √(b² - 4ac)) / (2a)`.
                  - Discriminant `D = b² - 4ac`
                  - `D > 0`: Two distinct real roots
                  - `D = 0`: Two equal real roots
                  - `D < 0`: No real roots (complex conjugate roots in Class 11)
                - **Trigonometric Identites**:
                  - `sin²θ + cos²θ = 1`
                  - `1 + tan²θ = sec²θ`
                  - `1 + cot²θ = cosec²θ`
                - **Integration by Parts**: `∫ u·v dx = u·∫v dx - ∫ (u' · ∫v dx) dx` (Follow the **ILATE** priority rule: Inverse, Logarithmic, Algebraic, Trigonometric, Exponential).
                
                **2. Presentation Tips for Board Exams:**
                - Write the standard theorem or identity used on the right-hand margin in brackets: `[∵ sin²θ + cos²θ = 1]`.
                - Highlight the final answer clearly with step index.
                """.trimIndent()
            }

            // Chemistry / Reactions / Acids & Bases
            lower.contains("acid") || lower.contains("base") || lower.contains("reaction") || lower.contains("chemical") || lower.contains("organic") || lower.contains("solution") || lower.contains("periodic") -> {
                """
                ### ⚗️ Chemistry NCERT High-Yield Summary (${grade.displayName})
                
                **1. Key Reactions & Classifications:**
                - **Combination Reaction**: `CaO (s) + H₂O (l) → Ca(OH)₂ (aq) + Heat` (Slaking of lime - highly exothermic)
                - **Decomposition Reaction**: `2FeSO₄ (s) --Δ--> Fe₂O₃ (s) + SO₂ (g) + SO₃ (g)` (Notice pungent smelling sulfur gases)
                - **Neutralization**: `Acid + Base → Salt + Water` (e.g. `HCl + NaOH → NaCl + H₂O`)
                - **Saponification (Organic)**: `Ester + NaOH → Alcohol + Sodium carboxylate (Soap)`
                
                **2. Important Observations Frequently Asked:**
                - **Rusting of Iron**: Hydrated ferric oxide `Fe₂O₃ · xH₂O`.
                - **Litmus Test**: Blue litmus turns red in acid; Red litmus turns blue in base.
                - **pH Scale**: pH < 7 (Acidic), pH = 7 (Neutral), pH > 7 (Basic).
                
                **3. Board Exam Tip:** Always state the physical states of reactants and products `(s)`, `(l)`, `(g)`, `(aq)` and balance chemical equations completely!
                """.trimIndent()
            }

            // Biology / Life Processes / Photosynthesis
            lower.contains("photosynthesis") || lower.contains("respiration") || lower.contains("cell") || lower.contains("heredity") || lower.contains("reproduction") || lower.contains("biology") -> {
                """
                ### 🧬 Biology NCERT Core Concepts (${grade.displayName})
                
                **1. Photosynthesis Overall Reaction:**
                `6CO₂ + 12H₂O --(Sunlight / Chlorophyll)--> C₆H₁₂O₆ + 6O₂ + 6H₂O`
                - **Three Major Events in Photosynthesis**:
                  1. Absorption of light energy by chlorophyll.
                  2. Conversion of light energy into chemical energy and splitting of water molecules into hydrogen and oxygen.
                  3. Reduction of carbon dioxide into carbohydrates.
                  
                **2. Aerobic vs Anaerobic Respiration Breakdown:**
                - **Aerobic**: Takes place in mitochondria, requires O₂, breaks pyruvate into `CO₂ + H₂O + 38 ATP` (high energy yield).
                - **Anaerobic (Yeast)**: Alcoholic fermentation producing `Ethanol + CO₂ + 2 ATP`.
                - **Anaerobic (Muscle Cramps)**: Lack of O₂ in muscle cells leads to `Lactic Acid + 2 ATP`.
                
                **3. Exam Diagram Reminder**: Practice labeling the **Human Alimentary Canal**, **Nephron**, and **Cross-section of a Leaf**. CBSE awards 2 out of 5 marks strictly for clear labeling.
                """.trimIndent()
            }

            else -> {
                """
                ### 🎓 Vidya AI Tutor • Comprehensive Master Breakdown
                
                Here is the structured NCERT curriculum breakdown for **${userMessage.trim()}**$contextTag:
                
                1. **Core Concept & Definition:**
                   - This key topic from **${grade.displayName}** is regularly featured across both Short Answer (2/3 marks) and Long Answer (5 marks) sections of ${board.displayName} examinations.
                   - Always state standard textbook definitions using precise scientific and academic terminology.
                   
                2. **Step-by-Step NCERT Framework:**
                   - Break down complex mechanisms into sequenced, numbered steps.
                   - In mathematical derivations, show all initial assumptions and boundary conditions explicitly.
                   
                3. **CBSE Board Exam High-Yield Tips:**
                   - **Word Limit Discipline**: Stick closely to official limits (30-50 words for 2 marks, 50-80 words for 3 marks, 100-120 words for 5 marks).
                   - **Keywords First**: Evaluators use scoring keys with specific underlined keywords. Underline or bold these keywords in your examination script.
                   - **Diagram Inclusion**: Whenever appropriate, complement theoretical answers with neat pencil diagrams or flowcharts.
                """.trimIndent()
            }
        }
    }

    /**
     * Sends student notes to the Gemini API to generate a summarized 'crux' and key highlights.
     * Features multi-tier failover (gemini-3.5-flash -> gemini-3.1-flash-lite-preview -> offline intelligent curriculum engine).
     */
    suspend fun summarizeStudentNotes(
        title: String,
        subject: String,
        chapter: String,
        mainContent: String,
        cues: String = "",
        grade: String = "Class 10",
        board: String = "CBSE"
    ): Result<NoteCruxSummary> = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY.trim()

        // If offline or placeholder API key, use curriculum-aware local extraction
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            Log.i(TAG, "Using offline crux generation engine for note: $title")
            return@withContext Result.success(
                generateOfflineNoteCrux(title, subject, chapter, mainContent, cues, grade, board)
            )
        }

        val systemInstruction = """
You are an expert Indian curriculum academic mentor and summarizer (NCERT, CBSE, ICSE, State Boards).
Your objective is to read a student's study notes and generate an exceptionally sharp, high-retention 'Crux' & Revision Highlights:
1. "executiveCrux": A crisp 2-3 sentence executive crux capturing the fundamental law, mechanism, concept, or mathematical/accounting thesis.
2. "keyHighlights": An array of 3-5 concise, high-yield bullet points featuring essential keywords, core definitions, reaction equations, or examiner trap alerts.
3. "examCues": An array of 2-3 sharp active-recall questions suitable for Cornell note cue margins.
4. "highYieldFormulasOrTerms": An array of key formulas, SI units, reaction equations, or essential academic terminology extracted from the notes.

You MUST respond strictly with a valid JSON object matching this schema:
{
  "executiveCrux": "2-3 sentence core concept summary",
  "keyHighlights": ["Highlight 1", "Highlight 2", "Highlight 3"],
  "examCues": ["Question 1?", "Question 2?"],
  "highYieldFormulasOrTerms": ["Formula / Term 1", "Formula / Term 2"]
}
""".trimIndent()

        val prompt = buildString {
            appendLine("STUDENT NOTE METADATA:")
            appendLine("• Topic / Title: ${title.ifBlank { "Untitled Note" }}")
            appendLine("• Subject: ${subject.ifBlank { "General" }}")
            if (chapter.isNotBlank()) appendLine("• Chapter: $chapter")
            appendLine("• Curriculum: $board ($grade)")
            if (cues.isNotBlank()) {
                appendLine()
                appendLine("EXISTING CUES / KEYWORDS:")
                appendLine(cues)
            }
            appendLine()
            appendLine("STUDENT NOTE CONTENT:")
            appendLine(mainContent.ifBlank { title })
        }

        // Tier 1: Primary call with gemini-3.5-flash (Standard Task Model)
        val primaryResult = executeSummarizeApiCall(apiKey, "gemini-3.5-flash", systemInstruction, prompt, title)
        if (primaryResult.isSuccess) {
            return@withContext primaryResult
        }

        // Tier 2: Backup fast call with gemini-3.1-flash-lite-preview
        Log.w(TAG, "Primary crux summarization failed, trying gemini-3.1-flash-lite-preview")
        val backupResult = executeSummarizeApiCall(apiKey, "gemini-3.1-flash-lite-preview", systemInstruction, prompt, title)
        if (backupResult.isSuccess) {
            return@withContext backupResult
        }

        // Tier 3: Resilient offline curriculum knowledge fallback
        Log.w(TAG, "Online endpoints failed for summarization. Serving offline crux extraction.")
        Result.success(
            generateOfflineNoteCrux(title, subject, chapter, mainContent, cues, grade, board)
        )
    }

    /**
     * Overload to summarize a NoteEntity directly
     */
    suspend fun summarizeStudentNote(note: NoteEntity): Result<NoteCruxSummary> = summarizeStudentNotes(
        title = note.title,
        subject = note.subject,
        chapter = note.chapter,
        mainContent = note.mainContent,
        cues = note.cueOrKeywordColumn,
        grade = note.grade,
        board = note.board
    )

    private fun executeSummarizeApiCall(
        apiKey: String,
        modelId: String,
        systemInstruction: String,
        prompt: String,
        noteTitle: String
    ): Result<NoteCruxSummary> {
        return try {
            val url = "$BASE_URL$modelId:generateContent?key=$apiKey"
            val requestJson = JSONObject()

            // System Instruction
            val systemInstructionObj = JSONObject()
            val systemPartsArray = JSONArray().put(JSONObject().put("text", systemInstruction))
            systemInstructionObj.put("parts", systemPartsArray)
            requestJson.put("systemInstruction", systemInstructionObj)

            // Contents
            val contentsArray = JSONArray()
            val currentTurn = JSONObject()
            currentTurn.put("role", "user")
            val currentParts = JSONArray().put(JSONObject().put("text", prompt))
            currentTurn.put("parts", currentParts)
            contentsArray.put(currentTurn)
            requestJson.put("contents", contentsArray)

            // Generation Config with application/json
            val genConfig = JSONObject()
            genConfig.put("temperature", 0.3)
            genConfig.put("responseMimeType", "application/json")
            requestJson.put("generationConfig", genConfig)

            val body = requestJson.toString().toRequestBody(JSON_MEDIA_TYPE)
            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            val response = client.newCall(request).execute()
            val responseBodyString = response.body?.string()

            if (!response.isSuccessful || responseBodyString.isNullOrBlank()) {
                Log.w(TAG, "Gemini $modelId note summarization failed HTTP ${response.code}: $responseBodyString")
                return Result.failure(Exception("HTTP ${response.code}"))
            }

            val rootJson = JSONObject(responseBodyString)
            val candidates = rootJson.optJSONArray("candidates") ?: return Result.failure(Exception("No candidates"))
            if (candidates.length() == 0) return Result.failure(Exception("Empty candidates"))
            val firstCandidate = candidates.getJSONObject(0)
            val content = firstCandidate.optJSONObject("content") ?: return Result.failure(Exception("No content"))
            val parts = content.optJSONArray("parts") ?: return Result.failure(Exception("No parts"))
            if (parts.length() == 0) return Result.failure(Exception("Empty parts"))

            val text = parts.getJSONObject(0).optString("text", "")
            val summary = parseCruxJsonResponse(text, noteTitle, modelId)
            Result.success(summary)
        } catch (e: Exception) {
            Log.w(TAG, "Error executing note summarization on $modelId: ${e.message}")
            Result.failure(e)
        }
    }

    private fun parseCruxJsonResponse(rawText: String, noteTitle: String, modelId: String): NoteCruxSummary {
        val cleanJson = rawText.trim()
            .removePrefix("```json")
            .removePrefix("```JSON")
            .removePrefix("```")
            .removeSuffix("```")
            .trim()

        return try {
            val obj = JSONObject(cleanJson)
            val crux = obj.optString("executiveCrux", "").ifBlank {
                obj.optString("crux", "")
            }
            val highlights = mutableListOf<String>()
            val hlArray = obj.optJSONArray("keyHighlights") ?: obj.optJSONArray("highlights")
            if (hlArray != null) {
                for (i in 0 until hlArray.length()) {
                    val item = hlArray.optString(i, "").trim()
                    if (item.isNotBlank()) highlights.add(item)
                }
            }

            val cues = mutableListOf<String>()
            val cuesArray = obj.optJSONArray("examCues") ?: obj.optJSONArray("cues")
            if (cuesArray != null) {
                for (i in 0 until cuesArray.length()) {
                    val item = cuesArray.optString(i, "").trim()
                    if (item.isNotBlank()) cues.add(item)
                }
            }

            val formulas = mutableListOf<String>()
            val formArray = obj.optJSONArray("highYieldFormulasOrTerms") ?: obj.optJSONArray("formulas")
            if (formArray != null) {
                for (i in 0 until formArray.length()) {
                    val item = formArray.optString(i, "").trim()
                    if (item.isNotBlank()) formulas.add(item)
                }
            }

            NoteCruxSummary(
                title = noteTitle,
                executiveCrux = crux.ifBlank { "Core review crux extracted from $noteTitle" },
                keyHighlights = if (highlights.isNotEmpty()) highlights else listOf("Primary concept summarized from study notes"),
                examCues = cues,
                highYieldFormulasOrTerms = formulas,
                modelUsed = modelId,
                isOfflineGenerated = false
            )
        } catch (e: Exception) {
            parseCruxMarkdownFallback(cleanJson, noteTitle, modelId)
        }
    }

    private fun parseCruxMarkdownFallback(rawText: String, noteTitle: String, modelId: String): NoteCruxSummary {
        val lines = rawText.lines().map { it.trim() }.filter { it.isNotEmpty() }
        val highlights = mutableListOf<String>()
        val cues = mutableListOf<String>()
        val formulas = mutableListOf<String>()
        var crux = ""

        for (line in lines) {
            val lower = line.lowercase()
            when {
                lower.startsWith("•") || lower.startsWith("-") || lower.startsWith("*") -> {
                    highlights.add(line.removePrefix("•").removePrefix("-").removePrefix("*").trim())
                }
                lower.startsWith("?") || lower.contains("?") -> {
                    cues.add(line)
                }
                lower.contains("=") || lower.contains("→") || lower.contains("formula") -> {
                    formulas.add(line)
                }
                crux.isBlank() && !line.startsWith("#") -> {
                    crux = line
                }
            }
        }

        return NoteCruxSummary(
            title = noteTitle,
            executiveCrux = crux.ifBlank { rawText.take(220) },
            keyHighlights = if (highlights.isNotEmpty()) highlights.take(5) else listOf("Key highlight extracted from student notes"),
            examCues = cues.take(3),
            highYieldFormulasOrTerms = formulas.take(4),
            modelUsed = "$modelId (Text Parsed)",
            isOfflineGenerated = false
        )
    }

    fun generateOfflineNoteCrux(
        title: String,
        subject: String,
        chapter: String,
        mainContent: String,
        cues: String,
        grade: String,
        board: String
    ): NoteCruxSummary {
        val cleanContent = mainContent.ifBlank { title }
        val lines = cleanContent.lines().map { it.trim() }.filter { it.isNotEmpty() }

        // Extract high yield lines: bullet points, equations, or definitions
        val highlights = mutableListOf<String>()
        val formulas = mutableListOf<String>()
        val extractedCues = mutableListOf<String>()

        for (line in lines) {
            val lower = line.lowercase()
            if (line.startsWith("•") || line.startsWith("-") || line.startsWith("1.") || line.startsWith("2.") || line.startsWith("3.")) {
                val clean = line.replace(Regex("^[•\\-*\\d.]+\\s*"), "")
                if (clean.length in 12..200 && highlights.size < 5) {
                    highlights.add(clean)
                }
            }
            if (line.contains("=") || line.contains("→") || line.contains("->") || line.contains("ATP") || line.contains("CO2") || line.contains("O2")) {
                if (formulas.size < 4) {
                    formulas.add(line.replace(Regex("^[•\\-*\\d.]+\\s*"), ""))
                }
            }
        }

        // If not enough bullet points found, extract sentences
        if (highlights.isEmpty()) {
            val sentences = cleanContent.split(Regex("[.!?\n]"))
                .map { it.trim() }
                .filter { it.length > 20 }
            highlights.addAll(sentences.take(3))
        }

        if (highlights.isEmpty()) {
            highlights.add("Essential study point: Focus on NCERT textbook definitions and diagrams for $title.")
            highlights.add("Board Exam Tip: Remember step-by-step presentation to maximize evaluation marks.")
        }

        // Generate Cornell active recall cues
        if (cues.isNotBlank()) {
            cues.lines().map { it.trim() }.filter { it.isNotBlank() }.take(3).forEach { extractedCues.add(it) }
        } else {
            extractedCues.add("What is the core definition and physical/chemical mechanism of $title?")
            extractedCues.add("Which common board exam traps or numerical formulas are associated with this topic?")
        }

        val executiveCrux = if (lines.isNotEmpty() && lines.first().length > 20 && !lines.first().startsWith("#")) {
            lines.first()
        } else {
            "Fundamental $board ($grade) conceptual crux for $title in $subject: Master the core NCERT principles, key terminology, and step marking schemes."
        }

        return NoteCruxSummary(
            title = title.ifBlank { "Study Note" },
            executiveCrux = executiveCrux,
            keyHighlights = highlights,
            examCues = extractedCues,
            highYieldFormulasOrTerms = formulas,
            modelUsed = "Offline NCERT Knowledge Engine ⚡",
            isOfflineGenerated = true
        )
    }
}
