package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val board: String, // e.g. "CBSE", "ICSE", "NCERT"
    val grade: String, // e.g. "Class 10", "Class 12"
    val subject: String, // e.g. "Science", "Physics"
    val chapter: String, // e.g. "Chemical Reactions & Equations"
    val methodType: String, // e.g. "CORNELL", "FEYNMAN", "BOXING"
    val cueOrKeywordColumn: String = "", // Cues/Questions in Cornell, Concept in Feynman
    val mainContent: String = "", // Body notes or ELI5 explanation
    val summaryOrConclusion: String = "", // Bottom summary or Identified gaps
    val tags: String = "", // comma-separated tags e.g. "#PYQ, #5Marks, #VedicTrick"
    val isPinned: Boolean = false,
    val colorHex: String = "#1E293B",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
