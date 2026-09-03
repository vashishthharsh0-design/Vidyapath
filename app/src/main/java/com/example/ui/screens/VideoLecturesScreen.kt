package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.data.VideoSuggestionRepository
import com.example.model.ClassGrade
import com.example.model.SubjectType
import com.example.model.VideoCategory
import com.example.model.YouTubeVideoSuggestion
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.SaffronPrimary
import com.example.util.YouTubeLauncher
import com.example.viewmodel.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoLecturesScreen(
    state: MainUiState,
    onFilterSubject: (SubjectType?) -> Unit,
    onFilterCategory: (VideoCategory) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onToggleSaveVideo: (String) -> Unit,
    onSelectGrade: (ClassGrade) -> Unit,
    onCreateNoteFromVideo: (YouTubeVideoSuggestion) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showGradeDropdown by remember { mutableStateOf(false) }
    var showOnlySaved by remember { mutableStateOf(false) }

    // Filter videos according to class, subject, category, and search query
    val allForGrade = remember(state.selectedGrade) {
        VideoSuggestionRepository.getVideosForGrade(state.selectedGrade)
    }

    val filteredVideos = remember(
        state.selectedGrade,
        state.videoFilterSubject,
        state.videoFilterCategory,
        state.videoSearchQuery,
        showOnlySaved,
        state.savedVideoIds
    ) {
        var list = VideoSuggestionRepository.filterVideos(
            grade = state.selectedGrade,
            subject = state.videoFilterSubject,
            category = state.videoFilterCategory,
            query = state.videoSearchQuery
        )
        if (showOnlySaved) {
            list = list.filter { state.savedVideoIds.contains(it.id) }
        }
        list
    }

    // Dynamic subjects according to selected class
    val availableSubjects = remember(state.selectedGrade) {
        when (state.selectedGrade) {
            ClassGrade.CLASS_12_COMMERCE, ClassGrade.CLASS_11_COMMERCE -> listOf(
                SubjectType.ACCOUNTANCY,
                SubjectType.BUSINESS_STUDIES,
                SubjectType.ECONOMICS,
                SubjectType.MATHEMATICS
            )
            ClassGrade.CLASS_12, ClassGrade.CLASS_11 -> listOf(
                SubjectType.PHYSICS,
                SubjectType.CHEMISTRY,
                SubjectType.BIOLOGY,
                SubjectType.MATHEMATICS
            )
            ClassGrade.CLASS_10, ClassGrade.CLASS_9 -> listOf(
                SubjectType.SCIENCE_GENERAL,
                SubjectType.MATHEMATICS,
                SubjectType.HISTORY,
                SubjectType.POLITY
            )
            ClassGrade.COMPETITIVE -> listOf(
                SubjectType.ACCOUNTANCY,
                SubjectType.ECONOMICS,
                SubjectType.PHYSICS,
                SubjectType.MATHEMATICS
            )
        }
    }

    Scaffold(
        modifier = modifier.testTag("video_lectures_screen")
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = paddingValues.calculateTopPadding() + 8.dp,
                bottom = paddingValues.calculateBottomPadding() + 80.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Hero Banner for Class Video Suggestions
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF280A04),
                                        Color(0xFF1E1E2E)
                                    )
                                )
                            )
                            .padding(18.dp)
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFFFF0000))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.PlayArrow,
                                        contentDescription = "YouTube",
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "YouTube Class Hub",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }

                                // Class Selector Dropdown Button
                                Box {
                                    OutlinedButton(
                                        onClick = { showGradeDropdown = true },
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            contentColor = SaffronPrimary
                                        ),
                                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                        modifier = Modifier.height(34.dp)
                                    ) {
                                        Text(
                                            text = state.selectedGrade.displayName,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Icon(
                                            imageVector = Icons.Filled.ArrowDropDown,
                                            contentDescription = "Select Class",
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }

                                    DropdownMenu(
                                        expanded = showGradeDropdown,
                                        onDismissRequest = { showGradeDropdown = false }
                                    ) {
                                        ClassGrade.entries.forEach { grade ->
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = grade.displayName,
                                                        fontWeight = if (grade == state.selectedGrade) FontWeight.Bold else FontWeight.Normal,
                                                        color = if (grade == state.selectedGrade) SaffronPrimary else MaterialTheme.colorScheme.onSurface
                                                    )
                                                },
                                                onClick = {
                                                    onSelectGrade(grade)
                                                    showGradeDropdown = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Curated Lectures for ${state.selectedGrade.displayName}",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Hand-picked one-shots, NCERT numerical breakdowns, and board exam PYQ sessions by India's top educators (Physics Wallah, Rajat Arora, Sunil Panda, Dear Sir, Digraj Sir).",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.85f),
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            // Search Bar
            item {
                OutlinedTextField(
                    value = state.videoSearchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = { Text("Search by topic, chapter, or educator (e.g. Rajat Arora, One Shot)") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search videos",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    trailingIcon = {
                        if (state.videoSearchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchQueryChange("") }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Clear search"
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("video_search_input")
                )
            }

            // Video Format / Category Pills
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Lecture Format",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(VideoCategory.entries) { category ->
                            val isSelected = state.videoFilterCategory == category
                            FilterChip(
                                selected = isSelected,
                                onClick = { onFilterCategory(category) },
                                label = {
                                    Text(
                                        text = "${category.iconEmoji} ${category.displayName}",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = SaffronPrimary.copy(alpha = 0.18f),
                                    selectedLabelColor = SaffronPrimary
                                ),
                                modifier = Modifier.testTag("category_chip_${category.name}")
                            )
                        }
                    }
                }
            }

            // Subject Filter Chips according to class
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Filter by Subject (${state.selectedGrade.code})",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        // Bookmark filter toggle
                        FilterChip(
                            selected = showOnlySaved,
                            onClick = { showOnlySaved = !showOnlySaved },
                            leadingIcon = {
                                Icon(
                                    imageVector = if (showOnlySaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = "Saved (${state.savedVideoIds.size})",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        )
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            FilterChip(
                                selected = state.videoFilterSubject == null,
                                onClick = { onFilterSubject(null) },
                                label = { Text("All Subjects") }
                            )
                        }
                        items(availableSubjects) { subject ->
                            FilterChip(
                                selected = state.videoFilterSubject == subject,
                                onClick = {
                                    onFilterSubject(if (state.videoFilterSubject == subject) null else subject)
                                },
                                label = { Text(subject.displayName) }
                            )
                        }
                    }
                }
            }

            // Results count and direct YouTube Search bar
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${filteredVideos.size} videos recommended for ${state.selectedGrade.displayName}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    TextButton(
                        onClick = {
                            val q = if (state.videoSearchQuery.isNotBlank()) {
                                "${state.selectedGrade.displayName} ${state.videoSearchQuery}"
                            } else {
                                "${state.selectedGrade.displayName} one shot marathon lectures"
                            }
                            YouTubeLauncher.searchYouTube(context, q)
                        },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = SaffronPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Search YouTube",
                            style = MaterialTheme.typography.labelSmall,
                            color = SaffronPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Video Cards List
            if (filteredVideos.isEmpty()) {
                item {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SearchOff,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "No matching lectures found",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Try adjusting your subject or format filters, or search directly on YouTube.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = {
                                    val q = "${state.selectedGrade.displayName} ${state.videoSearchQuery}"
                                    YouTubeLauncher.searchYouTube(context, q)
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary)
                            ) {
                                Icon(imageVector = Icons.Filled.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Search '${state.videoSearchQuery}' on YouTube")
                            }
                        }
                    }
                }
            } else {
                items(filteredVideos, key = { it.id }) { video ->
                    val isSaved = state.savedVideoIds.contains(video.id)
                    VideoItemCard(
                        video = video,
                        isSaved = isSaved,
                        onWatchVideo = {
                            YouTubeLauncher.openVideo(context, video.youtubeVideoUrl, video.searchFallbackQuery)
                        },
                        onSearchYouTube = {
                            YouTubeLauncher.searchYouTube(context, video.searchFallbackQuery)
                        },
                        onToggleSave = { onToggleSaveVideo(video.id) },
                        onCreateNotes = { onCreateNoteFromVideo(video) }
                    )
                }
            }
        }
    }
}

@Composable
fun VideoItemCard(
    video: YouTubeVideoSuggestion,
    isSaved: Boolean,
    onWatchVideo: () -> Unit,
    onSearchYouTube: () -> Unit,
    onToggleSave: () -> Unit,
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
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .testTag("video_card_${video.id}")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Video Thumbnail Header Mockup
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF1B1B26),
                                Color(0xFF0F0F17)
                            )
                        )
                    )
                    .clickable { onWatchVideo() }
                    .padding(12.dp)
            ) {
                // Top Badges
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // YouTube Pill
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFFF0000))
                            .padding(horizontal = 6.dp, vertical = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "YouTube",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }

                    // Save / Bookmark button
                    IconButton(
                        onClick = onToggleSave,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.4f))
                    ) {
                        Icon(
                            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Save video",
                            tint = if (isSaved) SaffronPrimary else Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // Center Play Icon Indicator
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.65f))
                        .border(1.5.dp, SaffronPrimary, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Play Lecture",
                        tint = SaffronPrimary,
                        modifier = Modifier.size(28.dp)
                    )
                }

                // Bottom Left: Views Count & Subject
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Text(
                        text = "${video.subject.displayName} • ${video.viewsCount}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 11.sp
                    )
                }

                // Bottom Right: Duration Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Black.copy(alpha = 0.8f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = video.durationText,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                }
            }

            // Card Body
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Category & Topper Badge
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                        modifier = Modifier.padding(bottom = 2.dp)
                    ) {
                        Text(
                            text = "${video.category.iconEmoji} ${video.category.displayName}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    if (video.isBoardTopperFavorite) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = EmeraldGreen.copy(alpha = 0.15f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = null,
                                    tint = EmeraldGreen,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Topper Favorite",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldGreen,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }

                // Video Title
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Channel Name & Verified Badge
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = video.channelName,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (video.verifiedChannel) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Verified Channel",
                            tint = SaffronPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                // Description
                Text(
                    text = video.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp
                )

                // Recommended For Highlight
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Lightbulb,
                            contentDescription = null,
                            tint = SaffronPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = video.recommendedFor,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Key Topics Covered Chips
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(video.keyTopicsCovered) { topic ->
                        SuggestionChip(
                            onClick = {},
                            label = {
                                Text(
                                    text = "#$topic",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontSize = 10.sp
                                )
                            },
                            modifier = Modifier.height(26.dp)
                        )
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                // Action Buttons Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Watch on YouTube Primary Button
                    Button(
                        onClick = onWatchVideo,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFCC0000)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                        modifier = Modifier
                            .weight(1.2f)
                            .testTag("watch_video_btn_${video.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Watch Video",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Smart Note from Video Button
                    OutlinedButton(
                        onClick = onCreateNotes,
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("note_from_video_btn_${video.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.EditNote,
                            contentDescription = null,
                            tint = SaffronPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Take Notes",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = SaffronPrimary
                        )
                    }

                    // External Search Fallback
                    IconButton(
                        onClick = onSearchYouTube,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.OpenInNew,
                            contentDescription = "Open in YouTube search",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}
