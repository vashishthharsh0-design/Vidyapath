package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material.icons.automirrored.filled.MenuBook
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.NcertBookRepository
import com.example.data.NcertNotesProvider
import com.example.model.*
import com.example.ui.theme.*
import com.example.viewmodel.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NcertFolderScreen(
    state: MainUiState,
    onSelectGrade: (ClassGrade) -> Unit,
    onSelectBook: (NcertBook?) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onFilterSubject: (SubjectType?) -> Unit,
    onCreateNoteFromChapter: (NcertBook, NcertChapterInfo) -> Unit,
    onAskAiTutor: (NcertBook, NcertChapterInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val filteredBooks = remember(state.selectedGrade, state.ncertFilterSubject, state.ncertSearchQuery) {
        val query = state.ncertSearchQuery.trim()
        if (query.isNotBlank()) {
            NcertBookRepository.searchBooks(query, state.selectedGrade).let { list ->
                if (state.ncertFilterSubject != null) {
                    list.filter { it.subject == state.ncertFilterSubject }
                } else {
                    list
                }
            }
        } else {
            NcertBookRepository.getBooksByGradeAndSubject(state.selectedGrade, state.ncertFilterSubject)
        }
    }

    if (state.selectedNcertBook != null) {
        // Book Chapter Explorer Detail View with Detailed Notes
        NcertBookDetailView(
            book = state.selectedNcertBook,
            onBack = { onSelectBook(null) },
            onCreateNote = { ch -> onCreateNoteFromChapter(state.selectedNcertBook, ch) },
            onAskAi = { ch -> onAskAiTutor(state.selectedNcertBook, ch) },
            modifier = modifier
        )
    } else {
        // Main NCERT Folder Library
        Scaffold(
            topBar = {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 2.dp,
                    shadowElevation = 1.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(TealDark)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Folder,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "NCERT Folder & Notes",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Official Textbooks & In-Depth Notes (Classes 6–10)",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = EmeraldGreen
                                    )
                                }
                            }

                            FilledTonalButton(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ncert.nic.in/textbook.php"))
                                    try {
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        Toast.makeText(context, "Could not open browser", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = EmeraldGreenLight,
                                    contentColor = EmeraldGreen
                                ),
                                modifier = Modifier.testTag("official_ncert_portal_btn")
                            ) {
                                Icon(
                                    Icons.Default.OpenInNew,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("NCERT Portal", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Search Bar
                        OutlinedTextField(
                            value = state.ncertSearchQuery,
                            onValueChange = onSearchQueryChange,
                            placeholder = { Text("Search NCERT books, chapters, topics...", style = MaterialTheme.typography.bodyMedium) },
                            leadingIcon = {
                                Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingIcon = {
                                if (state.ncertSearchQuery.isNotBlank()) {
                                    IconButton(onClick = { onSearchQueryChange("") }) {
                                        Icon(Icons.Default.Close, contentDescription = "Clear search", modifier = Modifier.size(18.dp))
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = TealDark,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("ncert_search_field")
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Grade Selector Chips (Classes 6, 7, 8, 9, 10)
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val grades = listOf(
                                ClassGrade.CLASS_6,
                                ClassGrade.CLASS_7,
                                ClassGrade.CLASS_8,
                                ClassGrade.CLASS_9,
                                ClassGrade.CLASS_10
                            )

                            items(grades) { grade ->
                                val isSelected = state.selectedGrade == grade
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSelectGrade(grade) },
                                    label = {
                                        Text(
                                            text = grade.displayName,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                        )
                                    },
                                    leadingIcon = if (isSelected) {
                                        {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    } else null,
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = TealDark,
                                        selectedLabelColor = Color.White,
                                        selectedLeadingIconColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.testTag("ncert_grade_chip_${grade.name.lowercase()}")
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Subject Filter Chips
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            item {
                                FilterChip(
                                    selected = state.ncertFilterSubject == null,
                                    onClick = { onFilterSubject(null) },
                                    label = { Text("All Subjects", style = MaterialTheme.typography.labelSmall) },
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.testTag("ncert_subject_filter_all")
                                )
                            }

                            val subjects = listOf(
                                SubjectType.SCIENCE_GENERAL to "Science",
                                SubjectType.MATHEMATICS to "Math",
                                SubjectType.HISTORY to "History",
                                SubjectType.GEOGRAPHY to "Geography",
                                SubjectType.POLITY to "Civics",
                                SubjectType.ENGLISH to "English",
                                SubjectType.HINDI to "Hindi",
                                SubjectType.SANSKRIT to "Sanskrit"
                            )

                            items(subjects) { (subj, label) ->
                                val isSelected = state.ncertFilterSubject == subj
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onFilterSubject(if (isSelected) null else subj) },
                                    label = { Text(label, style = MaterialTheme.typography.labelSmall) },
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.testTag("ncert_subject_filter_${subj.name.lowercase()}")
                                )
                            }
                        }
                    }
                }
            },
            modifier = modifier
        ) { paddingValues ->
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .testTag("ncert_books_list")
            ) {
                // Info & Rationalised syllabus banner
                item {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = TealDark,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${state.selectedGrade.displayName} NCERT Textbooks (${filteredBooks.size} Books Available)",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Rationalised 2024–25 editions. Tap any textbook to access chapters, comprehensive detailed notes, formulas, and NCERT solutions.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                if (filteredBooks.isEmpty()) {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 40.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Outlined.FolderOff,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.outline,
                                    modifier = Modifier.size(56.dp)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "No NCERT Textbooks Found",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Try clearing the search query or selecting another subject.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                } else {
                    items(filteredBooks, key = { it.id }) { book ->
                        NcertBookCard(
                            book = book,
                            onOpenBook = { onSelectBook(book) },
                            onOpenPdfPortal = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(book.officialNcertUrl))
                                try {
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Could not open NCERT portal", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NcertBookCard(
    book: NcertBook,
    onOpenBook: () -> Unit,
    onOpenPdfPortal: () -> Unit
) {
    val bookColor = Color(book.coverColorHex)

    Card(
        onClick = onOpenBook,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("ncert_book_card_${book.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Book Spine Visual
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(84.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(bookColor)
                    .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "NCERT",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // CBSE Code Badge
                    Surface(
                        color = bookColor.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = book.cbseBookCode,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = bookColor,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    // Total Chapters Count
                    Text(
                        text = "${book.chapters.size} Chapters",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (book.hindiTitle.isNotBlank()) {
                    Text(
                        text = book.hindiTitle,
                        style = MaterialTheme.typography.labelMedium,
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = book.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onOpenBook,
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = bookColor),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Icon(
                            Icons.Default.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Detailed Notes", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = onOpenPdfPortal,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Icon(
                            Icons.Default.OpenInNew,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("NCERT PDF", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NcertBookDetailView(
    book: NcertBook,
    onBack: () -> Unit,
    onCreateNote: (NcertChapterInfo) -> Unit,
    onAskAi: (NcertChapterInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val bookColor = Color(book.coverColorHex)
    var selectedChapterForDetailedNotes by remember { mutableStateOf<NcertChapterInfo?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "${book.grade.displayName} • ${book.cbseBookCode} • Chapter Notes in Detail",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("ncert_detail_back_btn")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to Books")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(book.officialNcertUrl))
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Could not open portal", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.testTag("ncert_book_official_pdf_action")
                    ) {
                        Icon(Icons.Default.OpenInNew, contentDescription = "Official NCERT e-Book Portal")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .testTag("ncert_chapters_list")
        ) {
            // Book Overview Banner
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = bookColor.copy(alpha = 0.1f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = bookColor
                            ) {
                                Text(
                                    text = book.subjectCategory,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Rationalised Syllabus • ${book.chapters.size} Chapters • Full Notes",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = book.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Chapter items
            items(book.chapters, key = { it.chapterNumber }) { chapter ->
                NcertChapterCard(
                    chapter = chapter,
                    book = book,
                    onOpenDetailedNotes = { selectedChapterForDetailedNotes = chapter },
                    onCreateNote = { onCreateNote(chapter) },
                    onAskAi = { onAskAi(chapter) },
                    onOpenPdfPortal = {
                        val portalUrl = chapter.pdfPortalUrl ?: book.officialNcertUrl
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(portalUrl))
                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "Could not open NCERT portal", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }

    // Detailed Notes Full Reader Dialog
    selectedChapterForDetailedNotes?.let { chapter ->
        NcertDetailedNotesDialog(
            book = book,
            chapter = chapter,
            onDismiss = { selectedChapterForDetailedNotes = null },
            onCreateNote = {
                selectedChapterForDetailedNotes = null
                onCreateNote(chapter)
            },
            onAskAi = {
                selectedChapterForDetailedNotes = null
                onAskAi(chapter)
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun NcertChapterCard(
    chapter: NcertChapterInfo,
    book: NcertBook,
    onOpenDetailedNotes: () -> Unit,
    onCreateNote: () -> Unit,
    onAskAi: () -> Unit,
    onOpenPdfPortal: () -> Unit
) {
    val bookColor = Color(book.coverColorHex)
    var isQuickNotesExpanded by remember { mutableStateOf(false) }
    val detailedNotes = remember(book.id, chapter.chapterNumber) {
        NcertNotesProvider.getDetailedNotes(book, chapter)
    }

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("ncert_chapter_card_${chapter.chapterNumber}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Chapter Number Circle
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(bookColor.copy(alpha = 0.15f))
                ) {
                    Text(
                        text = chapter.chapterNumber.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = bookColor
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = chapter.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (chapter.hindiTitle.isNotBlank()) {
                        Text(
                            text = chapter.hindiTitle,
                            style = MaterialTheme.typography.labelMedium,
                            color = TextSecondary
                        )
                    }
                }

                // In-Depth Notes Indicator Badge
                Surface(
                    color = EmeraldGreenLight,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = EmeraldGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "Notes In Detail",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldGreen
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Chapter Summary
            Text(
                text = chapter.summary,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = MaterialTheme.typography.bodySmall.lineHeight
            )

            // Key Topics Chips
            if (chapter.keyTopics.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    chapter.keyTopics.forEach { topic ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                        ) {
                            Text(
                                text = "• $topic",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }

            // Quick Inline Preview Toggle
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { isQuickNotesExpanded = !isQuickNotesExpanded }
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isQuickNotesExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = bookColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isQuickNotesExpanded) "Collapse Notes Preview" else "Preview Detailed Notes (${detailedNotes.keyConcepts.size} Sections • ${detailedNotes.importantDefinitions.size} Definitions)",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = bookColor
                    )
                }

                Text(
                    text = "Tap to view",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Expandable Inline Notes Section
            AnimatedVisibility(visible = isQuickNotesExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    // Quick Concept Highlights
                    detailedNotes.keyConcepts.take(2).forEach { concept ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = concept.title,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = concept.explanation,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    if (detailedNotes.importantDefinitions.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Key Definition:",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        val firstDef = detailedNotes.importantDefinitions.first()
                        Text(
                            text = "• ${firstDef.first}: ${firstDef.second}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(10.dp))

            // Chapter Action Buttons: Detailed Notes, Make Notes, Ask AI, PDF
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Read Detailed Notes (Prominent Primary Action)
                Button(
                    onClick = onOpenDetailedNotes,
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = bookColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(34.dp)
                        .testTag("ncert_chapter_read_detailed_notes_${chapter.chapterNumber}")
                ) {
                    Icon(
                        Icons.Default.MenuBook,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Detailed Notes", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                }

                // Create Smart Notes
                FilledTonalButton(
                    onClick = onCreateNote,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(34.dp)
                        .testTag("ncert_chapter_make_notes_${chapter.chapterNumber}")
                ) {
                    Icon(
                        Icons.Default.EditNote,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Save Note", style = MaterialTheme.typography.labelSmall)
                }

                // Ask AI Tutor
                FilledTonalButton(
                    onClick = onAskAi,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(34.dp)
                ) {
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = SaffronPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ask AI", style = MaterialTheme.typography.labelSmall)
                }

                // Official PDF
                OutlinedButton(
                    onClick = onOpenPdfPortal,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(34.dp)
                ) {
                    Icon(
                        Icons.Default.PictureAsPdf,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = CrimsonRed
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("PDF", style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

@Composable
fun NcertDetailedNotesDialog(
    book: NcertBook,
    chapter: NcertChapterInfo,
    onDismiss: () -> Unit,
    onCreateNote: () -> Unit,
    onAskAi: () -> Unit
) {
    val context = LocalContext.current
    val bookColor = Color(book.coverColorHex)
    val notes = remember(book.id, chapter.chapterNumber) {
        NcertNotesProvider.getDetailedNotes(book, chapter)
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .testTag("ncert_detailed_notes_dialog"),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Dialog Header
                Surface(
                    color = bookColor.copy(alpha = 0.12f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(bookColor)
                            ) {
                                Text(
                                    text = chapter.chapterNumber.toString(),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = chapter.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "${book.title} • ${book.grade.displayName} • CBSE NCERT",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier.testTag("ncert_detailed_notes_close_btn")
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Close detailed notes")
                        }
                    }
                }

                // Scrollable Detailed Notes Content
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    // Header Status
                    item {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = EmeraldGreenLight,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = EmeraldGreen,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Official NCERT Rationalised Study Notes in Detail",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = EmeraldGreen
                                    )
                                    Text(
                                        text = "Curated according to CBSE 2024–25 board pattern with theoretical explanations, formulas, definitions, and model Q&As.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }

                    // Chapter Overview
                    item {
                        DetailSectionCard(
                            title = "Chapter Overview & Core Themes",
                            icon = Icons.Default.Info,
                            accentColor = bookColor
                        ) {
                            Text(
                                text = notes.overview,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
                            )
                        }
                    }

                    // Key Concepts Breakdown
                    item {
                        DetailSectionCard(
                            title = "Detailed Conceptual Breakdown (${notes.keyConcepts.size} Units)",
                            icon = Icons.Default.Lightbulb,
                            accentColor = SaffronPrimary
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                notes.keyConcepts.forEach { concept ->
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text(
                                                text = concept.title,
                                                style = MaterialTheme.typography.titleSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = concept.explanation,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                lineHeight = MaterialTheme.typography.bodySmall.lineHeight
                                            )

                                            if (concept.keyPoints.isNotEmpty()) {
                                                Spacer(modifier = Modifier.height(8.dp))
                                                concept.keyPoints.forEach { point ->
                                                    Row(
                                                        verticalAlignment = Alignment.Top,
                                                        modifier = Modifier.padding(vertical = 2.dp)
                                                    ) {
                                                        Text(
                                                            text = "• ",
                                                            style = MaterialTheme.typography.bodySmall,
                                                            fontWeight = FontWeight.Bold,
                                                            color = bookColor
                                                        )
                                                        Text(
                                                            text = point,
                                                            style = MaterialTheme.typography.bodySmall,
                                                            color = MaterialTheme.colorScheme.onSurface
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Important Definitions & Key Terminology
                    if (notes.importantDefinitions.isNotEmpty()) {
                        item {
                            DetailSectionCard(
                                title = "Important NCERT Definitions & Terms",
                                icon = Icons.Default.FormatQuote,
                                accentColor = DeepNavy
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    notes.importantDefinitions.forEach { (term, definition) ->
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(10.dp),
                                                verticalAlignment = Alignment.Top
                                            ) {
                                                Text(
                                                    text = "📌",
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Column {
                                                    Text(
                                                        text = term,
                                                        style = MaterialTheme.typography.titleSmall,
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colorScheme.onSurface
                                                    )
                                                    Spacer(modifier = Modifier.height(2.dp))
                                                    Text(
                                                        text = definition,
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
                    }

                    // Key Formulas, Laws, or Timelines
                    if (notes.keyFormulasOrLaws.isNotEmpty()) {
                        item {
                            DetailSectionCard(
                                title = "Essential Formulas, Equations & Laws",
                                icon = Icons.Default.Functions,
                                accentColor = CrimsonRed
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    notes.keyFormulasOrLaws.forEach { formula ->
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = CrimsonRedLight,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text(
                                                text = formula,
                                                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                                                fontWeight = FontWeight.SemiBold,
                                                color = CrimsonRed,
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // NCERT In-Text & Exercise Questions with Model Solutions
                    if (notes.ncertQuestionsAndAnswers.isNotEmpty()) {
                        item {
                            DetailSectionCard(
                                title = "NCERT In-Text & Exercise Q&A (Model Answers)",
                                icon = Icons.Default.Quiz,
                                accentColor = TealDark
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    notes.ncertQuestionsAndAnswers.forEachIndexed { idx, qna ->
                                        Surface(
                                            shape = RoundedCornerShape(10.dp),
                                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Column(modifier = Modifier.padding(12.dp)) {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Surface(
                                                        shape = RoundedCornerShape(4.dp),
                                                        color = TealDark
                                                    ) {
                                                        Text(
                                                            text = "Q${idx + 1}",
                                                            style = MaterialTheme.typography.labelSmall,
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color.White,
                                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.width(8.dp))
                                                    Text(
                                                        text = qna.question,
                                                        style = MaterialTheme.typography.titleSmall,
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colorScheme.onSurface
                                                    )
                                                }

                                                Spacer(modifier = Modifier.height(8.dp))
                                                Text(
                                                    text = "Model Solution:",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontWeight = FontWeight.Bold,
                                                    color = EmeraldGreen
                                                )
                                                Spacer(modifier = Modifier.height(2.dp))
                                                Text(
                                                    text = qna.answer,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    lineHeight = MaterialTheme.typography.bodySmall.lineHeight
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // CBSE Board Exam Pointers
                    if (notes.examPointers.isNotEmpty()) {
                        item {
                            DetailSectionCard(
                                title = "CBSE Board Exam Pointers & Mistakes to Avoid",
                                icon = Icons.Default.Star,
                                accentColor = SaffronPrimary
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    notes.examPointers.forEach { tip ->
                                        Row(
                                            verticalAlignment = Alignment.Top,
                                            modifier = Modifier.padding(vertical = 2.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = SaffronPrimary,
                                                modifier = Modifier
                                                    .size(16.dp)
                                                    .padding(top = 2.dp)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = tip,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Dialog Footer Actions
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 3.dp,
                    shadowElevation = 4.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        // Copy Notes Action
                        OutlinedButton(
                            onClick = {
                                val fullNotesText = buildString {
                                    appendLine("=== NCERT DETAILED NOTES: ${chapter.title} ===")
                                    appendLine("Book: ${book.title} (${book.grade.displayName})")
                                    appendLine()
                                    appendLine("OVERVIEW:")
                                    appendLine(notes.overview)
                                    appendLine()
                                    appendLine("KEY CONCEPTS:")
                                    notes.keyConcepts.forEach { c ->
                                        appendLine("${c.title}: ${c.explanation}")
                                        c.keyPoints.forEach { p -> appendLine("  • $p") }
                                    }
                                    appendLine()
                                    if (notes.importantDefinitions.isNotEmpty()) {
                                        appendLine("DEFINITIONS:")
                                        notes.importantDefinitions.forEach { (t, d) -> appendLine("• $t: $d") }
                                        appendLine()
                                    }
                                    if (notes.keyFormulasOrLaws.isNotEmpty()) {
                                        appendLine("FORMULAS & LAWS:")
                                        notes.keyFormulasOrLaws.forEach { f -> appendLine("• $f") }
                                        appendLine()
                                    }
                                    if (notes.ncertQuestionsAndAnswers.isNotEmpty()) {
                                        appendLine("NCERT Q&A:")
                                        notes.ncertQuestionsAndAnswers.forEach { q ->
                                            appendLine("Q: ${q.question}")
                                            appendLine("A: ${q.answer}")
                                            appendLine()
                                        }
                                    }
                                }

                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("NCERT Notes: ${chapter.title}", fullNotesText)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "Detailed notes copied to clipboard!", Toast.LENGTH_SHORT).show()
                            },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.height(38.dp)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Copy Notes", style = MaterialTheme.typography.labelSmall)
                        }

                        // Ask AI Tutor
                        FilledTonalButton(
                            onClick = onAskAi,
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.height(38.dp)
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = SaffronPrimary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Ask AI Tutor", style = MaterialTheme.typography.labelSmall)
                        }

                        // Save to Notebook
                        Button(
                            onClick = onCreateNote,
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = bookColor),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(38.dp)
                                .testTag("ncert_detailed_notes_save_to_notebook")
                        ) {
                            Icon(Icons.Default.EditNote, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Save to Notebook", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailSectionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(accentColor.copy(alpha = 0.15f))
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}
