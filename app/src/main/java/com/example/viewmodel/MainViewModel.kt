package com.example.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.GeminiChatRepository
import com.example.data.MnemonicsRepository
import com.example.data.NoteMethodsRepository
import com.example.data.NoteRepository
import com.example.data.SyllabusRepository
import com.example.data.TestPaperGeneratorRepository
import com.example.data.local.AppDatabase
import com.example.model.*
import com.example.util.NetworkMonitor
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class MainUiState(
    val selectedGrade: ClassGrade = ClassGrade.CLASS_10,
    val selectedBoard: BoardType = BoardType.CBSE,
    val selectedSubject: SubjectType? = null,
    val searchQuery: String = "",
    val activeTab: AppTab = AppTab.SYLLABUS,
    val selectedChapter: ChapterItem? = null,
    val selectedMethodDetail: NoteMethodDetail? = null,
    val noteFilterSubject: String = "All",
    val isCoverRecallModeActive: Boolean = false,
    val userNotes: List<NoteEntity> = emptyList(),
    val flashcards: List<FlashcardEntity> = emptyList(),
    val isSearching: Boolean = false,
    val noteEditDraft: NoteEntity? = null,
    val showNoteEditor: Boolean = false,
    val showVedicPracticeDialog: Boolean = false,
    val vedicPracticeInput: String = "",
    val vedicPracticeResult: String = "",
    val selectedTestPaper: TestPaperItem? = null,
    val selectedQuestionId: String? = null,
    val selectedMcqOptions: Map<String, Int> = emptyMap(),
    val revealedMarkingSchemes: Set<String> = emptySet(),
    val selfEvaluatedMarks: Map<String, Int> = emptyMap(),
    val testPaperFilterSubject: SubjectType? = null,
    val isExamTimerRunning: Boolean = false,
    val examTimeSecondsRemaining: Int = 180 * 60,
    val customTestPapers: List<TestPaperItem> = emptyList(),
    val isGeneratingTestPaper: Boolean = false,
    val paperForPdfExport: TestPaperItem? = null,
    val showTestPaperGenerateDialog: Boolean = false,
    val testPaperGenerationError: String? = null,
    val isLiteMode: Boolean = false,
    val showLiteModeInfoDialog: Boolean = false,
    val dataSavedMegabytes: Double = 148.5,
    val isNetworkAvailable: Boolean = true,
    val isAutoNetworkSwitchingEnabled: Boolean = true,
    val networkNoticeMessage: String? = null,
    val videoFilterSubject: SubjectType? = null,
    val videoFilterCategory: VideoCategory = VideoCategory.ALL,
    val videoSearchQuery: String = "",
    val savedVideoIds: Set<String> = emptySet(),
    val chatMessages: List<ChatMessage> = listOf(
        ChatMessage(
            sender = ChatSender.AI_TUTOR,
            text = "Namaste! 🙏 I am your **Vidya AI Educational Mentor**.\n\nI can assist you across all Indian curricula (CBSE, ICSE, State Boards):\n• 📘 **NCERT Concept Explanations** with step-by-step clarity\n• 📋 **CBSE Marking Schemes** to maximize your board exam score\n• 🔬 **Complex STEM & Accounts Numericals** (Partnership, Physics Optics, Calculus)\n• 🌐 **Live CBSE Updates & Syllabus Grounding** using Google Search\n• ⚡ **Rapid 1-minute formulas & Cornell note cues**\n\nChoose an AI mode above or ask any doubt to get started!",
            modelUsed = "gemini-3.5-flash",
            personaName = "Vidya AI Guru"
        )
    ),
    val selectedAiPersona: AiTutorPersona = AiTutorPersona.GENERAL_CBSE,
    val isAiSearchGroundingEnabled: Boolean = false,
    val isAiGenerating: Boolean = false,
    val aiContextChapter: ChapterItem? = null,
    val aiInputText: String = "",
    val isSummarizingNote: Boolean = false,
    val activeNoteCruxSummary: NoteCruxSummary? = null,
    val noteCruxTargetNoteId: Long? = null,
    val selectedNcertBook: NcertBook? = null,
    val ncertSearchQuery: String = "",
    val ncertFilterSubject: SubjectType? = null,
    val selectedNcertChapter: NcertChapterInfo? = null,
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val showApkDownloadDialog: Boolean = false
)

enum class ThemeMode(val displayName: String, val subtitle: String) {
    SYSTEM("System Default", "Follows device appearance"),
    LIGHT("Light Mode", "Crisp paper parchment style for daylight"),
    DARK("Dark Mode", "Eye-friendly low-light reading for night study")
}

enum class AppTab(val title: String, val iconKey: String) {
    SYLLABUS("Syllabus", "MenuBook"),
    AI_TUTOR("AI Tutor", "AutoAwesome"),
    VIDEOS("Videos", "PlayCircle"),
    TEST_PAPERS("Papers", "Assignment"),
    NOTEBOOK("Notebook", "EditNote"),
    NOTE_METHODS("Methods", "Lightbulb"),
    EXAM_TRICKS("Tricks", "Bolt"),
    FLASHCARDS("Cards", "Style"),
    NCERT("NCERT", "Folder")
}

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val repository = NoteRepository(database.noteDao(), database.flashcardDao())
    private val networkMonitor = NetworkMonitor(application)
    private val sharedPreferences = application.getSharedPreferences("vidya_notes_prefs", Context.MODE_PRIVATE)

    private val _uiState = MutableStateFlow(
        MainUiState(
            themeMode = runCatching {
                ThemeMode.valueOf(
                    application.getSharedPreferences("vidya_notes_prefs", Context.MODE_PRIVATE)
                        .getString("theme_mode", ThemeMode.SYSTEM.name) ?: ThemeMode.SYSTEM.name
                )
            }.getOrDefault(ThemeMode.SYSTEM)
        )
    )
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        // Set initial state based on current device network connectivity
        val initialOnline = networkMonitor.isCurrentlyOnline()
        _uiState.update {
            it.copy(
                isNetworkAvailable = initialOnline,
                isLiteMode = !initialOnline
            )
        }

        // Automatically switch between Lite Mode and Full Mode when internet goes ON or OFF
        viewModelScope.launch {
            networkMonitor.isOnline.collect { online ->
                _uiState.update { current ->
                    if (current.isAutoNetworkSwitchingEnabled) {
                        val newLite = !online
                        val targetTab = if (newLite && (current.activeTab == AppTab.VIDEOS || current.activeTab == AppTab.NOTE_METHODS || current.activeTab == AppTab.EXAM_TRICKS)) {
                            AppTab.SYLLABUS
                        } else {
                            current.activeTab
                        }
                        val notice = if (online) {
                            "🌐 Internet Connected: Switched to Full Edition automatically"
                        } else {
                            "⚡ Internet Disconnected: Switched to Offline Lite Mode automatically"
                        }
                        current.copy(
                            isNetworkAvailable = online,
                            isLiteMode = newLite,
                            activeTab = targetTab,
                            networkNoticeMessage = notice
                        )
                    } else {
                        current.copy(isNetworkAvailable = online)
                    }
                }
            }
        }

        viewModelScope.launch {
            repository.seedInitialFlashcardsIfEmpty(MnemonicsRepository.seedFlashcards)
        }

        viewModelScope.launch {
            repository.allNotes.collect { notes ->
                _uiState.update { it.copy(userNotes = notes) }
            }
        }

        viewModelScope.launch {
            repository.allFlashcards.collect { cards ->
                _uiState.update { it.copy(flashcards = cards) }
            }
        }
    }

    fun selectTab(tab: AppTab) {
        _uiState.update { it.copy(activeTab = tab, selectedChapter = null, selectedMethodDetail = null) }
    }

    fun setGrade(grade: ClassGrade) {
        _uiState.update { it.copy(selectedGrade = grade) }
    }

    fun setBoard(board: BoardType) {
        _uiState.update { it.copy(selectedBoard = board) }
    }

    fun setSubject(subject: SubjectType?) {
        _uiState.update { it.copy(selectedSubject = subject) }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun selectChapter(chapter: ChapterItem?) {
        _uiState.update { it.copy(selectedChapter = chapter) }
    }

    fun setNcertSearchQuery(query: String) {
        _uiState.update { it.copy(ncertSearchQuery = query) }
    }

    fun setNcertFilterSubject(subject: SubjectType?) {
        _uiState.update { it.copy(ncertFilterSubject = subject) }
    }

    fun selectNcertBook(book: NcertBook?) {
        _uiState.update { it.copy(selectedNcertBook = book, selectedNcertChapter = null) }
    }

    fun selectNcertChapter(chapter: NcertChapterInfo?) {
        _uiState.update { it.copy(selectedNcertChapter = chapter) }
    }

    fun openMatchingSyllabusChapter(matchingChapterId: String) {
        val chapter = SyllabusRepository.chapters.find { it.id == matchingChapterId }
        if (chapter != null) {
            _uiState.update {
                it.copy(
                    activeTab = AppTab.SYLLABUS,
                    selectedChapter = chapter,
                    selectedGrade = chapter.grade,
                    selectedSubject = chapter.subject
                )
            }
        }
    }

    fun selectMethodDetail(detail: NoteMethodDetail?) {
        _uiState.update { it.copy(selectedMethodDetail = detail) }
    }

    fun setNoteFilterSubject(subject: String) {
        _uiState.update { it.copy(noteFilterSubject = subject) }
    }

    fun toggleCoverRecallMode() {
        _uiState.update { it.copy(isCoverRecallModeActive = !it.isCoverRecallModeActive) }
    }

    fun openNewNoteEditor(
        initialTitle: String = "",
        initialSubject: String = "Science",
        initialChapter: String = "",
        initialMethod: NoteMethodType = NoteMethodType.CORNELL,
        initialCue: String = "",
        initialMain: String = "",
        initialSummary: String = "",
        initialTags: String = ""
    ) {
        val newNote = NoteEntity(
            title = initialTitle,
            board = _uiState.value.selectedBoard.shortName,
            grade = _uiState.value.selectedGrade.displayName,
            subject = initialSubject,
            chapter = initialChapter,
            methodType = initialMethod.name,
            cueOrKeywordColumn = initialCue,
            mainContent = initialMain,
            summaryOrConclusion = initialSummary,
            tags = initialTags,
            isPinned = false
        )
        _uiState.update { it.copy(noteEditDraft = newNote, showNoteEditor = true) }
    }

    fun editExistingNote(note: NoteEntity) {
        _uiState.update { it.copy(noteEditDraft = note, showNoteEditor = true) }
    }

    fun closeNoteEditor() {
        _uiState.update { it.copy(noteEditDraft = null, showNoteEditor = false) }
    }

    fun saveCurrentDraft(draft: NoteEntity) {
        viewModelScope.launch {
            val updated = draft.copy(updatedAt = System.currentTimeMillis())
            repository.saveNote(updated)
            _uiState.update { it.copy(noteEditDraft = null, showNoteEditor = false) }
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun togglePinNote(noteId: Long) {
        viewModelScope.launch {
            repository.togglePin(noteId)
        }
    }

    fun generateNoteCrux(
        title: String,
        subject: String,
        chapter: String,
        mainContent: String,
        cues: String = "",
        targetNoteId: Long? = null,
        onComplete: ((NoteCruxSummary) -> Unit)? = null
    ) {
        _uiState.update { it.copy(isSummarizingNote = true) }
        viewModelScope.launch {
            val result = GeminiChatRepository.summarizeStudentNotes(
                title = title,
                subject = subject,
                chapter = chapter,
                mainContent = mainContent,
                cues = cues,
                grade = _uiState.value.selectedGrade.displayName,
                board = _uiState.value.selectedBoard.shortName
            )
            val summary = result.getOrNull() ?: GeminiChatRepository.generateOfflineNoteCrux(
                title, subject, chapter, mainContent, cues,
                _uiState.value.selectedGrade.displayName,
                _uiState.value.selectedBoard.shortName
            )
            _uiState.update {
                it.copy(
                    isSummarizingNote = false,
                    activeNoteCruxSummary = summary,
                    noteCruxTargetNoteId = targetNoteId
                )
            }
            onComplete?.invoke(summary)
        }
    }

    fun generateNoteCruxForExistingNote(note: NoteEntity) {
        generateNoteCrux(
            title = note.title,
            subject = note.subject,
            chapter = note.chapter,
            mainContent = note.mainContent,
            cues = note.cueOrKeywordColumn,
            targetNoteId = note.id
        )
    }

    fun applyCruxToTargetNote(cruxText: String, cuesText: String? = null) {
        val targetId = _uiState.value.noteCruxTargetNoteId
        if (targetId != null) {
            viewModelScope.launch {
                val existing = repository.getNoteById(targetId)
                if (existing != null) {
                    val updated = existing.copy(
                        summaryOrConclusion = cruxText,
                        cueOrKeywordColumn = if (!cuesText.isNullOrBlank()) cuesText else existing.cueOrKeywordColumn,
                        updatedAt = System.currentTimeMillis()
                    )
                    repository.saveNote(updated)
                }
                _uiState.update { it.copy(activeNoteCruxSummary = null, noteCruxTargetNoteId = null) }
            }
        } else {
            _uiState.update { it.copy(activeNoteCruxSummary = null, noteCruxTargetNoteId = null) }
        }
    }

    fun dismissNoteCruxDialog() {
        _uiState.update { it.copy(activeNoteCruxSummary = null, noteCruxTargetNoteId = null) }
    }

    fun toggleFlashcardMastery(cardId: Long, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.updateFlashcardStatus(cardId, !currentStatus)
        }
    }

    fun createFlashcardFromNote(
        subject: String,
        chapter: String,
        question: String,
        answer: String,
        mnemonic: String
    ) {
        viewModelScope.launch {
            val card = FlashcardEntity(
                subject = subject,
                chapter = chapter,
                questionFront = question,
                answerBack = answer,
                mnemonicOrTrick = mnemonic
            )
            repository.saveFlashcard(card)
        }
    }

    fun deleteFlashcard(flashcard: FlashcardEntity) {
        viewModelScope.launch {
            repository.deleteFlashcard(flashcard)
        }
    }

    fun calculateVedicSquare(numberStr: String) {
        val num = numberStr.trim().toLongOrNull()
        if (num == null) {
            _uiState.update { it.copy(vedicPracticeResult = "Please enter a valid whole number.") }
            return
        }
        if (num % 10L != 5L) {
            _uiState.update { it.copy(vedicPracticeResult = "This Vedic trick (Ekadhikena Purvena) is specifically for numbers ending in 5 (e.g., 25, 45, 85, 115). For $num, the square is ${num * num}.") }
            return
        }
        val prefix = num / 10L
        val leftPart = prefix * (prefix + 1)
        val result = "${leftPart}25"
        _uiState.update {
            it.copy(
                vedicPracticeResult = "✅ Vedic Trick Step-by-Step for $num²:\n1. Number before '5' is $prefix\n2. Multiply $prefix × ($prefix + 1) = $prefix × ${prefix + 1} = $leftPart\n3. Append '25' to the end\n👉 Final Square = $result"
            )
        }
    }

    fun calculateVedicBase100(num1Str: String, num2Str: String) {
        val n1 = num1Str.trim().toLongOrNull()
        val n2 = num2Str.trim().toLongOrNull()
        if (n1 == null || n2 == null) {
            _uiState.update { it.copy(vedicPracticeResult = "Please enter two valid numbers near 100.") }
            return
        }
        val d1 = n1 - 100
        val d2 = n2 - 100
        val leftPart = n1 + d2
        val rightPart = d1 * d2
        val rightStr = if (rightPart in 0..9) "0$rightPart" else rightPart.toString()
        val product = n1 * n2
        _uiState.update {
            it.copy(
                vedicPracticeResult = "✅ Vedic Nikhilam Trick for $n1 × $n2:\n1. Deviations from 100: ($d1) and ($d2)\n2. Left Side: $n1 + ($d2) = $leftPart\n3. Right Side: ($d1) × ($d2) = $rightStr\n👉 Final Product = $product"
            )
        }
    }

    // ==========================================
    // TEST PAPER INTERACTIONS
    // ==========================================
    fun selectTestPaper(paper: TestPaperItem?) {
        _uiState.update {
            it.copy(
                selectedTestPaper = paper,
                selectedQuestionId = null,
                isExamTimerRunning = false,
                examTimeSecondsRemaining = (paper?.timeAllowedMinutes ?: 180) * 60
            )
        }
    }

    fun setTestPaperFilterSubject(subject: SubjectType?) {
        _uiState.update { it.copy(testPaperFilterSubject = subject) }
    }

    fun selectMcqOption(questionId: String, optionIndex: Int) {
        _uiState.update {
            val updated = it.selectedMcqOptions.toMutableMap()
            updated[questionId] = optionIndex
            it.copy(selectedMcqOptions = updated)
        }
    }

    fun toggleMarkingScheme(questionId: String) {
        _uiState.update {
            val updated = it.revealedMarkingSchemes.toMutableSet()
            if (updated.contains(questionId)) {
                updated.remove(questionId)
            } else {
                updated.add(questionId)
            }
            it.copy(revealedMarkingSchemes = updated)
        }
    }

    fun setQuestionSelfScore(questionId: String, marks: Int) {
        _uiState.update {
            val updated = it.selfEvaluatedMarks.toMutableMap()
            updated[questionId] = marks
            it.copy(selfEvaluatedMarks = updated)
        }
    }

    fun toggleExamTimer() {
        val nextRunning = !_uiState.value.isExamTimerRunning
        _uiState.update { it.copy(isExamTimerRunning = nextRunning) }
        if (nextRunning) {
            startTimerLoop()
        }
    }

    fun resetExamTimer() {
        _uiState.update {
            it.copy(
                isExamTimerRunning = false,
                examTimeSecondsRemaining = (_uiState.value.selectedTestPaper?.timeAllowedMinutes ?: 180) * 60
            )
        }
    }

    fun openTestPaperGenerateDialog() {
        _uiState.update { it.copy(showTestPaperGenerateDialog = true) }
    }

    fun closeTestPaperGenerateDialog() {
        _uiState.update { it.copy(showTestPaperGenerateDialog = false) }
    }

    fun openPdfExportDialog(paper: TestPaperItem) {
        _uiState.update { it.copy(paperForPdfExport = paper) }
    }

    fun closePdfExportDialog() {
        _uiState.update { it.copy(paperForPdfExport = null) }
    }

    fun toggleLiteMode() {
        _uiState.update { current ->
            val newLite = !current.isLiteMode
            // In Lite mode, we focus on SYLLABUS, NOTEBOOK, FLASHCARDS, TEST_PAPERS
            val targetTab = if (newLite && (current.activeTab == AppTab.VIDEOS || current.activeTab == AppTab.NOTE_METHODS || current.activeTab == AppTab.EXAM_TRICKS)) {
                AppTab.SYLLABUS
            } else {
                current.activeTab
            }
            current.copy(
                isLiteMode = newLite,
                activeTab = targetTab,
                networkNoticeMessage = if (newLite) "⚡ Switched to Offline Lite Mode" else "🌐 Switched to Full Edition"
            )
        }
    }

    fun dismissNetworkNotice() {
        _uiState.update { it.copy(networkNoticeMessage = null) }
    }

    fun toggleAutoNetworkSwitching() {
        _uiState.update { current ->
            val next = !current.isAutoNetworkSwitchingEnabled
            current.copy(
                isAutoNetworkSwitchingEnabled = next,
                networkNoticeMessage = if (next) "Auto network switching enabled (Lite on offline, Full on online)" else "Auto network switching paused"
            )
        }
    }

    fun openLiteModeInfoDialog() {
        _uiState.update { it.copy(showLiteModeInfoDialog = true) }
    }

    fun closeLiteModeInfoDialog() {
        _uiState.update { it.copy(showLiteModeInfoDialog = false) }
    }

    fun generateTestPaper(
        request: PaperGenerationRequest,
        onComplete: ((TestPaperItem) -> Unit)? = null
    ) {
        _uiState.update {
            it.copy(
                isGeneratingTestPaper = true,
                testPaperGenerationError = null
            )
        }

        viewModelScope.launch {
            val result = TestPaperGeneratorRepository.generateTestPaper(request)
            result.fold(
                onSuccess = { generatedPaper ->
                    _uiState.update { state ->
                        val updatedList = listOf(generatedPaper) + state.customTestPapers
                        state.copy(
                            customTestPapers = updatedList,
                            selectedTestPaper = generatedPaper,
                            isGeneratingTestPaper = false,
                            showTestPaperGenerateDialog = false,
                            isExamTimerRunning = false,
                            examTimeSecondsRemaining = generatedPaper.timeAllowedMinutes * 60
                        )
                    }
                    onComplete?.invoke(generatedPaper)
                },
                onFailure = { err ->
                    _uiState.update {
                        it.copy(
                            isGeneratingTestPaper = false,
                            testPaperGenerationError = err.localizedMessage ?: "Failed to generate paper"
                        )
                    }
                }
            )
        }
    }

    private var timerJob: kotlinx.coroutines.Job? = null
    private fun startTimerLoop() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.isExamTimerRunning && _uiState.value.examTimeSecondsRemaining > 0) {
                kotlinx.coroutines.delay(1000L)
                if (_uiState.value.isExamTimerRunning) {
                    _uiState.update {
                        val next = it.examTimeSecondsRemaining - 1
                        if (next <= 0) {
                            it.copy(examTimeSecondsRemaining = 0, isExamTimerRunning = false)
                        } else {
                            it.copy(examTimeSecondsRemaining = next)
                        }
                    }
                }
            }
        }
    }

    fun resetTestPaperProgress(paperId: String) {
        _uiState.update {
            it.copy(
                selectedMcqOptions = emptyMap(),
                revealedMarkingSchemes = emptySet(),
                selfEvaluatedMarks = emptyMap(),
                examTimeSecondsRemaining = (it.selectedTestPaper?.timeAllowedMinutes ?: 180) * 60,
                isExamTimerRunning = false
            )
        }
    }

    fun setVideoFilterSubject(subject: SubjectType?) {
        _uiState.update { it.copy(videoFilterSubject = subject) }
    }

    fun setVideoFilterCategory(category: VideoCategory) {
        _uiState.update { it.copy(videoFilterCategory = category) }
    }

    fun setVideoSearchQuery(query: String) {
        _uiState.update { it.copy(videoSearchQuery = query) }
    }

    fun toggleSaveVideo(videoId: String) {
        _uiState.update { state ->
            val updated = if (state.savedVideoIds.contains(videoId)) {
                state.savedVideoIds - videoId
            } else {
                state.savedVideoIds + videoId
            }
            state.copy(savedVideoIds = updated)
        }
    }

    // ==========================================
    // AI EDUCATIONAL TUTOR BOT
    // ==========================================
    fun setAiPersona(persona: AiTutorPersona) {
        _uiState.update {
            it.copy(
                selectedAiPersona = persona,
                isAiSearchGroundingEnabled = persona.defaultSearchGrounding || it.isAiSearchGroundingEnabled
            )
        }
    }

    fun toggleAiSearchGrounding() {
        _uiState.update { it.copy(isAiSearchGroundingEnabled = !it.isAiSearchGroundingEnabled) }
    }

    fun setAiInputText(text: String) {
        _uiState.update { it.copy(aiInputText = text) }
    }

    fun clearAiChat() {
        _uiState.update {
            it.copy(
                chatMessages = listOf(
                    ChatMessage(
                        sender = ChatSender.AI_TUTOR,
                        text = "Namaste! 🙏 Chat history cleared. What topic or doubt from your **${it.selectedGrade.displayName}** curriculum would you like to master next?",
                        modelUsed = it.selectedAiPersona.modelId,
                        personaName = it.selectedAiPersona.title
                    )
                ),
                aiContextChapter = null,
                isAiGenerating = false
            )
        }
    }

    fun openAiChatWithContext(chapter: ChapterItem, prompt: String? = null) {
        _uiState.update {
            it.copy(
                activeTab = AppTab.AI_TUTOR,
                aiContextChapter = chapter,
                aiInputText = prompt ?: "Explain the most important concepts and CBSE marking scheme for '${chapter.title}'"
            )
        }
        if (!prompt.isNullOrBlank()) {
            sendAiMessage(prompt)
        }
    }

    fun retryLastAiMessage() {
        val lastUserMsg = _uiState.value.chatMessages.lastOrNull { it.sender == ChatSender.USER } ?: return
        // Remove trailing error message if any
        _uiState.update { state ->
            state.copy(chatMessages = state.chatMessages.filter { !it.isError })
        }
        sendAiMessage(lastUserMsg.text, isRetry = true)
    }

    fun sendAiMessage(customPrompt: String? = null, isRetry: Boolean = false) {
        val prompt = (customPrompt ?: _uiState.value.aiInputText).trim()
        if (prompt.isBlank() || _uiState.value.isAiGenerating) return

        val currentState = _uiState.value
        val persona = currentState.selectedAiPersona
        val isSearchGrounding = currentState.isAiSearchGroundingEnabled
        val grade = currentState.selectedGrade
        val board = currentState.selectedBoard
        val contextChapter = currentState.aiContextChapter?.title

        val userMessage = ChatMessage(
            sender = ChatSender.USER,
            text = prompt
        )

        val loadingPlaceholder = ChatMessage(
            sender = ChatSender.AI_TUTOR,
            text = "Analyzing concept & formulating explanation...",
            modelUsed = persona.modelId,
            personaName = persona.title,
            isSearchGrounded = isSearchGrounding,
            isLoading = true
        )

        val updatedList = if (isRetry) {
            currentState.chatMessages.filter { it.id != userMessage.id } + listOf(loadingPlaceholder)
        } else {
            currentState.chatMessages + listOf(userMessage, loadingPlaceholder)
        }

        _uiState.update {
            it.copy(
                chatMessages = updatedList,
                isAiGenerating = true,
                aiInputText = ""
            )
        }

        viewModelScope.launch {
            val result = GeminiChatRepository.generateAiResponse(
                conversationHistory = currentState.chatMessages,
                userMessage = prompt,
                persona = persona,
                isSearchGroundingEnabled = isSearchGrounding,
                grade = grade,
                board = board,
                chapterContext = contextChapter
            )

            _uiState.update { state ->
                val listWithoutLoading = state.chatMessages.filter { !it.isLoading }
                result.fold(
                    onSuccess = { replyMsg ->
                        state.copy(
                            chatMessages = listWithoutLoading + replyMsg,
                            isAiGenerating = false
                        )
                    },
                    onFailure = { error ->
                        val errorMsg = ChatMessage(
                            sender = ChatSender.AI_TUTOR,
                            text = "Could not generate response: ${error.localizedMessage ?: "Network or API issue."}\n\n*Tip: Check that your Gemini API key is valid in the AI Studio Secrets panel or try again.*",
                            modelUsed = persona.modelId,
                            personaName = persona.title,
                            isError = true,
                            errorMessage = error.message
                        )
                        state.copy(
                            chatMessages = listWithoutLoading + errorMsg,
                            isAiGenerating = false
                        )
                    }
                )
            }
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        _uiState.update { it.copy(themeMode = mode) }
        viewModelScope.launch {
            sharedPreferences.edit().putString("theme_mode", mode.name).apply()
        }
    }

    fun toggleThemeMode() {
        val nextMode = when (_uiState.value.themeMode) {
            ThemeMode.SYSTEM -> ThemeMode.DARK
            ThemeMode.DARK -> ThemeMode.LIGHT
            ThemeMode.LIGHT -> ThemeMode.DARK
        }
        setThemeMode(nextMode)
    }

    fun openApkDownloadDialog() {
        _uiState.update { it.copy(showApkDownloadDialog = true) }
    }

    fun dismissApkDownloadDialog() {
        _uiState.update { it.copy(showApkDownloadDialog = false) }
    }
}
