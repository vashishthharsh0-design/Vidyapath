package com.example.model

enum class BoardType(val displayName: String, val shortName: String) {
    CBSE("CBSE (Central Board)", "CBSE"),
    ICSE("ICSE / ISC Board", "ICSE"),
    NCERT("NCERT National Core", "NCERT"),
    STATE_BOARD("State Board Syllabus", "State"),
    COMPETITIVE("Competitive (JEE / NEET / UPSC)", "Competitive")
}

enum class ClassGrade(val displayName: String, val code: String) {
    CLASS_10("Class 10 (Board)", "10"),
    CLASS_12_COMMERCE("Class 12 Commerce (Board)", "12 Comm"),
    CLASS_11_COMMERCE("Class 11 Commerce", "11 Comm"),
    CLASS_12("Class 12 Science (Board)", "12"),
    CLASS_11("Class 11 Science", "11"),
    CLASS_9("Class 9", "9"),
    CLASS_8("Class 8", "8"),
    CLASS_7("Class 7", "7"),
    CLASS_6("Class 6", "6"),
    COMPETITIVE("CUET / CA / JEE / NEET", "Comp")
}

enum class SubjectType(val displayName: String, val iconName: String, val isCommerce: Boolean = false) {
    ACCOUNTANCY("Accountancy", "ReceiptLong", isCommerce = true),
    BUSINESS_STUDIES("Business Studies", "BusinessCenter", isCommerce = true),
    ECONOMICS("Economics", "TrendingUp", isCommerce = true),
    MATHEMATICS("Mathematics", "Calculate"),
    SCIENCE_GENERAL("General Science", "Biotech"),
    PHYSICS("Physics", "Science"),
    CHEMISTRY("Chemistry", "Science"),
    BIOLOGY("Biology", "Eco"),
    HISTORY("History", "MenuBook"),
    POLITY("Polity & Civics", "Gavel"),
    GEOGRAPHY("Geography", "Public"),
    ENGLISH("English", "Translate"),
    HINDI("Hindi", "AutoStories"),
    SANSKRIT("Sanskrit", "School")
}

data class NCERTCruxPoint(
    val pointNumber: Int,
    val title: String,
    val description: String,
    val isHighYield: Boolean = false
)

data class FormulaItem(
    val name: String,
    val formula: String,
    val explanation: String,
    val unitOrNotes: String
)

data class PYQTrend(
    val topicName: String,
    val typicalMarks: String, // "1-Mark MCQ", "3-Mark Short", "5-Mark Case Based"
    val frequency: String, // "Repeated in 2020, 2022, 2023, 2024"
    val keyTrapToAvoid: String
)

data class ChapterItem(
    val id: String,
    val chapterNumber: Int,
    val title: String,
    val subject: SubjectType,
    val grade: ClassGrade,
    val board: BoardType,
    val marksWeightage: String,
    val summary: String,
    val keyTopics: List<String>,
    val cruxPoints: List<NCERTCruxPoint>,
    val formulas: List<FormulaItem>,
    val pyqTrends: List<PYQTrend>,
    val topperTips: List<String>,
    val suggestedMethod: NoteMethodType
)
