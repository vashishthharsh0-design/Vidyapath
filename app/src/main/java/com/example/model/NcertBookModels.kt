package com.example.model

data class NcertChapterInfo(
    val chapterNumber: Int,
    val title: String,
    val hindiTitle: String = "",
    val summary: String,
    val keyTopics: List<String> = emptyList(),
    val pdfPortalUrl: String = "https://ncert.nic.in/textbook.php",
    val matchingChapterId: String? = null
)

data class NcertBook(
    val id: String,
    val title: String,
    val hindiTitle: String = "",
    val grade: ClassGrade,
    val subject: SubjectType,
    val subjectCategory: String,
    val cbseBookCode: String,
    val publisher: String = "NCERT (National Council of Educational Research & Training)",
    val edition: String = "CBSE Rationalised & NEP Aligned Edition",
    val medium: String = "English & Hindi Medium",
    val description: String,
    val coverColorHex: Long = 0xFF1E88E5,
    val officialNcertUrl: String = "https://ncert.nic.in/textbook.php",
    val chapters: List<NcertChapterInfo>
) {
    val cbseCode: String get() = cbseBookCode
}
