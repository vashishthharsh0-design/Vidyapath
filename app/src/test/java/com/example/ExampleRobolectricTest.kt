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
}

