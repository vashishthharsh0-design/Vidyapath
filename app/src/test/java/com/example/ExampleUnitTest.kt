package com.example

import com.example.data.GeminiChatRepository
import com.example.data.VideoSuggestionRepository
import com.example.model.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testAiTutorPersonasAndModels() {
        // 1. General CBSE Mentor should use gemini-3.5-flash
        val general = AiTutorPersona.GENERAL_CBSE
        assertEquals("gemini-3.5-flash", general.modelId)
        assertFalse(general.defaultSearchGrounding)

        // 2. Complex STEM & Accounts solver should use gemini-3.1-pro-preview
        val stem = AiTutorPersona.STEM_PRO_SOLVER
        assertEquals("gemini-3.1-pro-preview", stem.modelId)
        assertFalse(stem.defaultSearchGrounding)

        // 3. Live CBSE Search Grounding should use gemini-3.5-flash and have search enabled by default
        val search = AiTutorPersona.LIVE_SEARCH
        assertEquals("gemini-3.5-flash", search.modelId)
        assertTrue(search.defaultSearchGrounding)

        // 4. Fast Revision should use gemini-3.1-flash-lite-preview
        val fast = AiTutorPersona.FAST_REVISION
        assertEquals("gemini-3.1-flash-lite-preview", fast.modelId)
        assertFalse(fast.defaultSearchGrounding)
    }

    @Test
    fun testEducationalFallbackGeneration() = runBlocking {
        // Test marking scheme fallback
        val resultMarking = GeminiChatRepository.generateAiResponse(
            conversationHistory = emptyList(),
            userMessage = "What is the CBSE marking scheme for 5-mark questions?",
            persona = AiTutorPersona.GENERAL_CBSE,
            isSearchGroundingEnabled = false,
            grade = ClassGrade.CLASS_10,
            board = BoardType.CBSE
        )
        assertTrue(resultMarking.isSuccess)
        val msg = resultMarking.getOrNull()!!
        assertTrue(msg.text.contains("Marking Scheme Breakdown"))

        // Test Accountancy Partnership fallback
        val resultAccounts = GeminiChatRepository.generateAiResponse(
            conversationHistory = emptyList(),
            userMessage = "Explain partnership goodwill and pro-rata share capital entries",
            persona = AiTutorPersona.STEM_PRO_SOLVER,
            isSearchGroundingEnabled = false,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE
        )
        assertTrue(resultAccounts.isSuccess)
        val accountsMsg = resultAccounts.getOrNull()!!
        assertTrue(accountsMsg.text.contains("Profit & Loss Appropriation"))

        // Test Search Grounding fallback
        val resultSearch = GeminiChatRepository.generateAiResponse(
            conversationHistory = emptyList(),
            userMessage = "Latest CBSE 2025-2026 exam date updates",
            persona = AiTutorPersona.LIVE_SEARCH,
            isSearchGroundingEnabled = true,
            grade = ClassGrade.CLASS_12,
            board = BoardType.CBSE
        )
        assertTrue(resultSearch.isSuccess)
        val searchMsg = resultSearch.getOrNull()!!
        assertTrue(searchMsg.isSearchGrounded)
        assertTrue(searchMsg.groundingSources.isNotEmpty())
    }

    @Test
    fun testVideosAccordingToClass() {
        // Test Class 12 Commerce videos
        val comm12Videos = VideoSuggestionRepository.getVideosForGrade(ClassGrade.CLASS_12_COMMERCE)
        assertTrue("Class 12 Commerce should have video suggestions", comm12Videos.isNotEmpty())
        assertTrue("Should contain Accountancy videos", comm12Videos.any { it.subject == SubjectType.ACCOUNTANCY })
        assertTrue("Should contain Business Studies videos", comm12Videos.any { it.subject == SubjectType.BUSINESS_STUDIES })
        assertTrue("Should contain Economics videos", comm12Videos.any { it.subject == SubjectType.ECONOMICS })

        // Test Class 10 videos
        val class10Videos = VideoSuggestionRepository.getVideosForGrade(ClassGrade.CLASS_10)
        assertTrue("Class 10 should have video suggestions", class10Videos.isNotEmpty())
        assertTrue("Class 10 should have Science videos", class10Videos.any { it.subject == SubjectType.SCIENCE_GENERAL })
        assertTrue("Class 10 should have Mathematics videos", class10Videos.any { it.subject == SubjectType.MATHEMATICS })

        // Test Class 12 Science videos
        val science12Videos = VideoSuggestionRepository.getVideosForGrade(ClassGrade.CLASS_12)
        assertTrue("Class 12 Science should have video suggestions", science12Videos.isNotEmpty())
        assertTrue("Class 12 Science should have Physics", science12Videos.any { it.subject == SubjectType.PHYSICS })
        assertTrue("Class 12 Science should have Chemistry", science12Videos.any { it.subject == SubjectType.CHEMISTRY })

        // Test Chapter-specific video suggestions
        val lightVideos = VideoSuggestionRepository.getVideosForChapter("Light - Reflection and Refraction", ClassGrade.CLASS_10)
        assertTrue("Should find videos for Light chapter", lightVideos.isNotEmpty())

        val partnershipVideos = VideoSuggestionRepository.getVideosForChapter("Accounting for Partnership Firms - Fundamentals", ClassGrade.CLASS_12_COMMERCE)
        assertTrue("Should find videos for Partnership chapter", partnershipVideos.isNotEmpty())

        // Test search and filter functionality
        val searchResults = VideoSuggestionRepository.filterVideos(
            grade = ClassGrade.CLASS_12_COMMERCE,
            subject = SubjectType.ACCOUNTANCY,
            category = VideoCategory.ONE_SHOT,
            query = "Partnership"
        )
        assertTrue("Filtered search should find matching partnership one shot video", searchResults.isNotEmpty())
    }

    @Test
    fun testPaperPatternBlueprints() {
        // 1. Full Board Blueprint verification
        val fullBoard = PaperPatternType.FULL_BOARD_80M
        assertEquals(80, fullBoard.maxMarks)
        assertEquals(180, fullBoard.durationMinutes)
        assertTrue(fullBoard.sectionsDescription.contains("Sec A"))
        assertTrue(fullBoard.sectionsDescription.contains("Sec E"))

        // 2. Mid-Term Blueprint verification
        val midTerm = PaperPatternType.MID_TERM_40M
        assertEquals(40, midTerm.maxMarks)
        assertEquals(90, midTerm.durationMinutes)

        // 3. Unit Test Blueprint verification
        val unitTest = PaperPatternType.UNIT_TEST_25M
        assertEquals(25, unitTest.maxMarks)
        assertEquals(45, unitTest.durationMinutes)
    }

    @Test
    fun testCuratedPaperGeneration() = runBlocking {
        // Generate Accountancy Full Board Mock Paper
        val accRequest = PaperGenerationRequest(
            subject = SubjectType.ACCOUNTANCY,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            patternType = PaperPatternType.FULL_BOARD_80M,
            difficulty = PaperDifficulty.STANDARD_BOARD,
            paperSetCode = "SET-1",
            useAiGeneration = false // tests deterministic blueprint generator
        )
        val accResult = com.example.data.TestPaperGeneratorRepository.generateTestPaper(accRequest)
        assertTrue("Accountancy generation should succeed", accResult.isSuccess)
        val accPaper = accResult.getOrNull()!!
        assertEquals(80, accPaper.maxMarks)
        assertEquals(180, accPaper.timeAllowedMinutes)
        assertTrue("Should have multiple sections A to E", accPaper.sections.size >= 4)
        
        // Verify Section A contains MCQs with marking scheme
        val secA = accPaper.sections.first { it.sectionName.contains("Section A", true) }
        assertTrue("Section A must have questions", secA.questions.isNotEmpty())
        val q1 = secA.questions.first()
        assertEquals(1, q1.marks)
        assertNotNull("Question 1 should have options", q1.options)
        assertTrue("Question 1 should have step-by-step marking steps", q1.cbseMarkingScheme.isNotEmpty())

        // Generate Class 10 Science Mid-Term Paper
        val sciRequest = PaperGenerationRequest(
            subject = SubjectType.SCIENCE_GENERAL,
            grade = ClassGrade.CLASS_10,
            board = BoardType.CBSE,
            patternType = PaperPatternType.MID_TERM_40M,
            difficulty = PaperDifficulty.HOTS_COMPETENCY,
            paperSetCode = "SET-2",
            useAiGeneration = false
        )
        val sciResult = com.example.data.TestPaperGeneratorRepository.generateTestPaper(sciRequest)
        assertTrue("Science generation should succeed", sciResult.isSuccess)
        val sciPaper = sciResult.getOrNull()!!
        assertEquals(40, sciPaper.maxMarks)
        assertEquals(90, sciPaper.timeAllowedMinutes)

        // Verify Case Study Question exists
        val allQ = sciPaper.sections.flatMap { it.questions }
        val caseQ = allQ.find { !it.casePassage.isNullOrBlank() }
        assertNotNull("Should contain at least one case study question", caseQ)
    }

    @Test
    fun testLiteModeStateAndToggle() {
        val state = com.example.viewmodel.MainUiState()
        // Verify Lite mode is active by default in the Lite edition
        assertTrue("Lite mode should default to true", state.isLiteMode)
        assertFalse("Lite info dialog should initially be dismissed", state.showLiteModeInfoDialog)
        assertTrue("Data saved estimate should be positive", state.dataSavedMegabytes > 0)
    }
}
