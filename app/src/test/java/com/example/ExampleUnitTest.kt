package com.example

import com.example.data.VideoSuggestionRepository
import com.example.model.ClassGrade
import com.example.model.SubjectType
import com.example.model.VideoCategory
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
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
