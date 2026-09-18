package com.example.model

data class NcertChapterInfo(
    val chapterNumber: Int,
    val title: String,
    val hindiTitle: String = "",
    val summary: String,
    val keyTopics: List<String> = emptyList(),
    val pdfPortalUrl: String = "https://ncert.nic.in/textbook.php",
    val matchingChapterId: String? = null,
    val detailedNotes: NcertChapterDetailedNotes? = null
)

data class NcertChapterDetailedNotes(
    val overview: String,
    val keyConcepts: List<NcertNoteConcept>,
    val importantDefinitions: List<Pair<String, String>> = emptyList(),
    val keyFormulasOrLaws: List<String> = emptyList(),
    val ncertQuestionsAndAnswers: List<NcertQnA> = emptyList(),
    val examPointers: List<String> = emptyList()
)

data class NcertNoteConcept(
    val title: String,
    val explanation: String,
    val keyPoints: List<String> = emptyList()
)

data class NcertQnA(
    val question: String,
    val answer: String,
    val questionType: String = "NCERT Exercise"
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
