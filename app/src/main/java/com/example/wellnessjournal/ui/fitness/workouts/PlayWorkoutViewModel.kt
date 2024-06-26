package com.example.wellnessjournal.ui.fitness.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.ExerciseRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.WorkoutRepository
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.daos.WorkoutBuildDao
import com.example.wellnessjournal.data.daos.WorkoutDao
import com.example.wellnessjournal.data.daos.WorkoutLogDao
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.WorkoutBuild
import com.example.wellnessjournal.data.entities.WorkoutLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * ViewModel for Methods/Variables Pertaining to Completing/ Logging a Workout
 */
class PlayWorkoutViewModel(application: Application) : AndroidViewModel(application) {
    // Get exercise and workout related Daos
    private val workoutBuildDao: WorkoutBuildDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutBuildDao()!!
    private val workoutDao: WorkoutDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutDao()!!
    private val exerciseDao: ExerciseDao =
        WellnessJournalDatabase.getDatabase(application)?.ExerciseDao()!!
    private val workoutLogDao: WorkoutLogDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutLogDao()!!

    // Get exercise and workout repositories
    private val workoutRepository: WorkoutRepository = WorkoutRepository(workoutDao, workoutBuildDao,
        workoutLogDao)
    private val exerciseRepository: ExerciseRepository = ExerciseRepository(exerciseDao)

    /**
     * Get list of exercises associated with a certain workout
     */
    fun getWorkoutBuildsForWorkout(workoutId: Int): LiveData<List<WorkoutBuild>> {
        return  workoutRepository.getWorkoutBuildsForWorkout(workoutId)
    }

    /**
     * Get an exercise by it's id
     */
    fun getExerciseById(exerciseId: Int): LiveData<Exercise> {
        return exerciseRepository.getExerciseById(exerciseId)
    }

    /**
     * Create a workout log
     */
    fun logWorkout(workoutLog: WorkoutLog) {
        // Access database in a different thread
        viewModelScope.launch(Dispatchers.IO){
            workoutRepository.addWorkoutLog(workoutLog)
        }
    }
}