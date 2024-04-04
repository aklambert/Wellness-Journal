package com.example.wellnessjournal.data

import androidx.lifecycle.LiveData
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.daos.WorkoutBuildDao
import com.example.wellnessjournal.data.daos.WorkoutDao
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild

class ExerciseRepository(private val exerciseDao: ExerciseDao){

    // List of all saved exercises
    val listExercises: LiveData<List<Exercise>> = exerciseDao.getExercises()

    /**
     * Create a new exercise
     */
    suspend fun createExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    /**
     * Update existing exercise
     */
    suspend fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    /**
     * Delete existing exercise
     */
    suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise)
    }

    /**
     * Get exercise by id
     */
    fun getExerciseById(exerciseId: Int): LiveData<Exercise> {
        return exerciseDao.getExerciseById(exerciseId)
    }

}