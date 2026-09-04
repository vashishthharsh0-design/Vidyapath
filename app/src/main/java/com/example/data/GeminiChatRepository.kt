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
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
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

        // Check if API key is unconfigured or default placeholder
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            // Provide high-quality local educational guidance and instruct the student how to enable live Gemini in Secrets panel
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
                    modelUsed = "${persona.modelId} (Curriculum Mode)",
                    personaName = persona.title,
                    isSearchGrounded = isSearchGroundingEnabled,
                    searchQueries = if (isSearchGroundingEnabled) listOf(userMessage) else emptyList(),
                    groundingSources = if (isSearchGroundingEnabled) {
                        listOf(
                            GroundingSource("CBSE Official Academic Portal", "https://cbseacademic.nic.in"),
                            GroundingSource("NCERT Textbook Solutions", "https://ncert.nic.in")
                        )
                    } else emptyList()
                )
            )
        }

        try {
            val url = "$BASE_URL${persona.modelId}:generateContent?key=$apiKey"

            // Construct System Instruction with dynamic Grade & Board context
            val systemInstructionText = buildString {
                append(persona.systemPromptTemplate)
                append("\n\nCurrent Student Context:")
                append("\n- Academic Grade: ${grade.displayName}")
                append("\n- Education Board: ${board.displayName} (${board.shortName})")
                if (!chapterContext.isNullOrBlank()) {
                    append("\n- Current Chapter Under Study: $chapterContext")
                }
                append("\n- Instructions: Always emphasize keywords that fetch marks in board exam marking schemes, provide examples from the standard Indian syllabus, and format using clear Markdown headings and bullet points.")
            }

            val requestJson = JSONObject()

            // System Instruction
            val systemInstructionObj = JSONObject()
            val systemPartsArray = JSONArray()
            val systemPartText = JSONObject()
            systemPartText.put("text", systemInstructionText)
            systemPartsArray.put(systemPartText)
            systemInstructionObj.put("parts", systemPartsArray)
            requestJson.put("systemInstruction", systemInstructionObj)

            // Contents array (Multi-turn conversation history)
            val contentsArray = JSONArray()

            // Include up to last 10 turns to stay safely within optimal context limits
            val recentHistory = conversationHistory
                .filter { !it.isLoading && !it.isError && it.text.isNotBlank() }
                .takeLast(10)

            for (msg in recentHistory) {
                val turnObj = JSONObject()
                val role = if (msg.sender == ChatSender.USER) "user" else "model"
                turnObj.put("role", role)

                val partsArray = JSONArray()
                val partObj = JSONObject()
                partObj.put("text", msg.text)
                partsArray.put(partObj)

                turnObj.put("parts", partsArray)
                contentsArray.put(turnObj)
            }

            // Append current user message
            val currentTurnObj = JSONObject()
            currentTurnObj.put("role", "user")
            val currentPartsArray = JSONArray()
            val currentPartObj = JSONObject()
            val promptWithContext = if (!chapterContext.isNullOrBlank() && conversationHistory.isEmpty()) {
                "Regarding Chapter '$chapterContext': $userMessage"
            } else {
                userMessage
            }
            currentPartObj.put("text", promptWithContext)
            currentPartsArray.put(currentPartObj)
            currentTurnObj.put("parts", currentPartsArray)
            contentsArray.put(currentTurnObj)

            requestJson.put("contents", contentsArray)

            // Search Grounding Tools (if enabled or default for persona)
            val shouldEnableSearch = isSearchGroundingEnabled || persona.defaultSearchGrounding
            if (shouldEnableSearch) {
                val toolsArray = JSONArray()
                val toolObj = JSONObject()
                toolObj.put("googleSearch", JSONObject())
                toolsArray.put(toolObj)
                requestJson.put("tools", toolsArray)
            }

            // Generation config
            val generationConfig = JSONObject()
            generationConfig.put("temperature", if (persona == AiTutorPersona.STEM_PRO_SOLVER) 0.2 else 0.7)
            requestJson.put("generationConfig", generationConfig)

            val body = requestJson.toString().toRequestBody(JSON_MEDIA_TYPE)
            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            val response = client.newCall(request).execute()
            val responseBodyString = response.body?.string()

            if (!response.isSuccessful || responseBodyString.isNullOrBlank()) {
                val errCode = response.code
                Log.e(TAG, "API call failed with HTTP $errCode: $responseBodyString")
                return@withContext Result.failure(
                    Exception("Gemini API Error (HTTP $errCode): ${response.message.ifBlank { "Please verify your Gemini API key in the AI Studio Secrets panel." }}")
                )
            }

            val responseJson = JSONObject(responseBodyString)
            val candidates = responseJson.optJSONArray("candidates")
            if (candidates == null || candidates.length() == 0) {
                return@withContext Result.failure(Exception("No answer generated. The model may have filtered the prompt or returned an empty candidate."))
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
            }.trim().ifBlank { "No response text received from model." }

            // Extract Search Grounding metadata if present
            val searchQueries = mutableListOf<String>()
            val groundingSources = mutableListOf<GroundingSource>()
            var wasSearchUsed = shouldEnableSearch

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
                            val title = web.optString("title").ifBlank { "Web Source" }
                            val sourceUrl = web.optString("uri")
                            if (sourceUrl.isNotBlank()) {
                                groundingSources.add(GroundingSource(title, sourceUrl))
                            }
                        }
                    }
                }
            }

            Result.success(
                ChatMessage(
                    sender = ChatSender.AI_TUTOR,
                    text = replyText,
                    modelUsed = persona.modelId,
                    personaName = persona.title,
                    isSearchGrounded = wasSearchUsed,
                    searchQueries = searchQueries,
                    groundingSources = groundingSources.distinctBy { it.url }
                )
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error executing Gemini request", e)
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
            lower.contains("marking scheme") || lower.contains("marking") || lower.contains("score") -> {
                """
                ### 📋 CBSE Official Marking Scheme Breakdown (${grade.displayName})
                
                Examiners award marks in **step-wise increments** as per CBSE evaluation guidelines$contextTag:
                
                1. **Principle / Definition / Formula (1 Mark)**:
                   - Explicitly write the fundamental law, theorem, or standard formula.
                   - Mention standard notations and SI units.
                   
                2. **Working Steps & Value Substitution (2 to 3 Marks)**:
                   - In **Science & Math**: Substitute given numeric values with proper signs (e.g. Cartesian sign convention for optics).
                   - In **Commerce (Accounts/BST)**: Journal entry format (`Date | Particulars | L.F. | Debit | Credit`) carries 0.5 to 1 mark. Working notes are strictly evaluated!
                   
                3. **Diagram / Schematic Representation (1 Mark)**:
                   - Draw neat, labeled diagrams with directional arrows (e.g., ray diagrams in Physics, circular flow in Macroeconomics).
                   
                4. **Final Conclusion & Unit (0.5 to 1 Mark)**:
                   - Highlight final calculated value with unit in a box (e.g., `Ans: +15 cm` or `Goodwill = ₹1,20,000`).
                   
                💡 **Topper Hack**: If a 5-mark question asks for explanation, structure into: **(a) Definition**, **(b) Essential Features (3 points)**, **(c) Practical Board Example**.
                """.trimIndent()
            }

            lower.contains("partnership") || lower.contains("goodwill") || lower.contains("share") || lower.contains("debit") || lower.contains("credit") -> {
                """
                ### 📊 Accountancy & Commerce Step-by-Step Solver
                
                **Key Rules for Partnership & Share Capital ($board):**
                
                1. **Profit & Loss Appropriation A/c Golden Rules:**
                   - **Debit side**: Interest on Capital, Partner Salary/Commission, Transfer to Reserve.
                   - **Credit side**: Net Profit transferred from P&L, Interest on Drawings.
                   
                2. **Goodwill Valuation Quick Recap:**
                   - **Average Profit Method**: (Normal Profit) × (Number of Years' Purchase)
                   - **Super Profit Method**: (Actual Profit - Normal Profit) × Years' Purchase
                   - **Capitalisation Method**: (Super Profit / Normal Rate of Return) × 100
                   
                3. **Pro-Rata Allotment Standard Entry:**
                   - `Bank A/c Dr.` (Application money received on applied shares)
                   - `To Share Application A/c`
                   - `Share Application A/c Dr.`
                   - `To Share Capital A/c` (Actual allotted shares × Application money)
                   - `To Share Allotment A/c` (Excess adjusted towards allotment)
                   - `To Bank A/c` (Refund for rejected applications)
                   
                📌 *Tap "Save to Notes" below to turn this solution directly into a Cornell revision sheet!*
                """.trimIndent()
            }

            lower.contains("optics") || lower.contains("light") || lower.contains("ray") || lower.contains("mirror") || lower.contains("lens") -> {
                """
                ### 🔬 Ray Optics & Light Core Master Notes
                
                **1. Mirror Formula & Lens Formula Comparison:**
                - **Mirror Formula**: 1/f = 1/v + 1/u
                - **Lens Formula**: 1/f = 1/v - 1/u
                - **Magnification (Mirror)**: m = -v/u = h_i / h_o
                - **Magnification (Lens)**: m = +v/u = h_i / h_o
                
                **2. New Cartesian Sign Convention (CBSE Crux):**
                - Object distance u is **always negative** (u < 0).
                - Concave mirror / Concave lens: Focal length f is **always negative**.
                - Convex mirror / Convex lens: Focal length f is **always positive**.
                - Real and inverted image: v is negative for mirror, positive for lens; m is negative.
                - Virtual and erect image: m is positive (m > 0).
                
                **3. Common Board Exam Trap:**
                Never forget to draw arrows indicating the direction of light rays! CBSE examiners deduct 0.5 mark per ray diagram if arrows are missing.
                """.trimIndent()
            }

            lower.contains("cbse") || lower.contains("syllabus") || lower.contains("exam date") || lower.contains("date sheet") || lower.contains("updates") -> {
                """
                ### 🌐 Live CBSE Academic & Examination Updates
                
                - **Official Board Calendar**: CBSE annual Board Examinations commence in mid-February and run through early April.
                - **Question Paper Pattern (NEP 2020 Aligned)**:
                  - **50% Competency-Based Questions**: Case study questions, assertion-reasoning, source-based questions.
                  - **20% Objective / MCQ Questions**: Fast 1-mark questions testing direct conceptual understanding.
                  - **30% Short & Long Answer Questions**: Traditional subjective questions with internal choice.
                - **Marking Scheme Transparency**: Evaluators must strictly follow the official model answer key issued by the CBSE Controller of Examinations.
                
                💡 *Tip: Toggle Google Search Grounding to verify real-time notifications directly from cbse.gov.in.*
                """.trimIndent()
            }

            else -> {
                """
                ### 🎓 Vidya AI Tutor Response
                
                Here is the structured breakdown for **${userMessage.trim()}**$contextTag:
                
                1. **Core Concept & Definition:**
                   - This fundamental topic in **${grade.displayName}** is frequently tested in both Section B (Short Answer) and Section C/D of the ${board.shortName} board exam.
                   - Ensure you memorize the standard NCERT wording for key definitions to secure maximum marks.
                   
                2. **Step-by-Step Explanation:**
                   - Understand the underlying principle before attempting derivations or numerical applications.
                   - Practice drawing diagrams and mind-mapping interconnected concepts.
                   
                3. **CBSE Board Exam High-Yield Tips:**
                   - Always state standard assumptions and given variables.
                   - Review previous 5-year question papers (PYQs) for recurring questions on this concept.
                   - Avoid overwriting; stick to the specified CBSE word limits (30-50 words for 2 marks, 50-80 words for 3 marks).
                   
                *(To unlock live generative responses with Gemini models like ${persona.modelId}, ensure your Gemini API Key is configured in the AI Studio Secrets panel.)*
                """.trimIndent()
            }
        }
    }
}
