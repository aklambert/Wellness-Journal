package com.example.wellnessjournal.ui.fitness.exercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.ExerciseRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.entities.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddExerciseViewModel(application: Application) : AndroidViewModel(application) {

    // Get Exercise Dao to access Exercise Table in database
    private val exerciseDao: ExerciseDao =
        WellnessJournalDatabase.getDatabase(application)?.ExerciseDao()!!

    // Get repository for accessing dao methods
    private val exerciseRepo: ExerciseRepository = ExerciseRepository(exerciseDao)

    /**
     * Create a new exercise
     */
    fun createExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepo.createExercise(exercise)
        }
    }
}