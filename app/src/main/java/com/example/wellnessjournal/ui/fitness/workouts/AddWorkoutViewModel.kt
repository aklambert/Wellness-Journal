package com.example.wellnessjournal.ui.fitness.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.WorkoutRepository
import com.example.wellnessjournal.data.daos.WorkoutBuildDao
import com.example.wellnessjournal.data.daos.WorkoutDao
import com.example.wellnessjournal.data.daos.WorkoutLogDao
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for Methods/Variables Pertaining to Adding Workouts
 */
class AddWorkoutViewModel(application: Application) : AndroidViewModel(application) {
    // Get Workout Daos to save exercise into a new workout
    private val workoutBuildDao: WorkoutBuildDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutBuildDao()!!
    private val workoutDao: WorkoutDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutDao()!!
    private val workoutLogDao: WorkoutLogDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutLogDao()!!

    // Get repository for accessing WorkoutBuildDao and WorkoutDao methods
    private val workoutRepository: WorkoutRepository = WorkoutRepository(workoutDao, workoutBuildDao,
        workoutLogDao)
    val listOfWorkouts: LiveData<List<Workout>> = workoutRepository.listWorkouts

    // Last workoutId currently saved in database
    val lastWorkoutId: LiveData<Int> = workoutRepository.lastWorkoutId

    /**
     * Create a new workout
     */
    fun saveWorkout(workout: Workout) {
        // Access database in a different thread
        viewModelScope.launch(Dispatchers.IO){
           workoutRepository.createWorkout(workout)
        }
    }

    /**
     * Save a new workout build
     */
    fun buildWorkout(workoutBuild: WorkoutBuild) {
        // Access database in a different thread
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.createWorkoutBuild(workoutBuild)
        }
    }
}