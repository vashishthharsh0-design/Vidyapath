package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.*
import com.example.viewmodel.AppTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomNav(
    selectedTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    isLiteMode: Boolean = false,
    modifier: Modifier = Modifier
) {
    var showMoreSheet by remember { mutableStateOf(false) }

    NavigationBar(
        windowInsets = WindowInsets.navigationBars,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp,
        modifier = modifier.testTag("app_bottom_nav_bar")
    ) {
        if (isLiteMode) {
            // Lite Mode: 4 Core Offline-First Destinations
            val liteTabs = listOf(
                NavTabItem(AppTab.SYLLABUS, "Crux", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
                NavTabItem(AppTab.NOTEBOOK, "Notes", Icons.Filled.EditNote, Icons.Outlined.EditNote),
                NavTabItem(AppTab.FLASHCARDS, "Cards", Icons.Filled.Style, Icons.Outlined.Style),
                NavTabItem(AppTab.TEST_PAPERS, "Papers", Icons.Filled.Assignment, Icons.Outlined.Assignment)
            )

            liteTabs.forEach { item ->
                val isSelected = selectedTab == item.tab
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onTabSelected(item.tab) },
                    alwaysShowLabel = true,
                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.filledIcon else item.outlinedIcon,
                            contentDescription = item.label,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = EmeraldGreen,
                        selectedTextColor = EmeraldGreen,
                        indicatorColor = EmeraldGreenLight,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.testTag("nav_item_${item.tab.name.lowercase()}")
                )
            }
        } else {
            // Full Mode: 5 Clean Standard M3 Destinations
            val isMoreSubTab = selectedTab in listOf(AppTab.VIDEOS, AppTab.NOTE_METHODS, AppTab.EXAM_TRICKS, AppTab.FLASHCARDS, AppTab.NCERT)
            
            val primaryTabs = listOf(
                NavTabItem(AppTab.SYLLABUS, "Crux", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
                NavTabItem(AppTab.AI_TUTOR, "AI Tutor", Icons.Filled.AutoAwesome, Icons.Outlined.AutoAwesome),
                NavTabItem(AppTab.TEST_PAPERS, "Papers", Icons.Filled.Assignment, Icons.Outlined.Assignment),
                NavTabItem(AppTab.NOTEBOOK, "Notes", Icons.Filled.EditNote, Icons.Outlined.EditNote)
            )

            primaryTabs.forEach { item ->
                val isSelected = selectedTab == item.tab
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onTabSelected(item.tab) },
                    alwaysShowLabel = true,
                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.filledIcon else item.outlinedIcon,
                            contentDescription = item.label,
                            modifier = Modifier.size(23.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SaffronPrimary,
                        selectedTextColor = SaffronPrimary,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.testTag("nav_item_${item.tab.name.lowercase()}")
                )
            }

            // 5th Destination: "More Tools" (NCERT, Videos, Note Methods, Exam Tricks, Flashcards)
            val moreLabel = when (selectedTab) {
                AppTab.NCERT -> "NCERT"
                AppTab.VIDEOS -> "Videos"
                AppTab.NOTE_METHODS -> "Methods"
                AppTab.EXAM_TRICKS -> "Tricks"
                AppTab.FLASHCARDS -> "Cards"
                else -> "More"
            }

            val moreIcon = when (selectedTab) {
                AppTab.NCERT -> Icons.Filled.Folder
                AppTab.VIDEOS -> Icons.Filled.PlayCircle
                AppTab.NOTE_METHODS -> Icons.Filled.Lightbulb
                AppTab.EXAM_TRICKS -> Icons.Filled.Bolt
                AppTab.FLASHCARDS -> Icons.Filled.Style
                else -> Icons.Filled.Widgets
            }

            val moreOutlinedIcon = when (selectedTab) {
                AppTab.NCERT -> Icons.Outlined.Folder
                AppTab.VIDEOS -> Icons.Outlined.PlayCircle
                AppTab.NOTE_METHODS -> Icons.Outlined.Lightbulb
                AppTab.EXAM_TRICKS -> Icons.Outlined.Bolt
                AppTab.FLASHCARDS -> Icons.Outlined.Style
                else -> Icons.Outlined.Widgets
            }

            NavigationBarItem(
                selected = isMoreSubTab,
                onClick = { showMoreSheet = true },
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = if (isMoreSubTab) moreIcon else moreOutlinedIcon,
                        contentDescription = "More Learning Tools",
                        modifier = Modifier.size(23.dp)
                    )
                },
                label = {
                    Text(
                        text = moreLabel,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isMoreSubTab) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SaffronPrimary,
                    selectedTextColor = SaffronPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.testTag("nav_item_more")
            )
        }
    }

    // Modal Bottom Sheet for "More Tools" in Full Mode
    if (showMoreSheet) {
        ModalBottomSheet(
            onDismissRequest = { showMoreSheet = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier.testTag("more_tools_bottom_sheet")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 32.dp)
            ) {
                Text(
                    text = "More Learning Tools",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "High-yield NCERT and CBSE preparation modules",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(18.dp))

                // NCERT Folder Item
                ToolOptionRow(
                    title = "NCERT Folder & Textbooks",
                    subtitle = "Official rationalised NCERT curriculum (Classes 6–10) with PDF links",
                    icon = Icons.Default.Folder,
                    iconTint = TealDark,
                    isSelected = selectedTab == AppTab.NCERT,
                    onClick = {
                        onTabSelected(AppTab.NCERT)
                        showMoreSheet = false
                    },
                    testTag = "more_tool_ncert"
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Videos Item
                ToolOptionRow(
                    title = "Video Masterclasses",
                    subtitle = "One-shot CBSE & State Board educator lessons",
                    icon = Icons.Default.PlayCircle,
                    iconTint = CrimsonRed,
                    isSelected = selectedTab == AppTab.VIDEOS,
                    onClick = {
                        onTabSelected(AppTab.VIDEOS)
                        showMoreSheet = false
                    },
                    testTag = "more_tool_videos"
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Note Methods Item
                ToolOptionRow(
                    title = "7 Smart Note Methods",
                    subtitle = "Cornell, Boxing, Mind Mapping, Charting & Outlines",
                    icon = Icons.Default.Lightbulb,
                    iconTint = AmberGold,
                    isSelected = selectedTab == AppTab.NOTE_METHODS,
                    onClick = {
                        onTabSelected(AppTab.NOTE_METHODS)
                        showMoreSheet = false
                    },
                    testTag = "more_tool_methods"
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Exam Tricks Item
                ToolOptionRow(
                    title = "Vedic Math & Exam Tricks",
                    subtitle = "Speed calculations, mnemonics & MCQ elimination tactics",
                    icon = Icons.Default.Bolt,
                    iconTint = SaffronPrimary,
                    isSelected = selectedTab == AppTab.EXAM_TRICKS,
                    onClick = {
                        onTabSelected(AppTab.EXAM_TRICKS)
                        showMoreSheet = false
                    },
                    testTag = "more_tool_tricks"
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Flashcards Item
                ToolOptionRow(
                    title = "Spaced Repetition Flashcards",
                    subtitle = "Active recall cards for rapid formula & definition mastery",
                    icon = Icons.Default.Style,
                    iconTint = PeacockIndigo,
                    isSelected = selectedTab == AppTab.FLASHCARDS,
                    onClick = {
                        onTabSelected(AppTab.FLASHCARDS)
                        showMoreSheet = false
                    },
                    testTag = "more_tool_flashcards"
                )
            }
        }
    }
}

@Composable
private fun ToolOptionRow(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconTint: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    testTag: String
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else SurfaceContainerHigh
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag(testTag)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(iconTint.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = SaffronPrimary,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

private data class NavTabItem(
    val tab: AppTab,
    val label: String,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector
)
