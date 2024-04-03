package com.example.wellnessjournal.ui.fitness.workouts

import android.app.Application
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.WorkoutRepository
import com.example.wellnessjournal.data.daos.WorkoutBuildDao
import com.example.wellnessjournal.data.daos.WorkoutDao
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateWorkoutViewModel(application: Application) : AndroidViewModel(application) {
    // Get Workout Daos
    private val workoutBuildDao: WorkoutBuildDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutBuildDao()!!
    private val workoutDao: WorkoutDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutDao()!!

    // Get workout repository
    private val workoutRepository: WorkoutRepository = WorkoutRepository(workoutDao, workoutBuildDao)
    val listOfWorkoutBuilds: LiveData<List<WorkoutBuild>> = workoutRepository.listWorkoutBuilds

    /**
     * Update an existing workout
     */
    fun updateWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.updateWorkout(workout)
        }
    }

    /**
     * Delete workout builds for previously saved workout
     */
    fun deleteWorkoutBuildsForWorkout(workoutId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.deleteWorkoutWorkoutBuild(workoutId)
        }
    }

    /**
     * Delete an existing workout
     */
    fun deleteWorkoutWithId(workoutId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.deleteWorkoutWithId(workoutId)
        }
    }
}