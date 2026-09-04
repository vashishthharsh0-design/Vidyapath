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
}
