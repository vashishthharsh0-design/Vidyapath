package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class FlashcardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val subject: String,
    val chapter: String,
    val questionFront: String,
    val answerBack: String,
    val mnemonicOrTrick: String = "",
    val isMastered: Boolean = false,
    val reviewCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

data class ExamTrick(
    val id: String,
    val category: String, // "Vedic Math & Speed", "CBSE Board Hacks", "Science Mnemonics", "Topper Note Secrets"
    val title: String,
    val shortSummary: String,
    val formulaOrTrick: String,
    val stepByStepExample: String,
    val applicationScenario: String,
    val boardRelevance: String
)
