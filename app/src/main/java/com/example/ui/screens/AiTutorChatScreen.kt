package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.theme.*
import com.example.viewmodel.MainUiState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiTutorChatScreen(
    state: MainUiState,
    onSendMessage: (String) -> Unit,
    onSetPersona: (AiTutorPersona) -> Unit,
    onToggleSearchGrounding: () -> Unit,
    onInputTextChange: (String) -> Unit,
    onClearChat: () -> Unit,
    onRetry: () -> Unit,
    onSaveToNotes: (String, String) -> Unit,
    onClearChapterContext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    // Auto-scroll to bottom on new message
    LaunchedEffect(state.chatMessages.size, state.isAiGenerating) {
        if (state.chatMessages.isNotEmpty()) {
            listState.animateScrollToItem(state.chatMessages.size - 1)
        }
    }

    val quickQuestions = remember(state.selectedGrade) {
        listOf(
            "📋 Explain CBSE 5-mark marking scheme for ${state.selectedGrade.displayName}",
            "🔢 Solve a numerical step-by-step with formulas & working notes",
            "🌐 What are latest CBSE 2025-2026 syllabus updates & date sheets?",
            "📝 Turn this into Cornell cues & summary for quick revision",
            "⚠️ Top 3 common board exam mistakes and traps to avoid"
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("ai_tutor_chat_screen")
    ) {
        // Top Header
        Surface(
            tonalElevation = 2.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(listOf(SaffronPrimary, PeacockIndigo))
                                )
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
                                text = "Vidya AI Educator",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "${state.selectedGrade.displayName} • ${state.selectedBoard.shortName}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = EmeraldGreen.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = state.selectedAiPersona.modelId,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = EmeraldGreen,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 9.sp,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                    )
                                }
                            }
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onClearChat,
                            modifier = Modifier.testTag("btn_clear_ai_chat")
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.DeleteSweep,
                                contentDescription = "Clear Chat",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Persona Selector Chips
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    AiTutorPersona.values().forEach { persona ->
                        val isSelected = state.selectedAiPersona == persona
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSetPersona(persona) },
                            label = {
                                Text(
                                    text = persona.title,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            leadingIcon = {
                                when (persona) {
                                    AiTutorPersona.GENERAL_CBSE -> Icon(Icons.Default.Psychology, contentDescription = null, modifier = Modifier.size(14.dp))
                                    AiTutorPersona.STEM_PRO_SOLVER -> Icon(Icons.Default.Functions, contentDescription = null, modifier = Modifier.size(14.dp))
                                    AiTutorPersona.LIVE_SEARCH -> Icon(Icons.Default.TravelExplore, contentDescription = null, modifier = Modifier.size(14.dp))
                                    AiTutorPersona.FAST_REVISION -> Icon(Icons.Default.Bolt, contentDescription = null, modifier = Modifier.size(14.dp))
                                }
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SaffronPrimary.copy(alpha = 0.15f),
                                selectedLabelColor = SaffronPrimary,
                                selectedLeadingIconColor = SaffronPrimary
                            ),
                            modifier = Modifier.testTag("chip_persona_${persona.name.lowercase()}")
                        )
                    }
                }

                // Google Search Grounding toggle bar
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onToggleSearchGrounding() }
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                    ) {
                        Icon(
                            imageVector = if (state.isAiSearchGroundingEnabled) Icons.Filled.CheckCircle else Icons.Outlined.TravelExplore,
                            contentDescription = null,
                            tint = if (state.isAiSearchGroundingEnabled) Color(0xFF4285F4) else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Google Search Grounding (Live Web)",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = if (state.isAiSearchGroundingEnabled) FontWeight.Bold else FontWeight.Normal,
                            color = if (state.isAiSearchGroundingEnabled) Color(0xFF4285F4) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Switch(
                        checked = state.isAiSearchGroundingEnabled,
                        onCheckedChange = { onToggleSearchGrounding() },
                        modifier = Modifier
                            .height(24.dp)
                            .testTag("switch_search_grounding")
                    )
                }

                // Chapter context banner if active
                if (state.aiContextChapter != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Chapter Context: ${state.aiContextChapter.title}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                            IconButton(
                                onClick = onClearChapterContext,
                                modifier = Modifier.size(20.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear chapter context",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Chat Message Thread
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .testTag("chat_messages_list")
        ) {
            // Quick suggestions header when conversation is short
            if (state.chatMessages.size <= 2) {
                item {
                    Column(modifier = Modifier.padding(bottom = 6.dp)) {
                        Text(
                            text = "💡 Quick Prompts for ${state.selectedGrade.displayName}:",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            quickQuestions.forEach { prompt ->
                                SuggestionChip(
                                    onClick = { onSendMessage(prompt) },
                                    label = {
                                        Text(
                                            text = prompt,
                                            style = MaterialTheme.typography.labelSmall,
                                            maxLines = 1
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

            items(state.chatMessages, key = { it.id }) { message ->
                ChatMessageItem(
                    message = message,
                    onCopy = {
                        clipboardManager.setText(AnnotatedString(message.text))
                        Toast.makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    onSaveToNotes = {
                        val title = "AI Notes: ${message.text.lines().firstOrNull { it.isNotBlank() }?.take(40) ?: "Topic Concept"}"
                        onSaveToNotes(title, message.text)
                    },
                    onRetry = onRetry,
                    onOpenUrl = { url ->
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "Could not open link: $url", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }

        // Bottom Input Field
        Surface(
            tonalElevation = 6.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = state.aiInputText,
                        onValueChange = onInputTextChange,
                        placeholder = {
                            Text(
                                text = "Ask Vidya AI any concept, numerical, or doubt...",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        shape = RoundedCornerShape(24.dp),
                        maxLines = 4,
                        enabled = !state.isAiGenerating,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = {
                            if (state.aiInputText.isNotBlank()) {
                                onSendMessage(state.aiInputText)
                            }
                        }),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SaffronPrimary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("ai_input_text_field")
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = {
                            if (state.aiInputText.isNotBlank()) {
                                onSendMessage(state.aiInputText)
                            }
                        },
                        enabled = state.aiInputText.isNotBlank() && !state.isAiGenerating,
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = SaffronPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .testTag("btn_send_ai_message")
                    ) {
                        if (state.isAiGenerating) {
                            CircularProgressIndicator(
                                strokeWidth = 2.dp,
                                color = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Send message",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatMessageItem(
    message: ChatMessage,
    onCopy: () -> Unit,
    onSaveToNotes: () -> Unit,
    onRetry: () -> Unit,
    onOpenUrl: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isUser = message.sender == ChatSender.USER
    val alignment = if (isUser) Alignment.End else Alignment.Start

    Column(
        horizontalAlignment = alignment,
        modifier = modifier
            .fillMaxWidth()
            .testTag(if (isUser) "user_message_bubble" else "ai_message_bubble")
    ) {
        Row(
            horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth(if (isUser) 0.92f else 1f)
        ) {
            if (!isUser) {
                // AI Avatar
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(listOf(SaffronPrimary, PeacockIndigo))
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }

            Column(modifier = Modifier.weight(1f, fill = false)) {
                // Header (Persona name / Model tag for AI)
                if (!isUser) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = message.personaName ?: "Vidya AI",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaffronPrimary
                        )
                        if (!message.modelUsed.isNullOrBlank()) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant
                            ) {
                                Text(
                                    text = message.modelUsed,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontSize = 9.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                )
                            }
                        }
                        if (message.isSearchGrounded) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = Color(0xFF4285F4).copy(alpha = 0.15f)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.TravelExplore,
                                        contentDescription = null,
                                        tint = Color(0xFF4285F4),
                                        modifier = Modifier.size(10.dp)
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Text(
                                        text = "Google Search",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 9.sp,
                                        color = Color(0xFF4285F4),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                // Bubble Container
                Surface(
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isUser) 16.dp else 4.dp,
                        bottomEnd = if (isUser) 4.dp else 16.dp
                    ),
                    color = when {
                        isUser -> SaffronPrimary
                        message.isError -> MaterialTheme.colorScheme.errorContainer
                        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
                    },
                    border = if (!isUser && !message.isError) {
                        BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
                    } else null,
                    modifier = Modifier.widthIn(max = 580.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
                        if (message.isLoading) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CircularProgressIndicator(
                                    strokeWidth = 2.dp,
                                    color = SaffronPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = message.text,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        } else {
                            Text(
                                text = message.text,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (isUser) Color.White else if (message.isError) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onSurface
                            )
                        }

                        // Search Grounding Source Citations
                        if (message.groundingSources.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "🌐 Web Search Sources & Grounding:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4285F4)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())
                            ) {
                                message.groundingSources.forEach { source ->
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = Color(0xFF4285F4).copy(alpha = 0.1f),
                                        modifier = Modifier.clickable { onOpenUrl(source.url) }
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.OpenInNew,
                                                contentDescription = null,
                                                tint = Color(0xFF4285F4),
                                                modifier = Modifier.size(12.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = source.title,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = Color(0xFF4285F4),
                                                maxLines = 1,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // AI Action Footer (Copy, Save to Notes, Retry)
                if (!isUser && !message.isLoading) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        TextButton(
                            onClick = onCopy,
                            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Icon(Icons.Outlined.ContentCopy, contentDescription = "Copy", modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Copy", style = MaterialTheme.typography.labelSmall)
                        }

                        TextButton(
                            onClick = onSaveToNotes,
                            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Icon(Icons.Outlined.EditNote, contentDescription = "Save to Notes", tint = SaffronPrimary, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Save to Notes", style = MaterialTheme.typography.labelSmall, color = SaffronPrimary, fontWeight = FontWeight.Bold)
                        }

                        if (message.isError) {
                            TextButton(
                                onClick = onRetry,
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Icon(Icons.Outlined.Refresh, contentDescription = "Retry", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Retry", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.error)
                            }
                        }

                        val timeStr = remember(message.timestamp) {
                            SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(message.timestamp))
                        }
                        Text(
                            text = timeStr,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            fontSize = 10.sp,
                            modifier = Modifier.padding(start = 6.dp)
                        )
                    }
                }
            }
        }
    }
}
