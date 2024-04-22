package com.example.wellnessjournal.ui.fitness.exercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.wellnessjournal.data.ExerciseRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.entities.Exercise

/**
 * ViewModel for Methods/Variables Pertaining to Viewing Exercises
 */
class ExercisesViewModel(application: Application): AndroidViewModel(application) {
    // Get ExerciseDao to access Exercise Table in database
    private val exerciseDao: ExerciseDao =
        WellnessJournalDatabase.getDatabase(application)?.ExerciseDao()!!

    // Get Exercise Repository for accessing methods in the exercise dao
    private val exerciseRepo: ExerciseRepository = ExerciseRepository(exerciseDao)

    // List of all saved exercises
    val listOfExercises: LiveData<List<Exercise>> = exerciseRepo.listExercises
}