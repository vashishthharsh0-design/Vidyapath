package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.NoteCruxSummary
import com.example.model.NoteEntity
import com.example.model.NoteMethodType
import com.example.ui.components.NoteCruxSummaryDialog
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesEditorScreen(
    initialNote: NoteEntity,
    onSave: (NoteEntity) -> Unit,
    onCancel: () -> Unit,
    onConvertToFlashcard: (question: String, answer: String, subject: String, chapter: String, mnemonic: String) -> Unit,
    isSummarizingCrux: Boolean = false,
    activeCruxSummary: NoteCruxSummary? = null,
    onGenerateCrux: ((title: String, subject: String, chapter: String, mainContent: String, cues: String) -> Unit)? = null,
    onDismissCruxDialog: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf(initialNote.title) }
    var subject by remember { mutableStateOf(initialNote.subject) }
    var chapter by remember { mutableStateOf(initialNote.chapter) }
    var methodTypeStr by remember { mutableStateOf(initialNote.methodType) }
    var cues by remember { mutableStateOf(initialNote.cueOrKeywordColumn) }
    var mainContent by remember { mutableStateOf(initialNote.mainContent) }
    var summary by remember { mutableStateOf(initialNote.summaryOrConclusion) }
    var tags by remember { mutableStateOf(initialNote.tags) }
    var isPinned by remember { mutableStateOf(initialNote.isPinned) }

    var showFlashcardSuccessDialog by remember { mutableStateOf(false) }

    val currentMethod = NoteMethodType.values().firstOrNull { it.name == methodTypeStr } ?: NoteMethodType.CORNELL

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (initialNote.id == 0L) "New Smart Note" else "Edit Note",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onCancel,
                        modifier = Modifier.testTag("editor_cancel_button")
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            onGenerateCrux?.invoke(title, subject, chapter, mainContent, cues)
                        },
                        enabled = !isSummarizingCrux,
                        modifier = Modifier.testTag("editor_top_crux_button")
                    ) {
                        if (isSummarizingCrux) {
                            CircularProgressIndicator(
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(20.dp),
                                color = SaffronPrimary
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "Generate AI Crux",
                                tint = SaffronPrimary
                            )
                        }
                    }

                    IconButton(
                        onClick = { isPinned = !isPinned },
                        modifier = Modifier.testTag("editor_pin_button")
                    ) {
                        Icon(
                            imageVector = if (isPinned) Icons.Default.PushPin else Icons.Default.PushPin,
                            contentDescription = "Pin Note",
                            tint = if (isPinned) SaffronPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    FilledTonalButton(
                        onClick = {
                            if (title.isBlank()) {
                                title = "${subject}: ${chapter.ifBlank { "Smart Note" }}"
                            }
                            val updated = initialNote.copy(
                                title = title,
                                subject = subject,
                                chapter = chapter,
                                methodType = methodTypeStr,
                                cueOrKeywordColumn = cues,
                                mainContent = mainContent,
                                summaryOrConclusion = summary,
                                tags = tags,
                                isPinned = isPinned
                            )
                            onSave(updated)
                        },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = SaffronPrimary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .testTag("editor_save_button")
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Save", fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Method Selection Pills
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Select Note Architecture:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                ScrollableTabRow(
                    selectedTabIndex = NoteMethodType.values().indexOf(currentMethod).coerceAtLeast(0),
                    edgePadding = 0.dp,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                ) {
                    NoteMethodType.values().forEach { method ->
                        Tab(
                            selected = methodTypeStr == method.name,
                            onClick = { methodTypeStr = method.name },
                            text = {
                                Text(
                                    text = method.title.split(" ").take(2).joinToString(" "),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = if (methodTypeStr == method.name) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            modifier = Modifier.testTag("editor_method_tab_${method.name.lowercase()}")
                        )
                    }
                }
            }

            // Quick Insertion Toolbar
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "⚡ Quick Smart Inserts:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = SaffronPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = {
                                mainContent += "\n\n🔤 MNEMONIC TRICK:\n• Sentence: \"...\"\n• Application: "
                            },
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("+ Mnemonic", style = MaterialTheme.typography.labelSmall)
                        }

                        OutlinedButton(
                            onClick = {
                                mainContent += "\n\n⚠️ 5-MARK BOARD PYQ ALERT:\n• Core Concept:\n• Common Trap to Avoid:\n• Equation/Diagram: "
                            },
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("+ 5-Mark Trap", style = MaterialTheme.typography.labelSmall)
                        }

                        OutlinedButton(
                            onClick = {
                                mainContent += "\n\n📐 FORMULA BOX:\n• Formula: \n• SI Units: \n• Condition: "
                            },
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("+ Formula", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }

            // AI Crux & Key Highlights Generator Banner
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SaffronLight),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("editor_ai_crux_banner")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(SaffronPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Gemini AI Crux & Highlights",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = SaffronDark
                            )
                            Text(
                                text = "Extract executive crux, formulas & Cornell recall cues",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            onGenerateCrux?.invoke(title, subject, chapter, mainContent, cues)
                        },
                        enabled = !isSummarizingCrux,
                        colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("editor_generate_crux_button")
                    ) {
                        if (isSummarizingCrux) {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Analyzing...", style = MaterialTheme.typography.labelSmall)
                        } else {
                            Icon(
                                imageVector = Icons.Default.Bolt,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Get Crux", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Metadata Row (Subject, Chapter, Title)
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Subject") },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("editor_subject_field")
                )
                OutlinedTextField(
                    value = chapter,
                    onValueChange = { chapter = it },
                    label = { Text("Chapter") },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1.5f)
                        .testTag("editor_chapter_field")
                )
            }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Note Title / Topic Heading") },
                placeholder = { Text("e.g., Photosynthesis 3-Pathways & Chlorophyll Mechanism") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("editor_title_field")
            )

            // Dynamic Inputs according to selected Method
            when (currentMethod) {
                NoteMethodType.CORNELL -> {
                    // Cues Box
                    OutlinedTextField(
                        value = cues,
                        onValueChange = { cues = it },
                        label = { Text("Left Column: Cues & Board Questions (1-Mark / 3-Mark)") },
                        placeholder = { Text("• What is the role of HCl in stomach?\n• Define Exothermic reaction with equation.\n• Why is respiration exothermic?") },
                        minLines = 3,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_cues_field")
                    )

                    // Main Content
                    OutlinedTextField(
                        value = mainContent,
                        onValueChange = { mainContent = it },
                        label = { Text("Right Column: Main Lecture & NCERT Notes") },
                        placeholder = { Text("Write detailed notes, equations, bullet points, and derivations here...") },
                        minLines = 8,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_main_content_field")
                    )

                    // Summary Box
                    OutlinedTextField(
                        value = summary,
                        onValueChange = { summary = it },
                        label = { Text("Bottom Box: 2-3 Line Summary / Core Crux") },
                        placeholder = { Text("Summarize the entire page in your own words for active recall...") },
                        minLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_summary_field")
                    )
                }

                NoteMethodType.FEYNMAN -> {
                    OutlinedTextField(
                        value = cues,
                        onValueChange = { cues = it },
                        label = { Text("Target Complex Concept & Analogy Anchor") },
                        placeholder = { Text("Concept: Lenz's Law | Analogy: 'Stubborn Child opposing push/pull'") },
                        minLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_cues_field")
                    )

                    OutlinedTextField(
                        value = mainContent,
                        onValueChange = { mainContent = it },
                        label = { Text("ELI5 (Explain Like I'm 10 - Plain Language Explanation)") },
                        placeholder = { Text("Explain without scientific jargon. Use simple real-life Indian analogies (chai, cricket, bicycle)...") },
                        minLines = 8,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_main_content_field")
                    )

                    OutlinedTextField(
                        value = summary,
                        onValueChange = { summary = it },
                        label = { Text("Identified Knowledge Gaps & NCERT Refinement") },
                        placeholder = { Text("Where did you get stuck? What equation explains the physics behind this?") },
                        minLines = 3,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_summary_field")
                    )
                }

                NoteMethodType.QEC -> {
                    OutlinedTextField(
                        value = cues,
                        onValueChange = { cues = it },
                        label = { Text("The Big Question (5-Marker Board Question)") },
                        placeholder = { Text("e.g. Explain 5 factors responsible for the launch of Non-Cooperation Movement.") },
                        minLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_cues_field")
                    )

                    OutlinedTextField(
                        value = mainContent,
                        onValueChange = { mainContent = it },
                        label = { Text("Evidence (5 Distinct Numbered Sub-Headings with Facts)") },
                        placeholder = { Text("1. WW1 Economic Crisis:\n2. Rowlatt Act:\n3. Jallianwala Bagh Massacre:\n4. Khilafat Issue:\n5. Hind Swaraj Philosophy:") },
                        minLines = 8,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_main_content_field")
                    )

                    OutlinedTextField(
                        value = summary,
                        onValueChange = { summary = it },
                        label = { Text("Conclusion (Historical / Scientific Significance)") },
                        placeholder = { Text("2-line concluding impact...") },
                        minLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_summary_field")
                    )
                }

                else -> {
                    // Generic structured layout (Boxing, Flowchart, Mnemonics, Mindmap)
                    OutlinedTextField(
                        value = cues,
                        onValueChange = { cues = it },
                        label = { Text("Keywords / Central Nodes / Categories") },
                        placeholder = { Text("e.g., Box 1: Trig Ratios | Box 2: Identities | Box 3: Angles") },
                        minLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_cues_field")
                    )

                    OutlinedTextField(
                        value = mainContent,
                        onValueChange = { mainContent = it },
                        label = { Text("Body Content / Step Sequence / Boxes") },
                        placeholder = { Text("Write structured flowchart steps, boxes, or mnemonic sentences...") },
                        minLines = 8,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_main_content_field")
                    )

                    OutlinedTextField(
                        value = summary,
                        onValueChange = { summary = it },
                        label = { Text("Key Takeaways & Memory Triggers") },
                        placeholder = { Text("Final takeaway...") },
                        minLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("editor_summary_field")
                    )
                }
            }

            // Tags
            OutlinedTextField(
                value = tags,
                onValueChange = { tags = it },
                label = { Text("Tags (comma-separated)") },
                placeholder = { Text("#PYQ, #5Marks, #Formula, #VedicTrick") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Make Flashcard Action
            FilledTonalButton(
                onClick = {
                    val q = if (cues.isNotBlank()) cues else title
                    val a = if (mainContent.isNotBlank()) mainContent else summary
                    if (q.isNotBlank() && a.isNotBlank()) {
                        onConvertToFlashcard(q, a, subject, chapter, summary)
                        showFlashcardSuccessDialog = true
                    }
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = PeacockIndigoLight,
                    contentColor = PeacockIndigo
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("convert_to_flashcard_button")
            ) {
                Icon(Icons.Default.Style, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Save as Revision Flashcard for Active Recall", fontWeight = FontWeight.Bold)
            }
        }
    }

    if (showFlashcardSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showFlashcardSuccessDialog = false },
            title = { Text("Flashcard Created! 🎴") },
            text = { Text("This note has been added to your Active Recall Flashcards deck for daily spaced repetition practice.") },
            confirmButton = {
                TextButton(onClick = { showFlashcardSuccessDialog = false }) {
                    Text("OK", fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    if (activeCruxSummary != null) {
        NoteCruxSummaryDialog(
            summary = activeCruxSummary,
            onApplyToSummary = { newSummary ->
                summary = newSummary
                onDismissCruxDialog?.invoke()
            },
            onApplySummaryAndCues = { newSummary, newCues ->
                summary = newSummary
                cues = if (cues.isBlank()) newCues else "$cues\n\n$newCues"
                onDismissCruxDialog?.invoke()
            },
            onDismiss = {
                onDismissCruxDialog?.invoke()
            }
        )
    }
}
