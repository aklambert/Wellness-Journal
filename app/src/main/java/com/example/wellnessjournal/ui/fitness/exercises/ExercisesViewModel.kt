package com.example.wellnessjournal.ui.fitness.exercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.ExerciseRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.entities.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExercisesViewModel(application: Application): AndroidViewModel(application) {
    // Get ExerciseDao to access Exercise Table in database
    private val exerciseDao: ExerciseDao =
        WellnessJournalDatabase.getDatabase(application)?.ExerciseDao()!!

    private val exerciseRepo: ExerciseRepository = ExerciseRepository(exerciseDao)
    val listOfExercises: LiveData<List<Exercise>> = exerciseRepo.listExercises
}