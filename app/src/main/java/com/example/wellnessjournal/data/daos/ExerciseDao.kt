package com.example.wellnessjournal.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entityrelations.ExerciseWithExerciseType
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    /**
     * Insert or update an exercise item
     */
    @Upsert
    suspend fun upsertExercise(exercise: Exercise)

    /**
     * Delete an exercise item
     */
    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    /**
     * Get a list of all saved exercises in order of their ID (when the exercise was created)
     */
    @Query("SELECT * FROM exercise ORDER BY exerciseId")
    fun getExercises(): Flow<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM exercise")
    fun getExerciseWithExerciseType(): Flow<List<ExerciseWithExerciseType>>
}