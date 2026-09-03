package com.example.data

import com.example.data.local.FlashcardDao
import com.example.data.local.NoteDao
import com.example.model.FlashcardEntity
import com.example.model.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepository(
    private val noteDao: NoteDao,
    private val flashcardDao: FlashcardDao
) {
    val allNotes: Flow<List<NoteEntity>> = noteDao.getAllNotes()
    val allFlashcards: Flow<List<FlashcardEntity>> = flashcardDao.getAllFlashcards()

    fun searchNotes(query: String): Flow<List<NoteEntity>> = noteDao.searchNotes(query)

    fun getNotesBySubject(subject: String): Flow<List<NoteEntity>> = noteDao.getNotesBySubject(subject)

    suspend fun getNoteById(id: Long): NoteEntity? = noteDao.getNoteById(id)

    suspend fun saveNote(note: NoteEntity): Long {
        return if (note.id == 0L) {
            noteDao.insertNote(note)
        } else {
            noteDao.updateNote(note)
            note.id
        }
    }

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

    suspend fun deleteNoteById(id: Long) = noteDao.deleteNoteById(id)

    suspend fun togglePin(id: Long) = noteDao.togglePin(id)

    suspend fun saveFlashcard(flashcard: FlashcardEntity): Long = flashcardDao.insertFlashcard(flashcard)

    suspend fun seedInitialFlashcardsIfEmpty(cards: List<FlashcardEntity>) {
        if (flashcardDao.getCount() == 0) {
            flashcardDao.insertAll(cards)
        }
    }

    suspend fun updateFlashcardStatus(id: Long, isMastered: Boolean) =
        flashcardDao.setMasteredStatus(id, isMastered)

    suspend fun deleteFlashcard(flashcard: FlashcardEntity) = flashcardDao.deleteFlashcard(flashcard)
}
