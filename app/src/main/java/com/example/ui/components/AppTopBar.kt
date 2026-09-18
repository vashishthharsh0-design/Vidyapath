package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.model.BoardType
import com.example.model.ClassGrade
import com.example.ui.theme.*
import com.example.viewmodel.AppTab
import com.example.viewmodel.MainUiState
import com.example.viewmodel.ThemeMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    state: MainUiState,
    onGradeChange: (ClassGrade) -> Unit,
    onBoardChange: (BoardType) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onToggleRecallMode: () -> Unit,
    onToggleLiteMode: () -> Unit = {},
    onOpenLiteModeInfo: () -> Unit = {},
    onDismissNetworkNotice: () -> Unit = {},
    onToggleThemeMode: () -> Unit = {},
    onSetThemeMode: (ThemeMode) -> Unit = {},
    onOpenApkDownload: () -> Unit = {},
    isDark: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    onAiTutorClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var showGradeMenu by remember { mutableStateOf(false) }
    var showBoardMenu by remember { mutableStateOf(false) }
    var showThemeMenu by remember { mutableStateOf(false) }
    var isSearchExpanded by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 3.dp,
        shadowElevation = 1.dp,
        modifier = modifier
            .fillMaxWidth()
            .testTag("app_top_bar")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            // Row 1: Brand & Primary Actions
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Left: Logo & App Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    if (onBackClick != null) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier
                                .size(40.dp)
                                .testTag("top_bar_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                    } else {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (state.isLiteMode) EmeraldGreen else SaffronPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (state.isLiteMode) Icons.Default.Bolt else Icons.Default.MenuBook,
                                contentDescription = "VidyaNotes Logo",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "VidyaNotes",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (state.isLiteMode) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    color = EmeraldGreenLight,
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier
                                        .clickable { onOpenLiteModeInfo() }
                                        .testTag("top_lite_badge")
                                ) {
                                    Text(
                                        text = "⚡ LITE",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = EmeraldGreen,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                        Text(
                            text = if (state.isLiteMode) "Offline Crux • Fast & Zero Data" else "NCERT & CBSE Board Companion",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (state.isLiteMode) EmeraldGreen else SaffronPrimary,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                // Right Actions: Search & AI Tutor shortcut
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    // Search toggle button
                    IconButton(
                        onClick = { isSearchExpanded = !isSearchExpanded },
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("toggle_search_button")
                    ) {
                        Icon(
                            imageVector = if (isSearchExpanded) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = "Search",
                            tint = if (isSearchExpanded) SaffronPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    // Dynamic Theme Toggle Button (Day / Night Low-Light Mode)
                    IconButton(
                        onClick = onToggleThemeMode,
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("top_bar_theme_toggle_btn")
                    ) {
                        Icon(
                            imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = if (isDark) "Switch to Light Mode" else "Switch to Dark Mode (Night Reading)",
                            tint = if (isDark) AmberGold else PeacockIndigo,
                            modifier = Modifier.size(21.dp)
                        )
                    }

                    // Universal APK Download shortcut
                    IconButton(
                        onClick = onOpenApkDownload,
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("top_bar_apk_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.InstallMobile,
                            contentDescription = "Universal Android APK & Sideload",
                            tint = SaffronPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // AI Tutor Shortcut
                    if (onAiTutorClick != null) {
                        IconButton(
                            onClick = onAiTutorClick,
                            modifier = Modifier
                                .size(38.dp)
                                .testTag("top_bar_ai_tutor_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "Vidya AI Tutor",
                                tint = if (state.activeTab == AppTab.AI_TUTOR) SaffronPrimary else PeacockIndigo,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                    // Info / Settings button
                    IconButton(
                        onClick = onOpenLiteModeInfo,
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("top_bar_info_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "App Info & Lite Mode",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Row 2: Secondary Quick Filter Bar (Horizontally scrollable for all screen sizes)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                // Dynamic Theme Mode Selector Chip
                Box {
                    Surface(
                        color = if (isDark) DarkSurfaceElevated else SurfaceContainerHigh,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { showThemeMenu = true }
                            .testTag("theme_mode_chip")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Icon(
                                imageVector = if (isDark) Icons.Default.DarkMode else Icons.Default.LightMode,
                                contentDescription = null,
                                tint = if (isDark) AmberGold else SaffronPrimary,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = when (state.themeMode) {
                                    ThemeMode.DARK -> "Dark Mode"
                                    ThemeMode.LIGHT -> "Light Mode"
                                    ThemeMode.SYSTEM -> if (isDark) "Auto (Dark)" else "Auto (Light)"
                                },
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = showThemeMenu,
                        onDismissRequest = { showThemeMenu = false }
                    ) {
                        ThemeMode.values().forEach { mode ->
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(
                                            text = mode.displayName,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = if (state.themeMode == mode) FontWeight.Bold else FontWeight.Normal
                                        )
                                        Text(
                                            text = mode.subtitle,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                },
                                onClick = {
                                    onSetThemeMode(mode)
                                    showThemeMenu = false
                                },
                                leadingIcon = {
                                    val icon = when (mode) {
                                        ThemeMode.SYSTEM -> Icons.Default.SettingsBrightness
                                        ThemeMode.LIGHT -> Icons.Default.LightMode
                                        ThemeMode.DARK -> Icons.Default.DarkMode
                                    }
                                    val tint = when (mode) {
                                        ThemeMode.SYSTEM -> MaterialTheme.colorScheme.primary
                                        ThemeMode.LIGHT -> SaffronPrimary
                                        ThemeMode.DARK -> AmberGold
                                    }
                                    Icon(icon, contentDescription = null, tint = tint)
                                },
                                trailingIcon = {
                                    if (state.themeMode == mode) {
                                        Icon(Icons.Default.Check, contentDescription = null, tint = SaffronPrimary)
                                    }
                                }
                            )
                        }
                    }
                }

                // Get Universal APK Chip
                Surface(
                    color = SaffronPrimary.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(32.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { onOpenApkDownload() }
                        .testTag("top_bar_get_apk_chip")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.GetApp,
                            contentDescription = null,
                            tint = SaffronPrimary,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Get APK",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaffronPrimary
                        )
                    }
                }

                // Live Network Status Chip (Auto-syncs with internet state)
                Surface(
                    color = if (state.isNetworkAvailable) EmeraldGreenLight.copy(alpha = 0.6f) else AmberWarningLight,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(32.dp)
                        .testTag("network_status_chip")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(if (state.isNetworkAvailable) EmeraldGreen else AmberWarning)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = if (state.isNetworkAvailable) "Online" else "Offline",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (state.isNetworkAvailable) EmeraldGreen else AmberWarning
                        )
                    }
                }

                // Lite Mode Toggle Chip
                Surface(
                    color = if (state.isLiteMode) EmeraldGreenLight else SurfaceContainerHigh,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(32.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { onToggleLiteMode() }
                        .testTag("toggle_lite_mode_btn")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Icon(
                            imageVector = if (state.isLiteMode) Icons.Default.Bolt else Icons.Default.AllInclusive,
                            contentDescription = null,
                            tint = if (state.isLiteMode) EmeraldGreen else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = if (state.isLiteMode) "Lite Mode" else "Full Edition",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (state.isLiteMode) EmeraldGreen else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Class Grade Selector Chip
                Box {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { showGradeMenu = true }
                            .testTag("grade_selector_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = state.selectedGrade.displayName,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(16.dp)
                            )
                        }
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

                // Board Selector Chip
                Box {
                    Surface(
                        color = PeacockIndigoLight,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { showBoardMenu = true }
                            .testTag("board_selector_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = state.selectedBoard.shortName,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = PeacockIndigo
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = PeacockIndigo,
                                modifier = Modifier.size(16.dp)
                            )
                        }
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

                // Active Recall Cover Mode Toggle (visible when on Notebook tab)
                if (state.activeTab == AppTab.NOTEBOOK) {
                    Surface(
                        color = if (state.isCoverRecallModeActive) SaffronLight else SurfaceContainerHigh,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { onToggleRecallMode() }
                            .testTag("toggle_active_recall_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Icon(
                                imageVector = if (state.isCoverRecallModeActive) Icons.Filled.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = null,
                                tint = if (state.isCoverRecallModeActive) SaffronPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (state.isCoverRecallModeActive) "Recall: ON" else "Recall: OFF",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (state.isCoverRecallModeActive) SaffronPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Animated Network Notice Banner (Appears when network state changes or modes switch)
            AnimatedVisibility(visible = state.networkNoticeMessage != null) {
                Surface(
                    color = if (state.isNetworkAvailable) EmeraldGreenLight else AmberWarningLight,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .testTag("network_notice_banner")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = if (state.isNetworkAvailable) Icons.Default.Wifi else Icons.Default.WifiOff,
                                contentDescription = null,
                                tint = if (state.isNetworkAvailable) EmeraldGreen else AmberWarning,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = state.networkNoticeMessage ?: "",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = if (state.isNetworkAvailable) EmeraldGreen else AmberWarning
                            )
                        }
                        IconButton(
                            onClick = onDismissNetworkNotice,
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Dismiss notice",
                                tint = if (state.isNetworkAvailable) EmeraldGreen else AmberWarning,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                }
            }

            // Row 3: Animated Search Input
            AnimatedVisibility(visible = isSearchExpanded) {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = { Text("Search chapters, formulas, mnemonics...") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    trailingIcon = {
                        if (state.searchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchQueryChange("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear search")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .testTag("top_search_text_field")
                )
            }
        }
    }
}
