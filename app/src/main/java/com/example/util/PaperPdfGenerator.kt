package com.example.util

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.PrintManager
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.model.PdfExportMode
import com.example.model.QuestionType
import com.example.model.TestPaperItem
import com.example.model.TestQuestion
import com.example.model.TestSection
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PaperPdfGenerator {

    private const val PAGE_WIDTH = 595 // A4 standard width (points)
    private const val PAGE_HEIGHT = 842 // A4 standard height (points)
    private const val MARGIN_LEFT = 40f
    private const val MARGIN_RIGHT = 555f
    private const val CONTENT_WIDTH = MARGIN_RIGHT - MARGIN_LEFT // 515f
    private const val MARGIN_TOP = 40f
    private const val MARGIN_BOTTOM = 800f

    data class PdfGenerationResult(
        val file: File,
        val pageCount: Int,
        val fileSizeFormatted: String
    )

    fun generatePdf(
        context: Context,
        paper: TestPaperItem,
        mode: PdfExportMode = PdfExportMode.STUDENT_QUESTION_PAPER
    ): Result<PdfGenerationResult> {
        return try {
            val pdfDocument = PdfDocument()

            // Prepare output folder
            val outputDir = File(context.cacheDir, "generated_test_papers")
            if (!outputDir.exists()) {
                outputDir.mkdirs()
            }
            val sanitizedTitle = paper.title.replace("[^a-zA-Z0-9_-]".toRegex(), "_")
            val modeSuffix = if (mode == PdfExportMode.STUDENT_QUESTION_PAPER) "QP" else "MS_Solutions"
            val outputFile = File(outputDir, "${sanitizedTitle}_${modeSuffix}.pdf")

            // Initialize Paints
            val textPaint = Paint().apply {
                color = Color.BLACK
                textSize = 9.5f
                typeface = Typeface.create(Typeface.SERIF, Typeface.NORMAL)
                isAntiAlias = true
            }

            val boldPaint = Paint().apply {
                color = Color.BLACK
                textSize = 9.5f
                typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
                isAntiAlias = true
            }

            val titlePaint = Paint().apply {
                color = Color.rgb(20, 25, 40)
                textSize = 12.5f
                typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
            }

            val subTitlePaint = Paint().apply {
                color = Color.rgb(40, 50, 70)
                textSize = 10f
                typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
            }

            val smallPaint = Paint().apply {
                color = Color.rgb(70, 80, 95)
                textSize = 8.5f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                isAntiAlias = true
            }

            val solutionHeaderPaint = Paint().apply {
                color = Color.rgb(194, 65, 12) // Saffron dark
                textSize = 8.5f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            }

            val solutionBodyPaint = Paint().apply {
                color = Color.rgb(35, 45, 60)
                textSize = 8.5f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                isAntiAlias = true
            }

            val linePaint = Paint().apply {
                color = Color.rgb(60, 60, 60)
                strokeWidth = 0.8f
                style = Paint.Style.STROKE
            }

            val doubleLinePaint = Paint().apply {
                color = Color.BLACK
                strokeWidth = 1.4f
                style = Paint.Style.STROKE
            }

            val boxPaint = Paint().apply {
                color = Color.rgb(245, 247, 250)
                style = Paint.Style.FILL
            }

            val boxBorderPaint = Paint().apply {
                color = Color.rgb(205, 215, 225)
                strokeWidth = 0.8f
                style = Paint.Style.STROKE
            }

            var currentPageNumber = 1
            var pageInfo = PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, currentPageNumber).create()
            var currentPage = pdfDocument.startPage(pageInfo)
            var canvas = currentPage.canvas
            var currentY = MARGIN_TOP

            fun checkPageBreak(requiredHeight: Float) {
                if (currentY + requiredHeight > MARGIN_BOTTOM) {
                    // Draw bottom page number on ending page
                    val pageText = "Page $currentPageNumber"
                    val pageTextWidth = textPaint.measureText(pageText)
                    canvas.drawText(pageText, (PAGE_WIDTH - pageTextWidth) / 2f, PAGE_HEIGHT - 25f, textPaint)
                    canvas.drawLine(MARGIN_LEFT, PAGE_HEIGHT - 35f, MARGIN_RIGHT, PAGE_HEIGHT - 35f, linePaint)

                    pdfDocument.finishPage(currentPage)

                    currentPageNumber++
                    pageInfo = PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, currentPageNumber).create()
                    currentPage = pdfDocument.startPage(pageInfo)
                    canvas = currentPage.canvas
                    currentY = MARGIN_TOP

                    // Mini running header for subsequent pages
                    canvas.drawText(
                        "${paper.board.displayName} | ${paper.subject.displayName} - ${paper.grade.displayName}",
                        MARGIN_LEFT,
                        currentY,
                        smallPaint
                    )
                    val modeTag = if (mode == PdfExportMode.STUDENT_QUESTION_PAPER) "Question Paper" else "Marking Scheme & Solutions"
                    val tagWidth = smallPaint.measureText(modeTag)
                    canvas.drawText(modeTag, MARGIN_RIGHT - tagWidth, currentY, smallPaint)
                    currentY += 8f
                    canvas.drawLine(MARGIN_LEFT, currentY, MARGIN_RIGHT, currentY, linePaint)
                    currentY += 16f
                }
            }

            // ====================================================
            // 1. OFFICIAL BOARD HEADER (PAGE 1)
            // ====================================================
            val boardTitle = when (paper.board.name) {
                "CBSE" -> "CENTRAL BOARD OF SECONDARY EDUCATION"
                "ICSE" -> "COUNCIL FOR THE INDIAN SCHOOL CERTIFICATE EXAMINATIONS"
                else -> "${paper.board.displayName.uppercase(Locale.ROOT)} EXAMINATION"
            }
            canvas.drawText(boardTitle, PAGE_WIDTH / 2f, currentY, titlePaint)
            currentY += 15f

            val examSubtitle = "ALL INDIA SENIOR SCHOOL / SECONDARY EXAMINATION 2025-26"
            canvas.drawText(examSubtitle, PAGE_WIDTH / 2f, currentY, subTitlePaint)
            currentY += 16f

            // Subject and Code Banner
            val subjectTitle = "${paper.subject.displayName.uppercase(Locale.ROOT)} (${paper.grade.displayName.uppercase(Locale.ROOT)})"
            canvas.drawText(subjectTitle, PAGE_WIDTH / 2f, currentY, titlePaint)
            currentY += 16f

            // Mode Watermark Banner if Marking Scheme
            if (mode == PdfExportMode.EVALUATOR_MARKING_SCHEME) {
                val bannerPaint = Paint().apply {
                    color = Color.rgb(255, 247, 237)
                    style = Paint.Style.FILL
                }
                val bannerBorder = Paint().apply {
                    color = Color.rgb(234, 88, 12)
                    strokeWidth = 1f
                    style = Paint.Style.STROKE
                }
                canvas.drawRect(MARGIN_LEFT, currentY, MARGIN_RIGHT, currentY + 18f, bannerPaint)
                canvas.drawRect(MARGIN_LEFT, currentY, MARGIN_RIGHT, currentY + 18f, bannerBorder)
                val msNoticePaint = Paint().apply {
                    color = Color.rgb(194, 65, 12)
                    textSize = 9f
                    typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    textAlign = Paint.Align.CENTER
                }
                canvas.drawText("OFFICIAL MARKING SCHEME & STEP-BY-STEP EVALUATION RUBRIC", PAGE_WIDTH / 2f, currentY + 13f, msNoticePaint)
                currentY += 24f
            }

            // Roll Number & Paper Series Box
            val rollBoxTop = currentY
            val rollBoxBottom = currentY + 36f
            canvas.drawRect(MARGIN_LEFT, rollBoxTop, MARGIN_LEFT + 260f, rollBoxBottom, boxBorderPaint)

            // Roll No. grid
            canvas.drawText("Roll No.", MARGIN_LEFT + 8f, rollBoxTop + 14f, boldPaint)
            val boxStartX = MARGIN_LEFT + 55f
            val boxY = rollBoxTop + 6f
            val boxSize = 16f
            for (i in 0 until 9) {
                canvas.drawRect(boxStartX + (i * 20f), boxY, boxStartX + (i * 20f) + boxSize, boxY + boxSize, boxBorderPaint)
            }
            canvas.drawText("Write Candidate Roll No. as per Admit Card", MARGIN_LEFT + 8f, rollBoxTop + 30f, smallPaint)

            // Q.P. Code & Series on the right
            canvas.drawRect(MARGIN_RIGHT - 180f, rollBoxTop, MARGIN_RIGHT, rollBoxBottom, boxBorderPaint)
            canvas.drawText("Series / Q.P. Code: SQP-2026/01", MARGIN_RIGHT - 172f, rollBoxTop + 14f, boldPaint)
            canvas.drawText("Set: 1 • Code: ${paper.subject.name.take(3)}/01", MARGIN_RIGHT - 172f, rollBoxTop + 28f, smallPaint)

            currentY = rollBoxBottom + 12f

            // Time and Marks Line
            canvas.drawLine(MARGIN_LEFT, currentY, MARGIN_RIGHT, currentY, doubleLinePaint)
            currentY += 14f

            canvas.drawText("Time Allowed: ${paper.timeAllowedMinutes / 60} Hours (${paper.timeAllowedMinutes} Minutes)", MARGIN_LEFT, currentY, boldPaint)
            val marksText = "Maximum Marks: ${paper.maxMarks}"
            val marksWidth = boldPaint.measureText(marksText)
            canvas.drawText(marksText, MARGIN_RIGHT - marksWidth, currentY, boldPaint)

            currentY += 8f
            canvas.drawLine(MARGIN_LEFT, currentY, MARGIN_RIGHT, currentY, doubleLinePaint)
            currentY += 16f

            // General Instructions
            canvas.drawText("General Instructions:", MARGIN_LEFT, currentY, boldPaint)
            currentY += 13f

            for ((idx, instruction) in paper.generalInstructions.withIndex()) {
                val bullet = "${idx + 1}. "
                val fullText = bullet + instruction
                val wrappedLines = wrapText(fullText, CONTENT_WIDTH, textPaint)
                for (line in wrappedLines) {
                    checkPageBreak(12f)
                    canvas.drawText(line, MARGIN_LEFT + 6f, currentY, textPaint)
                    currentY += 12f
                }
            }

            currentY += 8f
            canvas.drawLine(MARGIN_LEFT, currentY, MARGIN_RIGHT, currentY, linePaint)
            currentY += 14f

            // ====================================================
            // 2. TEST SECTIONS & QUESTIONS
            // ====================================================
            for (section in paper.sections) {
                // Section Header
                checkPageBreak(35f)

                // Draw section background banner
                val secBannerHeight = 22f
                canvas.drawRect(MARGIN_LEFT, currentY, MARGIN_RIGHT, currentY + secBannerHeight, boxPaint)
                canvas.drawRect(MARGIN_LEFT, currentY, MARGIN_RIGHT, currentY + secBannerHeight, boxBorderPaint)

                canvas.drawText(section.sectionName.uppercase(Locale.ROOT), MARGIN_LEFT + 8f, currentY + 15f, boldPaint)
                currentY += secBannerHeight + 6f

                if (section.instructions.isNotBlank()) {
                    val instLines = wrapText("Note: ${section.instructions}", CONTENT_WIDTH - 12f, smallPaint)
                    for (line in instLines) {
                        checkPageBreak(12f)
                        canvas.drawText(line, MARGIN_LEFT + 6f, currentY, smallPaint)
                        currentY += 11f
                    }
                    currentY += 4f
                }

                // Render questions in section
                for (question in section.questions) {
                    renderQuestion(
                        canvas = canvas,
                        question = question,
                        mode = mode,
                        currentY = currentY,
                        onYChanged = { currentY = it },
                        onCheckPageBreak = { checkPageBreak(it) },
                        textPaint = textPaint,
                        boldPaint = boldPaint,
                        smallPaint = smallPaint,
                        solutionHeaderPaint = solutionHeaderPaint,
                        solutionBodyPaint = solutionBodyPaint,
                        boxPaint = boxPaint,
                        boxBorderPaint = boxBorderPaint,
                        linePaint = linePaint
                    )
                }

                currentY += 10f
            }

            // End of paper marker
            checkPageBreak(40f)
            currentY += 10f
            canvas.drawLine(MARGIN_LEFT, currentY, MARGIN_RIGHT, currentY, doubleLinePaint)
            currentY += 16f
            val endText = "*** END OF QUESTION PAPER ***"
            val endWidth = boldPaint.measureText(endText)
            canvas.drawText(endText, (PAGE_WIDTH - endWidth) / 2f, currentY, boldPaint)

            // Final bottom page number
            val finalPageText = "Page $currentPageNumber"
            val finalWidth = textPaint.measureText(finalPageText)
            canvas.drawText(finalPageText, (PAGE_WIDTH - finalWidth) / 2f, PAGE_HEIGHT - 25f, textPaint)
            canvas.drawLine(MARGIN_LEFT, PAGE_HEIGHT - 35f, MARGIN_RIGHT, PAGE_HEIGHT - 35f, linePaint)

            pdfDocument.finishPage(currentPage)

            // Save PDF to file
            FileOutputStream(outputFile).use { fos ->
                pdfDocument.writeTo(fos)
            }
            pdfDocument.close()

            val fileSizeKb = outputFile.length() / 1024
            val sizeStr = if (fileSizeKb > 1024) String.format(Locale.ROOT, "%.1f MB", fileSizeKb / 1024f) else "$fileSizeKb KB"

            Result.success(PdfGenerationResult(outputFile, currentPageNumber, sizeStr))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private fun renderQuestion(
        canvas: Canvas,
        question: TestQuestion,
        mode: PdfExportMode,
        currentY: Float,
        onYChanged: (Float) -> Unit,
        onCheckPageBreak: (Float) -> Unit,
        textPaint: Paint,
        boldPaint: Paint,
        smallPaint: Paint,
        solutionHeaderPaint: Paint,
        solutionBodyPaint: Paint,
        boxPaint: Paint,
        boxBorderPaint: Paint,
        linePaint: Paint
    ) {
        var y = currentY

        // Estimate question block height to pre-check page break
        onCheckPageBreak(40f)

        // Case Passage if available
        if (!question.casePassage.isNullOrBlank()) {
            onCheckPageBreak(30f)
            val passageLines = wrapText(question.casePassage, CONTENT_WIDTH - 20f, smallPaint)
            val boxHeight = (passageLines.size * 12f) + 16f

            canvas.drawRect(MARGIN_LEFT + 4f, y, MARGIN_RIGHT - 4f, y + boxHeight, boxPaint)
            canvas.drawRect(MARGIN_LEFT + 4f, y, MARGIN_RIGHT - 4f, y + boxHeight, boxBorderPaint)

            canvas.drawText("Read the following passage and answer the questions that follow:", MARGIN_LEFT + 10f, y + 12f, boldPaint)
            var passageY = y + 24f
            for (line in passageLines) {
                canvas.drawText(line, MARGIN_LEFT + 10f, passageY, smallPaint)
                passageY += 12f
            }
            y += boxHeight + 10f
        }

        // Question header: Q.No and Marks on the right
        val qNumberText = "Q${question.questionNumber}."
        canvas.drawText(qNumberText, MARGIN_LEFT, y, boldPaint)

        val marksText = "[${question.marks} Mark${if (question.marks > 1) "s" else ""}]"
        val marksWidth = boldPaint.measureText(marksText)
        canvas.drawText(marksText, MARGIN_RIGHT - marksWidth, y, boldPaint)

        // Question Text (indented after Q.No)
        val qIndent = 26f
        val qWidth = CONTENT_WIDTH - qIndent - marksWidth - 8f
        val qLines = wrapText(question.questionText, qWidth, textPaint)

        var isFirstLine = true
        for (line in qLines) {
            if (!isFirstLine) {
                onCheckPageBreak(13f)
            }
            canvas.drawText(line, MARGIN_LEFT + qIndent, y, textPaint)
            y += 13f
            isFirstLine = false
        }

        // MCQ Options
        if (!question.options.isNullOrEmpty()) {
            y += 2f
            val optionLabels = listOf("(a)", "(b)", "(c)", "(d)")
            for ((optIdx, option) in question.options.withIndex()) {
                val label = optionLabels.getOrElse(optIdx) { "(${optIdx + 1})" }
                val optText = "$label $option"
                val optLines = wrapText(optText, CONTENT_WIDTH - 35f, textPaint)
                for (optLine in optLines) {
                    onCheckPageBreak(12f)
                    canvas.drawText(optLine, MARGIN_LEFT + qIndent + 4f, y, textPaint)
                    y += 12f
                }
            }
        }

        // If Marking Scheme mode, render Model Answer & Steps
        if (mode == PdfExportMode.EVALUATOR_MARKING_SCHEME) {
            y += 4f
            onCheckPageBreak(30f)

            // Solution Container Box
            val solStartY = y
            val modelLines = wrapText(question.modelAnswer, CONTENT_WIDTH - 30f, solutionBodyPaint)
            val stepsHeight = question.cbseMarkingScheme.size * 13f
            val totalSolHeight = 22f + (modelLines.size * 11.5f) + stepsHeight + 20f

            canvas.drawRect(MARGIN_LEFT + 15f, solStartY, MARGIN_RIGHT - 5f, solStartY + totalSolHeight, boxPaint)
            canvas.drawRect(MARGIN_LEFT + 15f, solStartY, MARGIN_RIGHT - 5f, solStartY + totalSolHeight, boxBorderPaint)

            y += 14f
            canvas.drawText("CBSE MARKING SCHEME & MODEL SOLUTION:", MARGIN_LEFT + 22f, y, solutionHeaderPaint)
            y += 13f

            for (mLine in modelLines) {
                canvas.drawText(mLine, MARGIN_LEFT + 22f, y, solutionBodyPaint)
                y += 11.5f
            }

            if (question.cbseMarkingScheme.isNotEmpty()) {
                y += 4f
                canvas.drawText("Step-by-Step Marks Distribution:", MARGIN_LEFT + 22f, y, boldPaint)
                y += 12f
                for (step in question.cbseMarkingScheme) {
                    val stepText = "• ${step.stepDescription}"
                    val stepLines = wrapText(stepText, CONTENT_WIDTH - 120f, solutionBodyPaint)
                    for ((sIdx, sLine) in stepLines.withIndex()) {
                        canvas.drawText(sLine, MARGIN_LEFT + 28f, y, solutionBodyPaint)
                        if (sIdx == 0) {
                            val markBadge = "[${step.marksAllocated}]"
                            val badgeWidth = solutionHeaderPaint.measureText(markBadge)
                            canvas.drawText(markBadge, MARGIN_RIGHT - 15f - badgeWidth, y, solutionHeaderPaint)
                        }
                        y += 12f
                    }
                }
            }

            if (question.topperTip.isNotBlank()) {
                y += 2f
                val tipLines = wrapText("💡 Topper Tip: ${question.topperTip}", CONTENT_WIDTH - 40f, smallPaint)
                for (tLine in tipLines) {
                    canvas.drawText(tLine, MARGIN_LEFT + 22f, y, smallPaint)
                    y += 10.5f
                }
            }

            y = solStartY + totalSolHeight + 6f
        }

        // Subtle divider between questions
        y += 8f
        canvas.drawLine(MARGIN_LEFT + 20f, y, MARGIN_RIGHT - 20f, y, linePaint)
        y += 12f

        onYChanged(y)
    }

    private fun wrapText(text: String, maxWidth: Float, paint: Paint): List<String> {
        val lines = mutableListOf<String>()
        val paragraphs = text.split("\n")

        for (paragraph in paragraphs) {
            if (paragraph.isBlank()) {
                lines.add("")
                continue
            }

            val words = paragraph.split("\\s+".toRegex())
            var currentLine = StringBuilder()

            for (word in words) {
                if (currentLine.isEmpty()) {
                    currentLine.append(word)
                } else {
                    val testLine = "$currentLine $word"
                    if (paint.measureText(testLine) <= maxWidth) {
                        currentLine.append(" ").append(word)
                    } else {
                        lines.add(currentLine.toString())
                        currentLine = StringBuilder(word)
                    }
                }
            }
            if (currentLine.isNotEmpty()) {
                lines.add(currentLine.toString())
            }
        }
        return lines
    }

    fun sharePdf(context: Context, pdfFile: File, title: String) {
        try {
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                pdfFile
            )
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/pdf"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, title)
                putExtra(Intent.EXTRA_TEXT, "Here is the official pattern test paper from VidyaNotes: $title")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share Test Paper PDF"))
        } catch (e: Exception) {
            Toast.makeText(context, "Could not share PDF: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    fun openPdf(context: Context, pdfFile: File) {
        try {
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                pdfFile
            )
            val viewIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(Intent.createChooser(viewIntent, "Open Test Paper PDF"))
        } catch (e: Exception) {
            Toast.makeText(context, "No PDF viewer installed on device.", Toast.LENGTH_SHORT).show()
        }
    }

    fun printPdf(context: Context, pdfFile: File, jobName: String) {
        try {
            val printManager = context.getSystemService(Context.PRINT_SERVICE) as? PrintManager
            if (printManager == null) {
                Toast.makeText(context, "Printing not supported on this device", Toast.LENGTH_SHORT).show()
                return
            }

            val printAdapter = object : PrintDocumentAdapter() {
                override fun onLayout(
                    oldAttributes: PrintAttributes?,
                    newAttributes: PrintAttributes?,
                    cancellationSignal: CancellationSignal?,
                    callback: LayoutResultCallback?,
                    extras: Bundle?
                ) {
                    if (cancellationSignal?.isCanceled == true) {
                        callback?.onLayoutCancelled()
                        return
                    }
                    val info = PrintDocumentInfo.Builder(pdfFile.name)
                        .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .build()
                    callback?.onLayoutFinished(info, true)
                }

                override fun onWrite(
                    pages: Array<out PageRange>?,
                    destination: ParcelFileDescriptor?,
                    cancellationSignal: CancellationSignal?,
                    callback: WriteResultCallback?
                ) {
                    try {
                        FileInputStream(pdfFile).use { input ->
                            FileOutputStream(destination?.fileDescriptor).use { output ->
                                input.copyTo(output)
                            }
                        }
                        callback?.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
                    } catch (e: Exception) {
                        callback?.onWriteFailed(e.message)
                    }
                }
            }

            val printAttributes = PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                .build()

            printManager.print(jobName, printAdapter, printAttributes)
        } catch (e: Exception) {
            Toast.makeText(context, "Print error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
