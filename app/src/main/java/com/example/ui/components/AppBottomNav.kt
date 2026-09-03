package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.theme.SaffronPrimary
import com.example.viewmodel.AppTab

@Composable
fun AppBottomNav(
    selectedTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        windowInsets = WindowInsets.navigationBars,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp,
        modifier = modifier.testTag("app_bottom_nav_bar")
    ) {
        val tabs = listOf(
            Triple(AppTab.SYLLABUS, Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
            Triple(AppTab.VIDEOS, Icons.Filled.PlayCircle, Icons.Outlined.PlayCircle),
            Triple(AppTab.TEST_PAPERS, Icons.Filled.Assignment, Icons.Outlined.Assignment),
            Triple(AppTab.NOTEBOOK, Icons.Filled.EditNote, Icons.Outlined.EditNote),
            Triple(AppTab.NOTE_METHODS, Icons.Filled.Lightbulb, Icons.Outlined.Lightbulb),
            Triple(AppTab.EXAM_TRICKS, Icons.Filled.Bolt, Icons.Outlined.Bolt),
            Triple(AppTab.FLASHCARDS, Icons.Filled.Style, Icons.Outlined.Style)
        )

        tabs.forEach { (tab, filledIcon, outlinedIcon) ->
            val isSelected = selectedTab == tab
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tab) },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = if (isSelected) filledIcon else outlinedIcon,
                        contentDescription = tab.title,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SaffronPrimary,
                    selectedTextColor = SaffronPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.testTag("nav_item_${tab.name.lowercase()}")
            )
        }
    }
}
