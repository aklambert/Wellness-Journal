package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.wellnessjournal.data.entities.ReflectionJournal

@Dao
interface ReflectionJournalDao {
    /**
     * Insert a exercise item
     */
    @Insert
    suspend fun insertReflectionJournal(reflectionJournal: ReflectionJournal)

    /**
     * Update an existing exercise
     */
    @Update
    suspend fun updateReflectionJournal(reflectionJournal: ReflectionJournal)

    /**
     * Delete an exercise item
     */
    @Delete
    suspend fun deleteReflectionJournal(reflectionJournal: ReflectionJournal)

    /**
     * Get a list of all saved exercises in order of their ID (when the exercise was created)
     * in the format of LiveData
     */
    @Query("SELECT * FROM reflectionJournal ORDER BY reflectionJournalId")
    fun getReflectionJournals(): LiveData<List<ReflectionJournal>>
}