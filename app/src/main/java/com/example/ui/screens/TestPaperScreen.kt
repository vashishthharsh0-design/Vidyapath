package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.TestPaperRepository
import com.example.model.*
import com.example.ui.components.PdfExportDialog
import com.example.ui.components.TestPaperGenerateDialog
import com.example.ui.theme.*
import com.example.viewmodel.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestPaperScreen(
    state: MainUiState,
    onSelectPaper: (TestPaperItem?) -> Unit,
    onFilterSubject: (SubjectType?) -> Unit,
    onSelectMcqOption: (questionId: String, optionIndex: Int) -> Unit,
    onToggleMarkingScheme: (questionId: String) -> Unit,
    onSetSelfScore: (questionId: String, marks: Int) -> Unit,
    onToggleTimer: () -> Unit,
    onResetTimer: () -> Unit,
    onResetProgress: (String) -> Unit,
    onCreateNoteFromQuestion: (TestQuestion, TestPaperItem) -> Unit,
    onOpenGenerateDialog: () -> Unit,
    onCloseGenerateDialog: () -> Unit,
    onGeneratePaper: (PaperGenerationRequest) -> Unit,
    onExportPdf: (TestPaperItem) -> Unit,
    onClosePdfExportDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (state.selectedTestPaper != null) {
            TestPaperDetailView(
                paper = state.selectedTestPaper,
                state = state,
                onBack = { onSelectPaper(null) },
                onSelectMcqOption = onSelectMcqOption,
                onToggleMarkingScheme = onToggleMarkingScheme,
                onSetSelfScore = onSetSelfScore,
                onToggleTimer = onToggleTimer,
                onResetTimer = onResetTimer,
                onResetProgress = { onResetProgress(state.selectedTestPaper.id) },
                onCreateNote = { q -> onCreateNoteFromQuestion(q, state.selectedTestPaper) },
                onExportPdf = onExportPdf,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            TestPaperListView(
                state = state,
                onSelectPaper = onSelectPaper,
                onFilterSubject = onFilterSubject,
                onOpenGenerateDialog = onOpenGenerateDialog,
                onExportPdf = onExportPdf,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Test Paper Generation Dialog
        if (state.showTestPaperGenerateDialog) {
            TestPaperGenerateDialog(
                initialGrade = state.selectedGrade,
                initialBoard = state.selectedBoard,
                initialSubject = state.selectedSubject,
                availableSubjects = SubjectType.values().toList(),
                isGenerating = state.isGeneratingTestPaper,
                onDismiss = onCloseGenerateDialog,
                onGenerate = onGeneratePaper
            )
        }

        // PDF Export & Print Dialog
        if (state.paperForPdfExport != null) {
            PdfExportDialog(
                paper = state.paperForPdfExport,
                onDismiss = onClosePdfExportDialog
            )
        }
    }
}

@Composable
fun TestPaperListView(
    state: MainUiState,
    onSelectPaper: (TestPaperItem) -> Unit,
    onFilterSubject: (SubjectType?) -> Unit,
    onOpenGenerateDialog: () -> Unit,
    onExportPdf: (TestPaperItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val staticPapers = TestPaperRepository.getTestPapersBySubject(state.testPaperFilterSubject)
    val customFiltered = state.customTestPapers.filter { state.testPaperFilterSubject == null || it.subject == state.testPaperFilterSubject }
    val papers = customFiltered + staticPapers

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 80.dp)
    ) {
        // Hero Card with Pattern Generator Callout
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("test_papers_hero_card")
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(PrimaryNavy, PeacockIndigo)
                            )
                        )
                        .padding(20.dp)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Surface(
                                color = SaffronPrimary,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "OFFICIAL BOARD BLUEPRINTS",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.AssignmentTurnedIn,
                                contentDescription = null,
                                tint = Color(0xFFFFD54F),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "CBSE & ICSE Board Mock Papers",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Practice standard 80M / 40M / 25M question papers strictly based on official blueprints with step marking schemes, topper solutions, and A4 printable PDF exports.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFF1F5F9)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Generator Action Card Button
                        Button(
                            onClick = onOpenGenerateDialog,
                            colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("hero_generate_paper_btn")
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Generate Paper as per Pattern", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Subject Filters
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Filter Papers by Subject",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(end = 8.dp)
                ) {
                    item {
                        FilterChip(
                            selected = state.testPaperFilterSubject == null,
                            onClick = { onFilterSubject(null) },
                            label = { Text("All Papers (${papers.size})") },
                            leadingIcon = if (state.testPaperFilterSubject == null) {
                                { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null,
                            modifier = Modifier.testTag("filter_paper_all")
                        )
                    }
                    val subjects = listOf(
                        SubjectType.ACCOUNTANCY,
                        SubjectType.SCIENCE_GENERAL,
                        SubjectType.MATHEMATICS,
                        SubjectType.PHYSICS,
                        SubjectType.ECONOMICS,
                        SubjectType.BUSINESS_STUDIES
                    )
                    items(subjects) { subj ->
                        FilterChip(
                            selected = state.testPaperFilterSubject == subj,
                            onClick = { onFilterSubject(subj) },
                            label = { Text(subj.displayName) },
                            leadingIcon = if (state.testPaperFilterSubject == subj) {
                                { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null,
                            modifier = Modifier.testTag("filter_paper_${subj.name.lowercase()}")
                        )
                    }
                }
            }
        }

        // Papers List
        items(papers) { paper ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectPaper(paper) }
                    .testTag("test_paper_item_${paper.id}")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            color = PeacockIndigoLight,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "${paper.board.shortName} • ${paper.grade.displayName}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = PeacockIndigo,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                        Surface(
                            color = SaffronLight,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "Max Marks: ${paper.maxMarks}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = SaffronPrimary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = paper.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = paper.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${paper.timeAllowedMinutes / 60} Hours (${paper.timeAllowedMinutes} Mins)",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.width(12.dp))
                            Icon(
                                imageVector = Icons.Default.FormatListNumbered,
                                contentDescription = null,
                                tint = EmeraldGreen,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            val totalQ = paper.sections.sumOf { it.questions.size }
                            Text(
                                text = "$totalQ Sample Questions",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = { onExportPdf(paper) },
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                                modifier = Modifier.testTag("pdf_btn_${paper.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PictureAsPdf,
                                    contentDescription = null,
                                    tint = Color(0xFFDC2626),
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "PDF",
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }

                            Button(
                                onClick = { onSelectPaper(paper) },
                                colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = "Start Test",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(14.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TestPaperDetailView(
    paper: TestPaperItem,
    state: MainUiState,
    onBack: () -> Unit,
    onSelectMcqOption: (String, Int) -> Unit,
    onToggleMarkingScheme: (String) -> Unit,
    onSetSelfScore: (String, Int) -> Unit,
    onToggleTimer: () -> Unit,
    onResetTimer: () -> Unit,
    onResetProgress: () -> Unit,
    onCreateNote: (TestQuestion) -> Unit,
    onExportPdf: (TestPaperItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var showInstructions by remember { mutableStateOf(false) }
    var selectedSectionIndex by remember { mutableIntStateOf(0) } // 0 = All

    val allQuestions = paper.sections.flatMap { it.questions }
    val totalPaperMarks = paper.maxMarks
    val currentEvaluatedMarks = allQuestions.sumOf { q -> state.selfEvaluatedMarks[q.id] ?: 0 }

    // Format timer
    val hours = state.examTimeSecondsRemaining / 3600
    val minutes = (state.examTimeSecondsRemaining % 3600) / 60
    val seconds = state.examTimeSecondsRemaining % 60
    val timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 100.dp)
    ) {
        // Top Navigation Bar inside view
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onBack,
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    modifier = Modifier.testTag("test_paper_back_btn")
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("All Papers", style = MaterialTheme.typography.labelSmall)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedButton(
                        onClick = { onExportPdf(paper) },
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("export_pdf_detail_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.PictureAsPdf,
                            contentDescription = null,
                            tint = Color(0xFFDC2626),
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Export PDF / Print", style = MaterialTheme.typography.labelSmall)
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    TextButton(onClick = onResetProgress) {
                        Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Reset", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }

        // Exam Live Timer & Score Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (state.examTimeSecondsRemaining < 900) Color(0xFFFFF1F2) else MaterialTheme.colorScheme.surfaceVariant
                ),
                border = BorderStroke(1.dp, if (state.isExamTimerRunning) SaffronPrimary else Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("exam_timer_card")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = paper.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "CBSE Board Mock Examination • ${paper.timeAllowedMinutes / 60}h Duration",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Exam Timer Control
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = if (state.isExamTimerRunning) SaffronPrimary else MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp),
                                shadowElevation = 1.dp
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Timer,
                                        contentDescription = null,
                                        tint = if (state.isExamTimerRunning) Color.White else MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = timeFormatted,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Monospace,
                                        color = if (state.isExamTimerRunning) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            IconButton(
                                onClick = onToggleTimer,
                                modifier = Modifier
                                    .size(32.dp)
                                    .testTag("toggle_exam_timer_btn")
                            ) {
                                Icon(
                                    imageVector = if (state.isExamTimerRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = if (state.isExamTimerRunning) "Pause Timer" else "Start Timer",
                                    tint = SaffronPrimary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Self Evaluation Score Bar
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Self-Evaluated Score: $currentEvaluatedMarks / $totalPaperMarks Marks",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        val pct = if (totalPaperMarks > 0) ((currentEvaluatedMarks.toFloat() / totalPaperMarks) * 100).toInt() else 0
                        Surface(
                            color = if (pct >= 75) EmeraldGreenLight else SaffronLight,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "$pct%",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (pct >= 75) EmeraldGreen else SaffronPrimary,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { if (totalPaperMarks > 0) currentEvaluatedMarks.toFloat() / totalPaperMarks else 0f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = EmeraldGreen,
                        trackColor = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }

        // CBSE General Instructions Accordion
        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showInstructions = !showInstructions }
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = SaffronPrimary, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "CBSE Official General Instructions",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Icon(
                            imageVector = if (showInstructions) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null
                        )
                    }

                    AnimatedVisibility(visible = showInstructions) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.padding(top = 10.dp)
                        ) {
                            paper.generalInstructions.forEach { inst ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text("• ", fontWeight = FontWeight.Bold, color = SaffronPrimary)
                                    Text(
                                        text = inst,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Section Filter Tabs
        item {
            val sectionsList = listOf("All Sections") + paper.sections.map { it.sectionName.take(18) + "..." }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(end = 8.dp)
            ) {
                items(sectionsList.size) { idx ->
                    FilterChip(
                        selected = selectedSectionIndex == idx,
                        onClick = { selectedSectionIndex = idx },
                        label = { Text(sectionsList[idx]) },
                        leadingIcon = if (selectedSectionIndex == idx) {
                            { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(14.dp)) }
                        } else null
                    )
                }
            }
        }

        // Questions List
        val filteredSections = if (selectedSectionIndex == 0) {
            paper.sections
        } else {
            listOf(paper.sections[selectedSectionIndex - 1])
        }

        filteredSections.forEach { section ->
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = section.sectionName,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = PeacockIndigo
                        )
                        Text(
                            text = section.instructions,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            items(section.questions) { question ->
                QuestionItemCard(
                    question = question,
                    selectedOption = state.selectedMcqOptions[question.id],
                    isMarkingSchemeRevealed = state.revealedMarkingSchemes.contains(question.id),
                    selfScore = state.selfEvaluatedMarks[question.id],
                    onSelectOption = { onSelectMcqOption(question.id, it) },
                    onToggleMarkingScheme = { onToggleMarkingScheme(question.id) },
                    onSetSelfScore = { onSetSelfScore(question.id, it) },
                    onCreateNote = { onCreateNote(question) }
                )
            }
        }
    }
}

@Composable
fun QuestionItemCard(
    question: TestQuestion,
    selectedOption: Int?,
    isMarkingSchemeRevealed: Boolean,
    selfScore: Int?,
    onSelectOption: (Int) -> Unit,
    onToggleMarkingScheme: () -> Unit,
    onSetSelfScore: (Int) -> Unit,
    onCreateNote: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .testTag("question_card_${question.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Q Number, Type, Marks, Topic Tag
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(PeacockIndigo),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Q${question.questionNumber}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = question.questionType.label,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Surface(
                    color = SaffronLight,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "[ ${question.marks} ${if (question.marks > 1) "Marks" else "Mark"} ]",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = SaffronPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Topic Tag
            Text(
                text = "Topic: ${question.topicTag}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Case Passage if any
            if (!question.casePassage.isNullOrBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(10.dp)
                ) {
                    Text(
                        text = question.casePassage,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Question Text
            Text(
                text = question.questionText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            // MCQ Options
            if (question.options != null && question.correctOptionIndex != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    question.options.forEachIndexed { optIndex, optionText ->
                        val isSelected = selectedOption == optIndex
                        val isCorrect = optIndex == question.correctOptionIndex
                        val isAnswered = selectedOption != null

                        val containerColor = when {
                            !isAnswered -> MaterialTheme.colorScheme.surface
                            isSelected && isCorrect -> EmeraldGreenLight
                            isSelected && !isCorrect -> Color(0xFFFFEBEE)
                            isCorrect -> EmeraldGreenLight.copy(alpha = 0.5f)
                            else -> MaterialTheme.colorScheme.surface
                        }

                        val borderColor = when {
                            !isAnswered -> if (isSelected) SaffronPrimary else MaterialTheme.colorScheme.outlineVariant
                            isSelected && isCorrect -> EmeraldGreen
                            isSelected && !isCorrect -> Color(0xFFD32F2F)
                            isCorrect -> EmeraldGreen
                            else -> MaterialTheme.colorScheme.outlineVariant
                        }

                        Surface(
                            onClick = {
                                if (selectedOption == null) {
                                    onSelectOption(optIndex)
                                    if (optIndex == question.correctOptionIndex) {
                                        onSetSelfScore(question.marks)
                                    } else {
                                        onSetSelfScore(0)
                                    }
                                }
                            },
                            shape = RoundedCornerShape(10.dp),
                            color = containerColor,
                            border = BorderStroke(1.dp, borderColor),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(12.dp)
                            ) {
                                val optionLetter = ('A'.code + optIndex).toChar()
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isAnswered && isCorrect) EmeraldGreen
                                            else if (isAnswered && isSelected) Color(0xFFD32F2F)
                                            else MaterialTheme.colorScheme.surfaceVariant
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$optionLetter",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isAnswered && (isCorrect || isSelected)) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = optionText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = BorderSubtle, thickness = 0.8.dp)
            Spacer(modifier = Modifier.height(10.dp))

            // Action Row: Toggle Marking Scheme & Create Note
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onToggleMarkingScheme,
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (isMarkingSchemeRevealed) SaffronPrimary else PeacockIndigo
                    )
                ) {
                    Icon(
                        imageVector = if (isMarkingSchemeRevealed) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isMarkingSchemeRevealed) "Hide Marking Scheme" else "CBSE Marking Scheme & Solution",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                IconButton(
                    onClick = onCreateNote,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.NoteAdd,
                        contentDescription = "Save to Notes",
                        tint = PeacockIndigo,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // CBSE Step-by-Step Marking Scheme & Topper Answer Accordion
            AnimatedVisibility(visible = isMarkingSchemeRevealed) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .animateContentSize()
                ) {
                    // CBSE Step Breakdown
                    Surface(
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Checklist, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Strict CBSE Step-by-Step Marks Allotment:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldGreen
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            question.cbseMarkingScheme.forEach { step ->
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 3.dp)
                                ) {
                                    Text(
                                        text = "• ${step.stepDescription}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Surface(
                                        color = EmeraldGreenLight,
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = step.marksAllocated,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = EmeraldGreen,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Model Answer
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Model 100/100 Solution:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = question.modelAnswer,
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // Topper Tip & Pitfall
                    if (question.topperTip.isNotBlank()) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(AmberGoldLight)
                                .padding(10.dp)
                        ) {
                            Text("💡 ", fontSize = 14.sp)
                            Text(
                                text = "Topper's Secret: ${question.topperTip}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF78350F)
                            )
                        }
                    }

                    if (question.commonPitfall.isNotBlank()) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFFFEBEE))
                                .padding(10.dp)
                        ) {
                            Text("⚠️ ", fontSize = 14.sp)
                            Text(
                                text = "Examiner Trap Warning: ${question.commonPitfall}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFB71C1C)
                            )
                        }
                    }

                    // Self Evaluation Marks Slider / Selector
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, BorderSubtle),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Self-Score this Question (Out of ${question.marks}):",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                for (mark in 0..question.marks) {
                                    val isScoreSelected = (selfScore ?: -1) == mark
                                    OutlinedButton(
                                        onClick = { onSetSelfScore(mark) },
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            containerColor = if (isScoreSelected) EmeraldGreen else Color.Transparent,
                                            contentColor = if (isScoreSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                        ),
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                                        shape = RoundedCornerShape(6.dp),
                                        modifier = Modifier.height(30.dp)
                                    ) {
                                        Text("$mark", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
