package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.SyllabusRepository
import com.example.data.TestPaperRepository
import com.example.model.ClassGrade
import com.example.model.SubjectType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("VidyaNotes Lite", appName)
  }

  @Test
  fun `verify commerce syllabus chapters exist and load`() {
    val commerceChapters = SyllabusRepository.getChaptersByGradeAndSubject(ClassGrade.CLASS_12_COMMERCE)
    assertTrue("Should have Commerce chapters for Class 12", commerceChapters.isNotEmpty())
    val accountancy = commerceChapters.any { it.subject == SubjectType.ACCOUNTANCY }
    val businessStudies = commerceChapters.any { it.subject == SubjectType.BUSINESS_STUDIES }
    val economics = commerceChapters.any { it.subject == SubjectType.ECONOMICS }
    assertTrue("Should contain Accountancy", accountancy)
    assertTrue("Should contain Business Studies", businessStudies)
    assertTrue("Should contain Economics", economics)
  }

  @Test
  fun `verify strictly based test papers exist with CBSE step marking`() {
    val papers = TestPaperRepository.testPapers
    assertTrue("Should have test papers", papers.isNotEmpty())
    val accountancyPaper = papers.firstOrNull { it.subject == SubjectType.ACCOUNTANCY }
    assertTrue("Should have Accountancy test paper", accountancyPaper != null)
    assertEquals(80, accountancyPaper!!.maxMarks)
    val firstQuestion = accountancyPaper.sections.first().questions.first()
    assertTrue("Question should have CBSE step marking scheme", firstQuestion.cbseMarkingScheme.isNotEmpty())
  }

  @Test
  fun `verify AI response generator provides comprehensive academic guidance`() = kotlinx.coroutines.runBlocking {
    val result = com.example.data.GeminiChatRepository.generateAiResponse(
        conversationHistory = emptyList(),
        userMessage = "Explain CBSE 5 mark marking scheme and partnership goodwill valuation",
        persona = com.example.model.AiTutorPersona.GENERAL_CBSE,
        isSearchGroundingEnabled = false,
        grade = ClassGrade.CLASS_12_COMMERCE,
        board = com.example.model.BoardType.CBSE
    )
    assertTrue("AI generation should succeed", result.isSuccess)
    val reply = result.getOrNull()
    assertTrue("Reply should not be null", reply != null)
    assertTrue("Reply text should be non-empty", reply!!.text.isNotBlank())
    assertTrue("Reply should mention marking or syllabus content", reply.text.contains("Mark") || reply.text.contains("CBSE") || reply.text.contains("Partnership"))
  }

  @Test
  fun `verify student notes summarization produces executive crux, key highlights, and active recall cues`() = kotlinx.coroutines.runBlocking {
    val sampleNoteContent = """
      Ohm's Law states that the current flowing through a conductor is directly proportional to the potential difference across its ends, provided temperature remains constant.
      • V = I * R
      • Resistance depends on length, cross-sectional area, and material resistivity.
      • SI unit of resistance is Ohm (Ω).
      • In series combination: R_eq = R1 + R2 + R3.
      • In parallel combination: 1/R_eq = 1/R1 + 1/R2 + 1/R3.
      Board Exam Trap: Temperature must be explicitly stated as constant to secure full 2 marks!
    """.trimIndent()

    val result = com.example.data.GeminiChatRepository.summarizeStudentNotes(
        title = "Ohm's Law and Resistance",
        subject = "Physics",
        chapter = "Electricity",
        mainContent = sampleNoteContent,
        cues = "What is Ohm's law?\nHow does resistance vary with temperature?",
        grade = "Class 10",
        board = "CBSE"
    )

    assertTrue("Crux generation must succeed", result.isSuccess)
    val crux = result.getOrNull()
    assertTrue("Crux object should not be null", crux != null)
    assertTrue("Executive crux should be non-empty", crux!!.executiveCrux.isNotBlank())
    assertTrue("Key highlights should contain elements", crux.keyHighlights.isNotEmpty())
    assertTrue("Exam cues should contain questions", crux.examCues.isNotEmpty())

    val formatted = crux.toFormattedSummary()
    assertTrue("Formatted summary must contain EXECUTIVE CRUX", formatted.contains("EXECUTIVE CRUX"))
    assertTrue("Formatted summary must contain KEY HIGHLIGHTS", formatted.contains("KEY HIGHLIGHTS"))
  }

  @Test
  fun `verify NoteEntity overload generates valid summary for existing note`() = kotlinx.coroutines.runBlocking {
    val note = com.example.model.NoteEntity(
        id = 42L,
        title = "Goodwill Valuation Methods in Partnership",
        subject = "Accountancy",
        chapter = "Partnership Fundamentals",
        methodType = "CORNELL",
        cueOrKeywordColumn = "Average Profit vs Super Profit?",
        mainContent = "Average Profit Method = Total Normal Profit / Number of Years. Super Profit = Actual Average Profit - Normal Profit. Goodwill = Super Profit * Number of Years Purchase.",
        summaryOrConclusion = "",
        tags = "#Accounts, #Class12",
        grade = "Class 12",
        board = "CBSE"
    )

    val result = com.example.data.GeminiChatRepository.summarizeStudentNote(note)
    assertTrue("NoteEntity crux generation should succeed", result.isSuccess)
    val crux = result.getOrThrow()
    assertEquals("Goodwill Valuation Methods in Partnership", crux.title)
    assertTrue("Executive crux should summarize partnership concept", crux.executiveCrux.isNotBlank())
    assertTrue("Highlights should exist", crux.keyHighlights.isNotEmpty())
  }

  @Test
  fun `verify Class 6 7 8 syllabus, videos, and test papers integration`() {
    // Verify Class 8
    val class8Chapters = SyllabusRepository.getChaptersByGradeAndSubject(ClassGrade.CLASS_8)
    assertTrue("Class 8 should have chapters", class8Chapters.isNotEmpty())
    val class8Videos = com.example.data.VideoSuggestionRepository.getVideosForGrade(ClassGrade.CLASS_8)
    assertTrue("Class 8 should have videos", class8Videos.isNotEmpty())

    // Verify Class 7
    val class7Chapters = SyllabusRepository.getChaptersByGradeAndSubject(ClassGrade.CLASS_7)
    assertTrue("Class 7 should have chapters", class7Chapters.isNotEmpty())
    val class7Videos = com.example.data.VideoSuggestionRepository.getVideosForGrade(ClassGrade.CLASS_7)
    assertTrue("Class 7 should have videos", class7Videos.isNotEmpty())

    // Verify Class 6
    val class6Chapters = SyllabusRepository.getChaptersByGradeAndSubject(ClassGrade.CLASS_6)
    assertTrue("Class 6 should have chapters", class6Chapters.isNotEmpty())
    val class6Videos = com.example.data.VideoSuggestionRepository.getVideosForGrade(ClassGrade.CLASS_6)
    assertTrue("Class 6 should have videos", class6Videos.isNotEmpty())

    // Verify Test papers
    val papers = TestPaperRepository.testPapers
    assertTrue("Should have Class 8 test paper", papers.any { it.grade == ClassGrade.CLASS_8 })
    assertTrue("Should have Class 7 test paper", papers.any { it.grade == ClassGrade.CLASS_7 })
    assertTrue("Should have Class 6 test paper", papers.any { it.grade == ClassGrade.CLASS_6 })
  }
}

