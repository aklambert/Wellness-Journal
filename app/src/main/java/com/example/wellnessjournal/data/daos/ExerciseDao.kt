package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entityrelations.ExerciseWithExerciseType
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    /**
     * Insert a exercise item
     */
    @Insert
    suspend fun insertExercise(exercise: Exercise)

    /**
     * Update an existing exercise
     */
    @Update
    suspend fun updateExercise(exercise: Exercise)

    /**
     * Delete an exercise item
     */
    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    /**
     * Get a list of all saved exercises in order of their ID (when the exercise was created)
     * in the format of LiveData
     */
    @Query("SELECT * FROM exercise ORDER BY exerciseId")
    fun getExercises(): LiveData<List<Exercise>>

    /**
     * Get an exercise with its associated exercise type
     */
    @Transaction
    @Query("SELECT * FROM exercise")
    fun getExerciseWithExerciseType(): Flow<List<ExerciseWithExerciseType>>
}