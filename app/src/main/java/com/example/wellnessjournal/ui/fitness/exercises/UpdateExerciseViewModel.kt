package com.example.wellnessjournal.ui.fitness.exercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.ExerciseRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.WorkoutRepository
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.WorkoutBuild
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for Methods/Variables Pertaining to Updating Exercises
 */
class UpdateExerciseViewModel(application: Application) : AndroidViewModel(application) {
    // Get ExerciseDao to access Exercise Table in database
    private val exerciseDao: ExerciseDao =
        WellnessJournalDatabase.getDatabase(application)?.ExerciseDao()!!

    // Get Workout related Daos
    private val workoutDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutDao()!!
    private val workoutBuildDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutBuildDao()!!
    private val workoutLogDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutLogDao()!!

    // Get repositories for accessing dao methods
    private val exerciseRepo: ExerciseRepository = ExerciseRepository(exerciseDao)
    private val workoutRepo: WorkoutRepository = WorkoutRepository(workoutDao, workoutBuildDao, workoutLogDao)

    /**
     * Update existing exercise
     */
    fun updateExercise(exercise: Exercise) {
        // Access database in a different thread (not the main one)
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepo.updateExercise(exercise)
        }
    }

    /**
     * Delete existing exercise
     */
    fun deleteExercise(exercise: Exercise) {
        // Access database in a different thread (not the main one)
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepo.deleteExercise(exercise)
        }
    }

    /**
     * Get workout builds containing a certain exercise
     */
    fun getWorkoutBuildsWithExercise(exerciseId: Int): LiveData<List<WorkoutBuild>> {
        return workoutRepo.getWorkoutBuildsWithExercise(exerciseId)
    }
}