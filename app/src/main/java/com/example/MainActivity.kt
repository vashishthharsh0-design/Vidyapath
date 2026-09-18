package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.NcertNotesProvider
import com.example.model.NoteMethodType
import com.example.ui.components.AppBottomNav
import com.example.ui.components.AppTopBar
import com.example.ui.components.LiteModeInfoDialog
import com.example.ui.components.NoteCruxSummaryDialog
import com.example.ui.screens.*
import com.example.ui.theme.VidyaNotesTheme
import com.example.viewmodel.AppTab
import com.example.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VidyaNotesTheme {
                VidyaNotesApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun VidyaNotesApp(viewModel: MainViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Handle Android system back button
    BackHandler(enabled = state.showNoteEditor || state.selectedChapter != null || state.selectedMethodDetail != null || state.selectedTestPaper != null || state.selectedNcertBook != null) {
        if (state.showNoteEditor) {
            viewModel.closeNoteEditor()
        } else if (state.selectedTestPaper != null) {
            viewModel.selectTestPaper(null)
        } else if (state.selectedChapter != null) {
            viewModel.selectChapter(null)
        } else if (state.selectedNcertBook != null) {
            viewModel.selectNcertBook(null)
        } else if (state.selectedMethodDetail != null) {
            viewModel.selectMethodDetail(null)
        }
    }

    if (state.showNoteEditor && state.noteEditDraft != null) {
        NotesEditorScreen(
            initialNote = state.noteEditDraft!!,
            onSave = { updated -> viewModel.saveCurrentDraft(updated) },
            onCancel = { viewModel.closeNoteEditor() },
            onConvertToFlashcard = { q, a, subj, chap, mnem ->
                viewModel.createFlashcardFromNote(subj, chap, q, a, mnem)
            },
            isSummarizingCrux = state.isSummarizingNote,
            activeCruxSummary = if (state.noteCruxTargetNoteId == null) state.activeNoteCruxSummary else null,
            onGenerateCrux = { title, subj, chap, content, cues ->
                viewModel.generateNoteCrux(title, subj, chap, content, cues)
            },
            onDismissCruxDialog = { viewModel.dismissNoteCruxDialog() }
        )
    } else {
        Scaffold(
            contentWindowInsets = WindowInsets.statusBars,
            topBar = {
                AppTopBar(
                    state = state,
                    onGradeChange = { viewModel.setGrade(it) },
                    onBoardChange = { viewModel.setBoard(it) },
                    onSearchQueryChange = { viewModel.setSearchQuery(it) },
                    onToggleRecallMode = { viewModel.toggleCoverRecallMode() },
                    onToggleLiteMode = { viewModel.toggleLiteMode() },
                    onOpenLiteModeInfo = { viewModel.openLiteModeInfoDialog() },
                    onDismissNetworkNotice = { viewModel.dismissNetworkNotice() },
                    onBackClick = if (state.selectedChapter != null) {
                        { viewModel.selectChapter(null) }
                    } else if (state.selectedNcertBook != null) {
                        { viewModel.selectNcertBook(null) }
                    } else null,
                    onAiTutorClick = { viewModel.selectTab(AppTab.AI_TUTOR) }
                )
            },
            bottomBar = {
                if (state.selectedChapter == null && state.selectedNcertBook == null) {
                    AppBottomNav(
                        selectedTab = state.activeTab,
                        onTabSelected = { viewModel.selectTab(it) },
                        isLiteMode = state.isLiteMode
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (state.selectedChapter != null) {
                    ChapterDetailScreen(
                        chapter = state.selectedChapter!!,
                        onBack = { viewModel.selectChapter(null) },
                        onAskAiTutor = { chapter -> viewModel.openAiChatWithContext(chapter) },
                        onCreateNotes = { chapter, method ->
                            viewModel.openNewNoteEditor(
                                initialTitle = "${chapter.title} - ${method.title}",
                                initialSubject = chapter.subject.displayName,
                                initialChapter = "Ch ${chapter.chapterNumber}: ${chapter.title}",
                                initialMethod = method,
                                initialCue = chapter.pyqTrends.joinToString("\n") { "• ${it.topicName} (${it.typicalMarks})" },
                                initialMain = chapter.cruxPoints.joinToString("\n\n") { "${it.pointNumber}. ${it.title}:\n${it.description}" },
                                initialSummary = chapter.summary,
                                initialTags = "#${chapter.subject.displayName}, #Class10, #NCERT, #${method.name}"
                            )
                        }
                    )
                } else {
                    AnimatedContent(
                        targetState = state.activeTab,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "tab_transition"
                    ) { activeTab ->
                        when (activeTab) {
                            AppTab.SYLLABUS -> {
                                SyllabusScreen(
                                    state = state,
                                    onChapterClick = { viewModel.selectChapter(it) },
                                    onSubjectSelect = { viewModel.setSubject(it) },
                                    onCreateNotesForChapter = { chapter ->
                                        viewModel.openNewNoteEditor(
                                            initialTitle = "${chapter.title} - Smart Notes",
                                            initialSubject = chapter.subject.displayName,
                                            initialChapter = "Ch ${chapter.chapterNumber}: ${chapter.title}",
                                            initialMethod = chapter.suggestedMethod,
                                            initialCue = chapter.pyqTrends.joinToString("\n") { "• ${it.topicName} (${it.typicalMarks})" },
                                            initialMain = chapter.cruxPoints.joinToString("\n\n") { "${it.pointNumber}. ${it.title}:\n${it.description}" },
                                            initialSummary = chapter.summary,
                                            initialTags = "#${chapter.subject.displayName}, #Class10, #NCERT"
                                        )
                                    },
                                    onNavigateToVideos = { viewModel.selectTab(AppTab.VIDEOS) },
                                    onNavigateToAiTutor = { viewModel.selectTab(AppTab.AI_TUTOR) },
                                    onNavigateToNotes = { viewModel.selectTab(AppTab.NOTEBOOK) },
                                    onNavigateToFlashcards = { viewModel.selectTab(AppTab.FLASHCARDS) },
                                    onNavigateToPapers = { viewModel.selectTab(AppTab.TEST_PAPERS) },
                                    onNavigateToTricks = { viewModel.selectTab(AppTab.EXAM_TRICKS) },
                                    onNavigateToNcert = { viewModel.selectTab(AppTab.NCERT) },
                                    onOpenLiteInfo = { viewModel.openLiteModeInfoDialog() }
                                )
                            }

                            AppTab.AI_TUTOR -> {
                                AiTutorChatScreen(
                                    state = state,
                                    onSendMessage = { viewModel.sendAiMessage(it) },
                                    onSetPersona = { viewModel.setAiPersona(it) },
                                    onToggleSearchGrounding = { viewModel.toggleAiSearchGrounding() },
                                    onInputTextChange = { viewModel.setAiInputText(it) },
                                    onClearChat = { viewModel.clearAiChat() },
                                    onRetry = { viewModel.retryLastAiMessage() },
                                    onSaveToNotes = { title, content ->
                                        viewModel.openNewNoteEditor(
                                            initialTitle = title,
                                            initialSubject = state.selectedSubject?.displayName ?: state.aiContextChapter?.subject?.displayName ?: "General",
                                            initialChapter = state.aiContextChapter?.title ?: "AI Tutor Solutions",
                                            initialMethod = NoteMethodType.CORNELL,
                                            initialCue = "Key Concepts / Cues:\n" + content.lines().filter { it.startsWith("•") || it.startsWith("-") || it.startsWith("1.") || it.startsWith("2.") }.take(6).joinToString("\n"),
                                            initialMain = content,
                                            initialSummary = "Quick Summary / Formula:\n" + content.lines().takeLast(3).joinToString("\n"),
                                            initialTags = "#${state.selectedBoard.shortName}, #${state.selectedGrade.displayName.replace(" ", "")}, #VidyaAI"
                                        )
                                    },
                                    onClearChapterContext = { viewModel.selectChapter(null) }
                                )
                            }

                            AppTab.VIDEOS -> {
                                VideoLecturesScreen(
                                    state = state,
                                    onFilterSubject = { viewModel.setVideoFilterSubject(it) },
                                    onFilterCategory = { viewModel.setVideoFilterCategory(it) },
                                    onSearchQueryChange = { viewModel.setVideoSearchQuery(it) },
                                    onToggleSaveVideo = { viewModel.toggleSaveVideo(it) },
                                    onSelectGrade = { viewModel.setGrade(it) },
                                    onCreateNoteFromVideo = { video ->
                                        viewModel.openNewNoteEditor(
                                            initialTitle = "${video.title} [Lecture Notes]",
                                            initialSubject = video.subject.displayName,
                                            initialChapter = video.chapterTitle,
                                            initialMethod = NoteMethodType.CORNELL,
                                            initialCue = "Educator: ${video.channelName}\nDuration: ${video.durationText}\nCategory: ${video.category.displayName}\nKey Topics:\n" + video.keyTopicsCovered.joinToString("\n") { "• $it" },
                                            initialMain = "LECTURE NOTES:\n• Watch link: ${video.youtubeVideoUrl}\n\n1. Overview & Fundamentals:\n${video.description}\n\n2. Key Derivations / Numericals / Concepts Solved:\n\n\n3. Formulae & Definitions Mentioned:\n",
                                            initialSummary = "Quick Takeaway / Exam Formulae:\n${video.recommendedFor}",
                                            initialTags = "#${video.subject.displayName}, #${video.grade.displayName.replace(" ", "")}, #YouTubeLecture"
                                        )
                                    }
                                )
                            }

                            AppTab.TEST_PAPERS -> {
                                TestPaperScreen(
                                    state = state,
                                    onSelectPaper = { viewModel.selectTestPaper(it) },
                                    onFilterSubject = { viewModel.setTestPaperFilterSubject(it) },
                                    onSelectMcqOption = { qId, opt -> viewModel.selectMcqOption(qId, opt) },
                                    onToggleMarkingScheme = { qId -> viewModel.toggleMarkingScheme(qId) },
                                    onSetSelfScore = { qId, score -> viewModel.setQuestionSelfScore(qId, score) },
                                    onToggleTimer = { viewModel.toggleExamTimer() },
                                    onResetTimer = { viewModel.resetExamTimer() },
                                    onResetProgress = { paperId -> viewModel.resetTestPaperProgress(paperId) },
                                    onCreateNoteFromQuestion = { question, paper ->
                                        viewModel.openNewNoteEditor(
                                            initialTitle = "Q${question.questionNumber}: ${question.topicTag}",
                                            initialSubject = paper.subject.displayName,
                                            initialChapter = paper.title,
                                            initialMethod = NoteMethodType.CORNELL,
                                            initialCue = "CBSE Marking Steps:\n" + question.cbseMarkingScheme.joinToString("\n") { "• ${it.stepDescription} (${it.marksAllocated})" },
                                            initialMain = "QUESTION:\n${question.questionText}\n\nMODEL SOLUTION:\n${question.modelAnswer}",
                                            initialSummary = "Topper Tip: ${question.topperTip}\nCommon Pitfall: ${question.commonPitfall}",
                                            initialTags = "#${paper.subject.displayName}, #BoardExam, #ModelPaper"
                                        )
                                    },
                                    onOpenGenerateDialog = { viewModel.openTestPaperGenerateDialog() },
                                    onCloseGenerateDialog = { viewModel.closeTestPaperGenerateDialog() },
                                    onGeneratePaper = { request -> viewModel.generateTestPaper(request) },
                                    onExportPdf = { paper -> viewModel.openPdfExportDialog(paper) },
                                    onClosePdfExportDialog = { viewModel.closePdfExportDialog() }
                                )
                            }

                            AppTab.NOTE_METHODS -> {
                                NoteMethodsScreen(
                                    onUseMethod = { methodType ->
                                        val sample = com.example.data.NoteMethodsRepository.getMethodByType(methodType)
                                        viewModel.openNewNoteEditor(
                                            initialTitle = sample.sampleTitle,
                                            initialSubject = sample.bestForSubjects.firstOrNull()?.displayName ?: "Science",
                                            initialChapter = "Sample Masterclass",
                                            initialMethod = methodType,
                                            initialCue = sample.sampleCue,
                                            initialMain = sample.sampleMainContent,
                                            initialSummary = sample.sampleSummary,
                                            initialTags = "#${methodType.name}, #SmartNotes"
                                        )
                                    }
                                )
                            }

                            AppTab.NOTEBOOK -> {
                                NotebookScreen(
                                    state = state,
                                    onNoteClick = { viewModel.editExistingNote(it) },
                                    onTogglePin = { viewModel.togglePinNote(it) },
                                    onDeleteNote = { viewModel.deleteNote(it) },
                                    onSubjectFilterChange = { viewModel.setNoteFilterSubject(it) },
                                    onToggleRecallMode = { viewModel.toggleCoverRecallMode() },
                                    onAddNewNote = { viewModel.openNewNoteEditor() },
                                    onGenerateCrux = { viewModel.generateNoteCruxForExistingNote(it) }
                                )
                            }

                            AppTab.EXAM_TRICKS -> {
                                ExamTricksScreen(
                                    state = state,
                                    onCalculateVedicSquare = { viewModel.calculateVedicSquare(it) },
                                    onCalculateVedicBase100 = { n1, n2 -> viewModel.calculateVedicBase100(n1, n2) }
                                )
                            }

                            AppTab.FLASHCARDS -> {
                                FlashcardsScreen(
                                    state = state,
                                    onToggleMastery = { id, currentStatus -> viewModel.toggleFlashcardMastery(id, currentStatus) },
                                    onDeleteFlashcard = { viewModel.deleteFlashcard(it) },
                                    onCreateFlashcard = { subj, chap, q, a, mnem ->
                                        viewModel.createFlashcardFromNote(subj, chap, q, a, mnem)
                                    }
                                )
                            }

                            AppTab.NCERT -> {
                                NcertFolderScreen(
                                    state = state,
                                    onSelectGrade = { viewModel.setGrade(it) },
                                    onSelectBook = { viewModel.selectNcertBook(it) },
                                    onSearchQueryChange = { viewModel.setNcertSearchQuery(it) },
                                    onFilterSubject = { viewModel.setNcertFilterSubject(it) },
                                    onCreateNoteFromChapter = { book, chapter ->
                                        val detailedNotes = NcertNotesProvider.getDetailedNotes(book, chapter)
                                        val cueText = buildString {
                                            appendLine("NCERT KEY TOPICS:")
                                            chapter.keyTopics.forEach { appendLine("• $it") }
                                            if (detailedNotes.importantDefinitions.isNotEmpty()) {
                                                appendLine()
                                                appendLine("KEY DEFINITIONS:")
                                                detailedNotes.importantDefinitions.forEach { (t, d) -> appendLine("• $t: $d") }
                                            }
                                        }
                                        val mainText = buildString {
                                            appendLine("NCERT CHAPTER OVERVIEW:")
                                            appendLine(detailedNotes.overview)
                                            appendLine()
                                            appendLine("DETAILED CONCEPTS & THEORY:")
                                            detailedNotes.keyConcepts.forEach { c ->
                                                appendLine(c.title)
                                                appendLine(c.explanation)
                                                c.keyPoints.forEach { p -> appendLine("  - $p") }
                                                appendLine()
                                            }
                                            if (detailedNotes.keyFormulasOrLaws.isNotEmpty()) {
                                                appendLine("ESSENTIAL FORMULAS, LAWS & TIMELINES:")
                                                detailedNotes.keyFormulasOrLaws.forEach { f -> appendLine("• $f") }
                                                appendLine()
                                            }
                                            if (detailedNotes.ncertQuestionsAndAnswers.isNotEmpty()) {
                                                appendLine("NCERT EXERCISE MODEL Q&A:")
                                                detailedNotes.ncertQuestionsAndAnswers.forEach { qna ->
                                                    appendLine("Q: ${qna.question}")
                                                    appendLine("A: ${qna.answer}")
                                                    appendLine()
                                                }
                                            }
                                        }
                                        val summaryText = buildString {
                                            appendLine("EXAM REVISION & BOARD POINTERS:")
                                            detailedNotes.examPointers.forEach { appendLine("• $it") }
                                        }

                                        viewModel.openNewNoteEditor(
                                            initialTitle = "${chapter.title} (Ch ${chapter.chapterNumber})",
                                            initialSubject = book.subject.displayName,
                                            initialChapter = "${book.title} - Ch ${chapter.chapterNumber}",
                                            initialMethod = NoteMethodType.CORNELL,
                                            initialCue = cueText,
                                            initialMain = mainText,
                                            initialSummary = summaryText,
                                            initialTags = "#NCERT, #${book.grade.displayName.replace(" ", "")}, #${book.subject.displayName}"
                                        )
                                    },
                                    onAskAiTutor = { book, chapter ->
                                        viewModel.selectTab(AppTab.AI_TUTOR)
                                        viewModel.setAiInputText("Please explain NCERT ${book.title} Chapter ${chapter.chapterNumber}: '${chapter.title}' (${chapter.hindiTitle}) in detail. What are the core concepts, derivations/mechanisms, and most frequent CBSE exam questions from this chapter?")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (state.showLiteModeInfoDialog) {
        LiteModeInfoDialog(
            isLiteMode = state.isLiteMode,
            dataSavedMb = state.dataSavedMegabytes,
            onToggleLiteMode = { viewModel.toggleLiteMode() },
            onDismiss = { viewModel.closeLiteModeInfoDialog() }
        )
    }

    if (state.activeNoteCruxSummary != null && state.noteCruxTargetNoteId != null) {
        NoteCruxSummaryDialog(
            summary = state.activeNoteCruxSummary!!,
            onApplyToSummary = { summaryText ->
                viewModel.applyCruxToTargetNote(summaryText)
            },
            onApplySummaryAndCues = { summaryText, cuesText ->
                viewModel.applyCruxToTargetNote(summaryText, cuesText)
            },
            onDismiss = { viewModel.dismissNoteCruxDialog() }
        )
    }
}
