package com.example.model

enum class VideoCategory(val displayName: String, val iconEmoji: String) {
    ALL("All", "📚"),
    ONE_SHOT("One-Shot Marathon", "🔥"),
    CONCEPT_DEEP_DIVE("Concept Deep Dive", "💡"),
    NUMERICALS_PRACTICE("Numericals & Formats", "📐"),
    PYQ_STRATEGY("Board PYQs & Strategy", "📝")
}

data class YouTubeVideoSuggestion(
    val id: String,
    val title: String,
    val channelName: String,
    val verifiedChannel: Boolean = true,
    val grade: ClassGrade,
    val subject: SubjectType,
    val chapterTitle: String,
    val durationText: String, // e.g. "1 hr 45 min", "52 min"
    val category: VideoCategory,
    val viewsCount: String, // e.g. "1.4M views", "920K views"
    val description: String,
    val keyTopicsCovered: List<String>,
    val youtubeVideoUrl: String,
    val searchFallbackQuery: String,
    val recommendedFor: String, // e.g. "Best for Quick Pre-Board Revision"
    val isBoardTopperFavorite: Boolean = true
)
