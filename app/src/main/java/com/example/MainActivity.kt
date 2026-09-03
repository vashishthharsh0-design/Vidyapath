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
import com.example.model.NoteMethodType
import com.example.ui.components.AppBottomNav
import com.example.ui.components.AppTopBar
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
    BackHandler(enabled = state.showNoteEditor || state.selectedChapter != null || state.selectedMethodDetail != null || state.selectedTestPaper != null) {
        if (state.showNoteEditor) {
            viewModel.closeNoteEditor()
        } else if (state.selectedTestPaper != null) {
            viewModel.selectTestPaper(null)
        } else if (state.selectedChapter != null) {
            viewModel.selectChapter(null)
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
            }
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
                    onBackClick = if (state.selectedChapter != null) {
                        { viewModel.selectChapter(null) }
                    } else null
                )
            },
            bottomBar = {
                if (state.selectedChapter == null) {
                    AppBottomNav(
                        selectedTab = state.activeTab,
                        onTabSelected = { viewModel.selectTab(it) }
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
                                    onNavigateToVideos = { viewModel.selectTab(AppTab.VIDEOS) }
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
                                    }
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
                                    onAddNewNote = { viewModel.openNewNoteEditor() }
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
                        }
                    }
                }
            }
        }
    }
}
