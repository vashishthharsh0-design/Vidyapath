package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.FlashcardEntity
import com.example.ui.theme.*
import com.example.viewmodel.MainUiState

@Composable
fun FlashcardsScreen(
    state: MainUiState,
    onToggleMastery: (Long, Boolean) -> Unit,
    onDeleteFlashcard: (FlashcardEntity) -> Unit,
    onCreateFlashcard: (subject: String, chapter: String, question: String, answer: String, mnemonic: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedSubjectFilter by remember { mutableStateOf("All") }
    var showOnlyLearning by remember { mutableStateOf(false) }
    var showAddCardDialog by remember { mutableStateOf(false) }

    val filteredCards = state.flashcards.filter { card ->
        val matchesSubject = selectedSubjectFilter == "All" || card.subject.equals(selectedSubjectFilter, ignoreCase = true)
        val matchesMastery = !showOnlyLearning || !card.isMastered
        matchesSubject && matchesMastery
    }

    val masteredCount = state.flashcards.count { it.isMastered }
    val totalCount = state.flashcards.size
    val masteryPercentage = if (totalCount > 0) ((masteredCount.toFloat() / totalCount) * 100).toInt() else 0

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddCardDialog = true },
                containerColor = PeacockIndigo,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(bottom = 60.dp)
                    .testTag("add_flashcard_fab")
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Flashcard")
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 100.dp)
        ) {
            // Mastery Progress Hero Card
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("flashcards_progress_card")
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    text = "Active Recall Spaced Repetition",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "$masteredCount of $totalCount Concepts Mastered ($masteryPercentage%)",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (masteryPercentage > 70) EmeraldGreenLight else SaffronLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$masteryPercentage%",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (masteryPercentage > 70) EmeraldGreen else SaffronPrimary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        LinearProgressIndicator(
                            progress = { if (totalCount > 0) masteredCount.toFloat() / totalCount else 0f },
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

            // Filters
            item {
                val subjects = listOf("All", "Science", "Physics", "Chemistry", "Biology", "Mathematics", "History")
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(end = 8.dp)
                    ) {
                        items(subjects) { subj ->
                            FilterChip(
                                selected = selectedSubjectFilter == subj,
                                onClick = { selectedSubjectFilter = subj },
                                label = { Text(subj) },
                                leadingIcon = if (selectedSubjectFilter == subj) {
                                    { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                                } else null
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Flashcards (${filteredCards.size})",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        FilterChip(
                            selected = showOnlyLearning,
                            onClick = { showOnlyLearning = !showOnlyLearning },
                            label = { Text("Needs Revision Only") },
                            leadingIcon = if (showOnlyLearning) {
                                { Icon(Icons.Default.FilterList, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null
                        )
                    }
                }
            }

            // Cards
            if (filteredCards.isEmpty()) {
                item {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = EmeraldGreen,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "All Caught Up!",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "No flashcards pending in this filter. Tap '+' to add new high-yield exam questions.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            items(filteredCards) { card ->
                FlashcardItem(
                    card = card,
                    onToggleMastery = { onToggleMastery(card.id, card.isMastered) },
                    onDelete = { onDeleteFlashcard(card) }
                )
            }
        }
    }

    if (showAddCardDialog) {
        AddFlashcardDialog(
            onDismiss = { showAddCardDialog = false },
            onAdd = { subj, chap, q, a, mnem ->
                onCreateFlashcard(subj, chap, q, a, mnem)
                showAddCardDialog = false
            }
        )
    }
}

@Composable
fun FlashcardItem(
    card: FlashcardEntity,
    onToggleMastery: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFlipped by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 350),
        label = "flashcard_flip"
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (card.isMastered) EmeraldGreenLight.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { isFlipped = !isFlipped }
            .testTag("flashcard_item_${card.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header Row: Subject, Chapter, Mastered Status
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = PeacockIndigoLight,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "${card.subject} • ${card.chapter}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = PeacockIndigo,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (card.isMastered) {
                        Surface(
                            color = EmeraldGreen,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "MASTERED",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    } else {
                        Surface(
                            color = AmberGoldLight,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "LEARNING",
                                style = MaterialTheme.typography.labelSmall,
                                color = AmberGold,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    IconButton(onClick = onDelete, modifier = Modifier.size(28.dp)) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.6f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Front or Back content
            if (!isFlipped) {
                // Front: Question
                Text(
                    text = "Q: ${card.questionFront}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "🔄 Tap card to flip & reveal answer",
                        style = MaterialTheme.typography.labelSmall,
                        color = SaffronPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                // Back: Answer & Mnemonic
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(12.dp)
                ) {
                    Column {
                        Text(
                            text = "A: ${card.answerBack}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        if (card.mnemonicOrTrick.isNotBlank()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "💡 Memory Hook: ${card.mnemonicOrTrick}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = SaffronPrimary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = BorderSubtle, thickness = 0.8.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // Action: Mark Mastered / Mark Learning
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { isFlipped = !isFlipped }) {
                    Icon(Icons.Default.Flip, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (isFlipped) "Show Question" else "Show Answer")
                }

                FilledTonalButton(
                    onClick = onToggleMastery,
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = if (card.isMastered) SaffronLight else EmeraldGreenLight,
                        contentColor = if (card.isMastered) SaffronPrimary else EmeraldGreen
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = if (card.isMastered) Icons.Default.Replay else Icons.Default.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (card.isMastered) "Mark as Review" else "Mark Mastered",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
fun AddFlashcardDialog(
    onDismiss: () -> Unit,
    onAdd: (subject: String, chapter: String, question: String, answer: String, mnemonic: String) -> Unit
) {
    var subject by remember { mutableStateOf("Science") }
    var chapter by remember { mutableStateOf("") }
    var question by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }
    var mnemonic by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Create Revision Flashcard") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Subject (e.g. Science, Maths)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = chapter,
                    onValueChange = { chapter = it },
                    label = { Text("Chapter") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = question,
                    onValueChange = { question = it },
                    label = { Text("Front: Question / Definition") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it },
                    label = { Text("Back: Answer / Equation") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = mnemonic,
                    onValueChange = { mnemonic = it },
                    label = { Text("Mnemonic / Trick (Optional)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (question.isNotBlank() && answer.isNotBlank()) {
                        onAdd(subject, chapter, question, answer, mnemonic)
                    }
                },
                enabled = question.isNotBlank() && answer.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary)
            ) {
                Text("Add to Deck", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
