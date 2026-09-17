package com.example.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.model.NoteEntity
import com.example.ui.theme.*
import com.example.viewmodel.MainUiState

@Composable
fun NotebookScreen(
    state: MainUiState,
    onNoteClick: (NoteEntity) -> Unit,
    onTogglePin: (Long) -> Unit,
    onDeleteNote: (NoteEntity) -> Unit,
    onSubjectFilterChange: (String) -> Unit,
    onToggleRecallMode: () -> Unit,
    onAddNewNote: () -> Unit,
    onGenerateCrux: (NoteEntity) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var noteToDelete by remember { mutableStateOf<NoteEntity?>(null) }

    val filteredNotes = state.userNotes.filter { note ->
        val matchesSubject = state.noteFilterSubject == "All" || note.subject.equals(state.noteFilterSubject, ignoreCase = true)
        val matchesSearch = state.searchQuery.isBlank() ||
                note.title.contains(state.searchQuery, ignoreCase = true) ||
                note.mainContent.contains(state.searchQuery, ignoreCase = true) ||
                note.tags.contains(state.searchQuery, ignoreCase = true) ||
                note.cueOrKeywordColumn.contains(state.searchQuery, ignoreCase = true)
        matchesSubject && matchesSearch
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNewNote,
                containerColor = SaffronPrimary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .testTag("add_new_note_fab")
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add New Smart Note")
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 100.dp)
        ) {
            // Recall Mode Banner
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (state.isCoverRecallModeActive) SaffronLight else MaterialTheme.colorScheme.surfaceVariant
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = if (state.isCoverRecallModeActive) Icons.Filled.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = null,
                                tint = if (state.isCoverRecallModeActive) SaffronPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(26.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = if (state.isCoverRecallModeActive) "Active Recall Mode: ON" else "Active Recall Mode: OFF",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (state.isCoverRecallModeActive) SaffronDark else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = if (state.isCoverRecallModeActive) "Notes are hidden. Test memory from Cues!" else "Tap to hide notes and test yourself using Cues.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Switch(
                            checked = state.isCoverRecallModeActive,
                            onCheckedChange = { onToggleRecallMode() },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = SaffronPrimary
                            ),
                            modifier = Modifier.testTag("active_recall_switch")
                        )
                    }
                }
            }

            // Subject Filter Row
            item {
                val subjects = listOf("All", "Science", "Physics", "Chemistry", "Biology", "Mathematics", "History", "Polity")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(end = 8.dp)
                ) {
                    items(subjects) { subj ->
                        FilterChip(
                            selected = state.noteFilterSubject == subj,
                            onClick = { onSubjectFilterChange(subj) },
                            label = { Text(subj) },
                            leadingIcon = if (state.noteFilterSubject == subj) {
                                { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null,
                            modifier = Modifier.testTag("notebook_filter_${subj.lowercase()}")
                        )
                    }
                }
            }

            // Notes Count
            item {
                Text(
                    text = "My Notes (${filteredNotes.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Empty State
            if (filteredNotes.isEmpty()) {
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(36.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(SaffronLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.EditNote,
                                    contentDescription = null,
                                    tint = SaffronPrimary,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "No Notes in this Section",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Tap '+' to create a note using Cornell, Feynman, Boxing, or Flowchart templates, or explore Syllabus to generate from NCERT chapters.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = onAddNewNote,
                                colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text("Create First Note", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            // List of Notes
            items(filteredNotes) { note ->
                UserNoteCard(
                    note = note,
                    isRecallCoverActive = state.isCoverRecallModeActive,
                    onClick = { onNoteClick(note) },
                    onTogglePin = { onTogglePin(note.id) },
                    onDelete = { noteToDelete = note },
                    onGenerateCrux = { onGenerateCrux(note) },
                    onShare = {
                        val shareText = buildString {
                            appendLine("📚 ${note.title}")
                            appendLine("🏛️ ${note.board} • ${note.grade} • ${note.subject} (Ch: ${note.chapter})")
                            appendLine("Architecture: ${note.methodType}")
                            appendLine()
                            if (note.cueOrKeywordColumn.isNotBlank()) {
                                appendLine("📌 CUES / QUESTIONS:")
                                appendLine(note.cueOrKeywordColumn)
                                appendLine()
                            }
                            appendLine("📝 MAIN NOTES:")
                            appendLine(note.mainContent)
                            appendLine()
                            if (note.summaryOrConclusion.isNotBlank()) {
                                appendLine("💡 SUMMARY / CRUX:")
                                appendLine(note.summaryOrConclusion)
                            }
                            appendLine()
                            appendLine("— Generated via VidyaNotes")
                        }
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, shareText)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Share Study Note"))
                    }
                )
            }
        }
    }

    if (noteToDelete != null) {
        AlertDialog(
            onDismissRequest = { noteToDelete = null },
            title = { Text("Delete Note?") },
            text = { Text("Are you sure you want to delete '${noteToDelete?.title}'? This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        noteToDelete?.let { onDeleteNote(it) }
                        noteToDelete = null
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { noteToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun UserNoteCard(
    note: NoteEntity,
    isRecallCoverActive: Boolean,
    onClick: () -> Unit,
    onTogglePin: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit,
    onGenerateCrux: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isRevealedLocally by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (isRecallCoverActive) {
                    isRevealedLocally = !isRevealedLocally
                } else {
                    onClick()
                }
            }
            .testTag("user_note_card_${note.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header Row: Subject, Method Badge, Pin, More Actions
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        color = PeacockIndigoLight,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = note.subject.ifBlank { "General" },
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
                            text = note.methodType,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = SaffronPrimary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onGenerateCrux,
                        modifier = Modifier
                            .size(32.dp)
                            .testTag("note_ai_crux_${note.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "AI Crux & Highlights",
                            tint = SaffronPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onTogglePin,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PushPin,
                            contentDescription = "Pin Note",
                            tint = if (note.isPinned) SaffronPrimary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onShare,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (note.chapter.isNotBlank()) {
                Text(
                    text = "Chapter: ${note.chapter}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // In Active Recall Mode
            if (isRecallCoverActive) {
                // Show Cues/Questions prominently
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(PeacockIndigoLight)
                        .padding(12.dp)
                ) {
                    Column {
                        Text(
                            text = "❓ ACTIVE RECALL CUES (Test yourself):",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = PeacockIndigo
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (note.cueOrKeywordColumn.isNotBlank()) note.cueOrKeywordColumn else "What are the core definitions & equations in: '${note.title}'?",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (isRevealedLocally) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(
                                text = "✅ REVEALED NOTES & ANSWER:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldGreen
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = note.mainContent,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                } else {
                    OutlinedButton(
                        onClick = { isRevealedLocally = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Tap to Reveal Answer")
                    }
                }
            } else {
                // Standard Display
                if (note.cueOrKeywordColumn.isNotBlank()) {
                    Text(
                        text = "• Cue: ${note.cueOrKeywordColumn.lines().first()}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = PeacockIndigo,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Text(
                    text = note.mainContent,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                if (note.summaryOrConclusion.isNotBlank()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "💡 Summary / Crux: ${note.summaryOrConclusion}",
                        style = MaterialTheme.typography.labelSmall,
                        color = EmeraldGreen,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                } else {
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SaffronLight)
                            .clickable { onGenerateCrux() }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = SaffronPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Generate Gemini Crux & Highlights",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaffronDark
                        )
                    }
                }
            }

            if (note.tags.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    note.tags.split(",").map { it.trim() }.filter { it.isNotEmpty() }.take(3).forEach { tag ->
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = if (tag.startsWith("#")) tag else "#$tag",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
