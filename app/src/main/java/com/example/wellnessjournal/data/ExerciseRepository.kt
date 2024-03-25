package com.example.wellnessjournal.data

import androidx.lifecycle.LiveData
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.entities.Exercise

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    val listExercises: LiveData<List<Exercise>> = exerciseDao.getExercises()

    suspend fun createExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    suspend fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }
}