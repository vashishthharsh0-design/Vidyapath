package com.example.data.local

import androidx.room.*
import com.example.model.FlashcardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM flashcards ORDER BY isMastered ASC, id DESC")
    fun getAllFlashcards(): Flow<List<FlashcardEntity>>

    @Query("SELECT * FROM flashcards WHERE subject = :subject ORDER BY isMastered ASC, id DESC")
    fun getFlashcardsBySubject(subject: String): Flow<List<FlashcardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlashcard(flashcard: FlashcardEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(flashcards: List<FlashcardEntity>)

    @Update
    suspend fun updateFlashcard(flashcard: FlashcardEntity)

    @Delete
    suspend fun deleteFlashcard(flashcard: FlashcardEntity)

    @Query("UPDATE flashcards SET isMastered = :isMastered, reviewCount = reviewCount + 1 WHERE id = :id")
    suspend fun setMasteredStatus(id: Long, isMastered: Boolean)

    @Query("SELECT COUNT(*) FROM flashcards")
    suspend fun getCount(): Int
}
