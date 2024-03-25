package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.wellnessjournal.data.entities.ExerciseType

@Dao
interface ExerciseTypeDao {
    /**
     * Insert or update an exercise type item
     */
    @Upsert
    suspend fun upsertExerciseType(exerciseType: ExerciseType)

    /**
     * Delete an exercise type item
     */
    @Delete
    suspend fun deleteExercise(exerciseType: ExerciseType)

    /**
     * Get a list of all saved exercise types in order of their ID (when the exercise type was created)
     */
    @Query("SELECT * FROM exercise_type ORDER BY exerciseTypeId")
    fun getExerciseTypes(): LiveData<List<ExerciseType>>

    /**
     * Get a list of all saved exercise type names
     */
    @Query("SELECT exerciseTypeName FROM exercise_type")
    fun getExerciseTypeNames(): LiveData<List<String>>
}