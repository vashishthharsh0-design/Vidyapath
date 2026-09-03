package com.example.model

enum class NoteMethodType(val title: String, val tagline: String) {
    CORNELL("Cornell Note Taking", "Best for Board Exams & Active Recall"),
    MIND_MAP("Mind Mapping & Radial Nodes", "Best for Biology, History & Complex Systems"),
    FEYNMAN("Feynman Technique (ELI5)", "Best for Tough Physics & Chemistry Concepts"),
    BOXING("Boxing / Bento Method", "Best for Formulas, Definitions & Compartmentalizing"),
    FLOW_CHART("Flow & Charting Method", "Best for Reactions, Cycles & Historical Timelines"),
    MNEMONICS("Mnemonic & Acronym Vault", "Best for Periodic Table, Lists & Fast Memorization"),
    QEC("Q-E-C (Question-Evidence-Conclusion)", "Best for 5-Mark Long Answers & UPSC/Board Essays")
}

data class MethodStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val proTip: String
)

data class NoteMethodDetail(
    val type: NoteMethodType,
    val iconKey: String,
    val shortSummary: String,
    val whyItWorks: String,
    val bestForSubjects: List<SubjectType>,
    val steps: List<MethodStep>,
    val sampleTitle: String,
    val sampleCue: String,
    val sampleMainContent: String,
    val sampleSummary: String,
    val visualLayoutStructure: String
)
