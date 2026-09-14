package com.example.ui.components

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.PdfExportMode
import com.example.model.TestPaperItem
import com.example.ui.theme.*
import com.example.util.PaperPdfGenerator
import java.io.File

@Composable
fun PdfExportDialog(
    paper: TestPaperItem,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var selectedMode by remember { mutableStateOf(PdfExportMode.STUDENT_QUESTION_PAPER) }
    var isGeneratingPdf by remember { mutableStateOf(false) }
    var generatedResult by remember { mutableStateOf<PaperPdfGenerator.PdfGenerationResult?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Auto-generate PDF on dialog open or mode change
    LaunchedEffect(selectedMode) {
        isGeneratingPdf = true
        errorMessage = null
        val result = PaperPdfGenerator.generatePdf(context, paper, selectedMode)
        isGeneratingPdf = false
        if (result.isSuccess) {
            generatedResult = result.getOrNull()
        } else {
            errorMessage = result.exceptionOrNull()?.message ?: "Failed to generate PDF"
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .testTag("pdf_export_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFFF0000).copy(alpha = 0.12f),
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.PictureAsPdf,
                                    contentDescription = null,
                                    tint = Color(0xFFDC2626),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Export Board Paper PDF",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "A4 Printable Document Pattern",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(12.dp))

                // Paper Summary Card
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = paper.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = PeacockIndigo.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "${paper.board.shortName} • ${paper.grade.displayName}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = PeacockIndigo,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }

                            Text(
                                text = "Max Marks: ${paper.maxMarks} • Time: ${paper.timeAllowedMinutes}m",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Mode Selector
                Text(
                    text = "Select Document Format:",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(6.dp))

                PdfExportMode.values().forEach { mode ->
                    val isSelected = selectedMode == mode
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) SaffronPrimary.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                        border = BorderStroke(
                            1.dp,
                            if (isSelected) SaffronPrimary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                            .clickable { selectedMode = mode }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { selectedMode = mode },
                                colors = RadioButtonDefaults.colors(selectedColor = SaffronPrimary)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(
                                    text = mode.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = mode.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Generation status info
                if (isGeneratingPdf) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    ) {
                        CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            color = SaffronPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Rendering A4 board exam layout...",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else if (generatedResult != null) {
                    val result = generatedResult!!
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = EmeraldGreen.copy(alpha = 0.1f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "PDF Ready: ${result.pageCount} Pages • ${result.fileSizeFormatted}",
                                style = MaterialTheme.typography.bodySmall,
                                color = EmeraldGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                } else if (errorMessage != null) {
                    Text(
                        text = "Error: $errorMessage",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(14.dp))

                // Action Buttons
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Open PDF button
                        OutlinedButton(
                            onClick = {
                                generatedResult?.file?.let { file ->
                                    PaperPdfGenerator.openPdf(context, file)
                                }
                            },
                            enabled = generatedResult != null && !isGeneratingPdf,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f).testTag("open_pdf_viewer_btn")
                        ) {
                            Icon(Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Open View", style = MaterialTheme.typography.labelMedium)
                        }

                        // Share PDF button
                        OutlinedButton(
                            onClick = {
                                generatedResult?.file?.let { file ->
                                    PaperPdfGenerator.sharePdf(context, file, paper.title)
                                }
                            },
                            enabled = generatedResult != null && !isGeneratingPdf,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f).testTag("share_pdf_btn")
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Share PDF", style = MaterialTheme.typography.labelMedium)
                        }
                    }

                    // Print / Save to Device Button
                    Button(
                        onClick = {
                            generatedResult?.file?.let { file ->
                                PaperPdfGenerator.printPdf(context, file, paper.title)
                            }
                        },
                        enabled = generatedResult != null && !isGeneratingPdf,
                        colors = ButtonDefaults.buttonColors(containerColor = SaffronPrimary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().testTag("print_save_pdf_btn")
                    ) {
                        Icon(Icons.Default.Print, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Print / Save as PDF", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
