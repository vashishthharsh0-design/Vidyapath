package com.example.model

enum class QuestionType(val label: String) {
    MCQ("1-Mark MCQ"),
    ASSERTION_REASON("1-Mark Assertion & Reason"),
    SHORT_ANSWER_3M("3-Mark Short Answer"),
    PROBLEM_SOLVING_4M("4-Mark Problem Solving"),
    LONG_CASE_STUDY_6M("6-Mark Long / Case Study")
}

data class MarkingStep(
    val stepDescription: String,
    val marksAllocated: String // e.g. "½ Mark", "1 Mark", "1½ Marks", "2 Marks"
)

data class TestQuestion(
    val id: String,
    val questionNumber: Int,
    val marks: Int,
    val questionType: QuestionType,
    val questionText: String,
    val casePassage: String? = null,
    val options: List<String>? = null,
    val correctOptionIndex: Int? = null,
    val modelAnswer: String,
    val cbseMarkingScheme: List<MarkingStep>,
    val topperTip: String,
    val commonPitfall: String,
    val topicTag: String
)

data class TestSection(
    val id: String,
    val sectionName: String,
    val instructions: String,
    val questions: List<TestQuestion>
)

data class TestPaperItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val subject: SubjectType,
    val grade: ClassGrade,
    val board: BoardType,
    val maxMarks: Int = 80,
    val timeAllowedMinutes: Int = 180, // 3 hours
    val generalInstructions: List<String>,
    val sections: List<TestSection>
)
