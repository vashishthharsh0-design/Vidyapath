package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.BoardType
import com.example.model.ClassGrade
import com.example.ui.theme.*
import com.example.viewmodel.AppTab
import com.example.viewmodel.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    state: MainUiState,
    onGradeChange: (ClassGrade) -> Unit,
    onBoardChange: (BoardType) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onToggleRecallMode: () -> Unit,
    onBackClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var showGradeMenu by remember { mutableStateOf(false) }
    var showBoardMenu by remember { mutableStateOf(false) }
    var isSearchExpanded by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    if (onBackClick != null) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.testTag("top_bar_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(SaffronPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = "VidyaNotes Logo",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "VidyaNotes",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Indian Syllabus & Smart Notes",
                                style = MaterialTheme.typography.labelSmall,
                                color = SaffronPrimary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Board & Class Badges (Dropdown triggers)
                    Box {
                        FilledTonalButton(
                            onClick = { showGradeMenu = true },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            modifier = Modifier
                                .height(34.dp)
                                .testTag("grade_selector_button")
                        ) {
                            Text(
                                text = state.selectedGrade.code,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = showGradeMenu,
                            onDismissRequest = { showGradeMenu = false }
                        ) {
                            ClassGrade.values().forEach { grade ->
                                DropdownMenuItem(
                                    text = { Text(grade.displayName) },
                                    onClick = {
                                        onGradeChange(grade)
                                        showGradeMenu = false
                                    },
                                    leadingIcon = {
                                        if (grade == state.selectedGrade) {
                                            Icon(
                                                Icons.Default.Check,
                                                contentDescription = null,
                                                tint = SaffronPrimary
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Box {
                        FilledTonalButton(
                            onClick = { showBoardMenu = true },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = PeacockIndigoLight,
                                contentColor = PeacockIndigo
                            ),
                            modifier = Modifier
                                .height(34.dp)
                                .testTag("board_selector_button")
                        ) {
                            Text(
                                text = state.selectedBoard.shortName,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = showBoardMenu,
                            onDismissRequest = { showBoardMenu = false }
                        ) {
                            BoardType.values().forEach { board ->
                                DropdownMenuItem(
                                    text = { Text(board.displayName) },
                                    onClick = {
                                        onBoardChange(board)
                                        showBoardMenu = false
                                    },
                                    leadingIcon = {
                                        if (board == state.selectedBoard) {
                                            Icon(
                                                Icons.Default.Check,
                                                contentDescription = null,
                                                tint = PeacockIndigo
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }

                    IconButton(
                        onClick = { isSearchExpanded = !isSearchExpanded },
                        modifier = Modifier.testTag("toggle_search_button")
                    ) {
                        Icon(
                            imageVector = if (isSearchExpanded) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Active Recall Cover Toggle (for Notebook tab)
                    if (state.activeTab == AppTab.NOTEBOOK) {
                        IconButton(
                            onClick = onToggleRecallMode,
                            modifier = Modifier.testTag("toggle_active_recall_button")
                        ) {
                            Icon(
                                imageVector = if (state.isCoverRecallModeActive) Icons.Filled.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = "Active Recall Cover Mode",
                                tint = if (state.isCoverRecallModeActive) SaffronPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            AnimatedVisibility(visible = isSearchExpanded) {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = { Text("Search chapters, formulas, mnemonics...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (state.searchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchQueryChange("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .testTag("top_search_text_field")
                )
            }
        }
    }
}
