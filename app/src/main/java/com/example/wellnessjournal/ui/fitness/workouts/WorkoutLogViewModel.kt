package com.example.wellnessjournal.ui.fitness.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.WorkoutRepository
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.daos.WorkoutBuildDao
import com.example.wellnessjournal.data.daos.WorkoutDao
import com.example.wellnessjournal.data.daos.WorkoutLogDao
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for Methods/Variables Pertaining to Viewing or Deleting Workout Logs
 */
class WorkoutLogViewModel(application: Application) : AndroidViewModel(application) {
    // Get workout related Daos
    private val workoutBuildDao: WorkoutBuildDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutBuildDao()!!
    private val workoutDao: WorkoutDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutDao()!!
    private val workoutLogDao: WorkoutLogDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutLogDao()!!

    // Get workout repository
    private val workoutRepository: WorkoutRepository = WorkoutRepository(workoutDao, workoutBuildDao,
        workoutLogDao)

    // List of workout logs
    val listWorkoutLogs: LiveData<List<WorkoutLog>> = workoutRepository.listWorkoutLogs

    /**
     * Get a workout by it's id
     */
    fun getWorkoutById(workoutId: Int): LiveData<Workout> {
        return workoutRepository.getWorkoutById(workoutId)
    }

    /**
     * Delete a workout log
     */
    fun deleteWorkoutLog(workoutLog: WorkoutLog) {
        // Access database in a different thread
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.deleteWorkoutLog(workoutLog)
        }
    }
}