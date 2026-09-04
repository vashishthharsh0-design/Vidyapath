package com.example.ui.screens

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SyllabusRepository
import com.example.data.VideoSuggestionRepository
import com.example.model.*
import com.example.ui.theme.*
import com.example.util.YouTubeLauncher
import com.example.viewmodel.MainUiState

@Composable
fun SyllabusScreen(
    state: MainUiState,
    onChapterClick: (ChapterItem) -> Unit,
    onSubjectSelect: (SubjectType?) -> Unit,
    onCreateNotesForChapter: (ChapterItem) -> Unit,
    onNavigateToVideos: () -> Unit = {},
    onNavigateToAiTutor: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val filteredChapters = if (state.searchQuery.isNotBlank()) {
        SyllabusRepository.searchChapters(state.searchQuery)
    } else {
        SyllabusRepository.getChaptersByGradeAndSubject(state.selectedGrade, state.selectedSubject)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 80.dp)
    ) {
        // Hero Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("syllabus_hero_card")
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(PrimaryNavy, SaffronDark)
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
                                    text = "${state.selectedBoard.shortName} • ${state.selectedGrade.displayName}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = Color(0xFFFFD54F),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "NCERT Aligned Chapter Mastery",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Explore high-yield formulas, NCERT crux summaries, board exam trap alerts & PYQs mapped with smart note methods.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFF1F5F9)
                        )
                    }
                }
            }
        }

        // Subject Filter Chips
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Filter by Subject",
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
                            selected = state.selectedSubject == null,
                            onClick = { onSubjectSelect(null) },
                            label = { Text("All Subjects") },
                            leadingIcon = if (state.selectedSubject == null) {
                                { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null,
                            modifier = Modifier.testTag("filter_subject_all")
                        )
                    }
                    val availableSubjects = when (state.selectedGrade) {
                        ClassGrade.CLASS_12_COMMERCE, ClassGrade.CLASS_11_COMMERCE -> listOf(
                            SubjectType.ACCOUNTANCY,
                            SubjectType.BUSINESS_STUDIES,
                            SubjectType.ECONOMICS,
                            SubjectType.MATHEMATICS
                        )
                        ClassGrade.CLASS_12, ClassGrade.CLASS_11 -> listOf(
                            SubjectType.ACCOUNTANCY,
                            SubjectType.BUSINESS_STUDIES,
                            SubjectType.ECONOMICS,
                            SubjectType.PHYSICS,
                            SubjectType.CHEMISTRY,
                            SubjectType.BIOLOGY,
                            SubjectType.MATHEMATICS
                        )
                        ClassGrade.CLASS_10, ClassGrade.CLASS_9 -> listOf(
                            SubjectType.SCIENCE_GENERAL,
                            SubjectType.MATHEMATICS,
                            SubjectType.HISTORY,
                            SubjectType.POLITY,
                            SubjectType.GEOGRAPHY,
                            SubjectType.ECONOMICS
                        )
                        ClassGrade.COMPETITIVE -> listOf(
                            SubjectType.ACCOUNTANCY,
                            SubjectType.BUSINESS_STUDIES,
                            SubjectType.ECONOMICS,
                            SubjectType.PHYSICS,
                            SubjectType.CHEMISTRY,
                            SubjectType.BIOLOGY,
                            SubjectType.MATHEMATICS
                        )
                    }
                    items(availableSubjects) { subject ->
                        FilterChip(
                            selected = state.selectedSubject == subject,
                            onClick = { onSubjectSelect(subject) },
                            label = { Text(subject.displayName) },
                            leadingIcon = if (state.selectedSubject == subject) {
                                { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null,
                            modifier = Modifier.testTag("filter_subject_${subject.name.lowercase()}")
                        )
                    }
                }
            }
        }

        // Recommended YouTube Video Spotlight Card for this Class
        item {
            val context = LocalContext.current
            val featuredVideo = VideoSuggestionRepository.getVideosForGrade(state.selectedGrade).firstOrNull()
            if (featuredVideo != null) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("syllabus_video_spotlight_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = Color(0xFFFF0000)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text("Class Video", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                    }
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${featuredVideo.subject.displayName} • ${featuredVideo.durationText}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            TextButton(
                                onClick = onNavigateToVideos,
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
                            ) {
                                Text("View All Videos", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = SaffronPrimary)
                                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = SaffronPrimary, modifier = Modifier.size(16.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = featuredVideo.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "By ${featuredVideo.channelName} • ${featuredVideo.viewsCount}",
                                style = MaterialTheme.typography.bodySmall,
                                color = SaffronPrimary,
                                fontWeight = FontWeight.Medium
                            )

                            Button(
                                onClick = {
                                    YouTubeLauncher.openVideo(context, featuredVideo.youtubeVideoUrl, featuredVideo.searchFallbackQuery)
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF0000)),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier.height(30.dp)
                            ) {
                                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(2.dp))
                                Text("Play", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Vidya AI Tutor Spotlight Card
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("syllabus_ai_tutor_card")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = SaffronPrimary
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Vidya AI Mentor", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.White)
                                }
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = Color(0xFF4285F4).copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "Google Search Grounded",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF4285F4),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                )
                            }
                        }

                        TextButton(
                            onClick = onNavigateToAiTutor,
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                            modifier = Modifier.height(28.dp)
                        ) {
                            Text("Open Chat →", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = SaffronPrimary)
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Need help with ${state.selectedGrade.displayName}?",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Ask any NCERT concept doubt, step-by-step numerical derivation, or get verified CBSE marking schemes instantly.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Chapters List Header
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Chapters & Key Topics (${filteredChapters.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Tap for Crux & PYQs",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        if (filteredChapters.isEmpty()) {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.SearchOff,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No chapters found",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Try clearing the search query or changing the subject filter.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Chapter Cards
        items(filteredChapters) { chapter ->
            ChapterCard(
                chapter = chapter,
                onClick = { onChapterClick(chapter) },
                onCreateNotes = { onCreateNotesForChapter(chapter) }
            )
        }
    }
}

@Composable
fun ChapterCard(
    chapter: ChapterItem,
    onClick: () -> Unit,
    onCreateNotes: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("chapter_card_${chapter.id}")
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ch ${chapter.chapterNumber}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = PeacockIndigoLight,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = chapter.subject.displayName,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = PeacockIndigo,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }

                Surface(
                    color = AmberGoldLight,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = chapter.marksWeightage,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = AmberGold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = chapter.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = chapter.summary,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Quick Info Badges Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    color = EmeraldGreenLight,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = EmeraldGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${chapter.cruxPoints.size} NCERT Crux",
                            style = MaterialTheme.typography.labelSmall,
                            color = EmeraldGreen,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                if (chapter.formulas.isNotEmpty()) {
                    Surface(
                        color = VioletPurpleLight,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Calculate,
                                contentDescription = null,
                                tint = VioletPurple,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${chapter.formulas.size} Formulas",
                                style = MaterialTheme.typography.labelSmall,
                                color = VioletPurple,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = chapter.suggestedMethod.title.split(" ").first(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = BorderSubtle, thickness = 0.8.dp)
            Spacer(modifier = Modifier.height(10.dp))

            // Action Buttons Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onClick,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("Read Crux & Traps", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                }

                FilledTonalButton(
                    onClick = onCreateNotes,
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = SaffronLight,
                        contentColor = SaffronPrimary
                    ),
                    modifier = Modifier.testTag("make_notes_button_${chapter.id}")
                ) {
                    Icon(Icons.Default.EditNote, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Smart Note", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}
