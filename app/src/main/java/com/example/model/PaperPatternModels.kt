package com.example.model

enum class PaperPatternType(
    val title: String,
    val subtitle: String,
    val maxMarks: Int,
    val durationMinutes: Int,
    val sectionsDescription: String
) {
    FULL_BOARD_80M(
        title = "Full Board Exam Pattern",
        subtitle = "Standard Annual Board Blueprint (Code SQP)",
        maxMarks = 80,
        durationMinutes = 180,
        sectionsDescription = "Sec A: 20 MCQs/AR (20M) • Sec B: 6 SA-I (12M) • Sec C: 7 SA-II (21M) • Sec D: 3 LA (15M) • Sec E: 3 Case Studies (12M)"
    ),
    MID_TERM_40M(
        title = "Periodic Assessment / Mid-Term",
        subtitle = "Half-Length Pattern (Term I / Pre-Boards)",
        maxMarks = 40,
        durationMinutes = 90,
        sectionsDescription = "Sec A: 10 MCQs/AR (10M) • Sec B: 3 SA-I (6M) • Sec C: 4 SA-II (12M) • Sec D: 1 LA + 1 Case (12M)"
    ),
    UNIT_TEST_25M(
        title = "Chapter Mastery Unit Test",
        subtitle = "Quick Assessment for 1-2 Chapters",
        maxMarks = 25,
        durationMinutes = 45,
        sectionsDescription = "Sec A: 7 MCQs (7M) • Sec B: 3 SA-I (6M) • Sec C: 2 SA-II (6M) • Sec D: 1 Problem/Case (6M)"
    )
}

enum class PdfExportMode(
    val title: String,
    val description: String
) {
    STUDENT_QUESTION_PAPER(
        title = "Student Question Paper",
        description = "Clean exam paper with roll-number box, instructions, and marks only (no answers). Perfect for real handwritten test practice."
    ),
    EVALUATOR_MARKING_SCHEME(
        title = "Official Marking Scheme & Solutions",
        description = "Complete paper including CBSE step-by-step marking rubrics (½ mark, 1 mark distribution), model solutions, and topper tips."
    )
}

enum class PaperDifficulty(val displayName: String, val badge: String) {
    STANDARD_BOARD("Standard Board Level", "CBSE Standard"),
    HOTS_COMPETENCY("HOTS & Competency Focus", "High Order Thinking"),
    REVISION_ESSENTIALS("Scoring & PYQ Essentials", "Revision Master")
}

data class PaperGenerationRequest(
    val subject: SubjectType,
    val grade: ClassGrade,
    val board: BoardType = BoardType.CBSE,
    val patternType: PaperPatternType = PaperPatternType.FULL_BOARD_80M,
    val difficulty: PaperDifficulty = PaperDifficulty.STANDARD_BOARD,
    val chapterScope: String? = null, // null means Full Syllabus
    val paperSetCode: String = "SET-1",
    val useAiGeneration: Boolean = true
)
