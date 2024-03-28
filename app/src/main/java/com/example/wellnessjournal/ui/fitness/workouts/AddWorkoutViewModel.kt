package com.example.wellnessjournal.ui.fitness.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.WorkoutRepository
import com.example.wellnessjournal.data.daos.WorkoutBuildDao
import com.example.wellnessjournal.data.daos.WorkoutDao
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild
import com.example.wellnessjournal.data.entityrelations.WorkoutWithWorkoutBuild
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWorkoutViewModel(application: Application) : AndroidViewModel(application) {
    // Get Workout Daos to save exercise into a new workout
    private val workoutBuildDao: WorkoutBuildDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutBuildDao()!!
    private val workoutDao: WorkoutDao =
        WellnessJournalDatabase.getDatabase(application)?.WorkoutDao()!!

    private var exerciseList = listOf<Exercise>()
    private var workoutList = listOf<Workout>()

    // Get repository for accessing WorkoutBuildDao and WorkoutDao methods
    private val workoutRepository: WorkoutRepository = WorkoutRepository(workoutDao, workoutBuildDao)
    val listOfWorkouts: LiveData<List<Workout>> = workoutRepository.listWorkouts
    /**
     * Create a new workout
     */
    fun saveWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO){
           workoutRepository.createWorkout(workout)
        }
    }

    /**
     * Save a new workout build
     */
    fun buildWorkout(workoutBuild: WorkoutBuild) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.createWorkoutBuild(workoutBuild)
        }
    }

    /**
     * Set the list of exercises that will be saved to a workout
     */
    fun setListOfExercises(exercises: List<Exercise>) {
        exerciseList = exercises
    }

    /**
     * Get the list of exercises that will be saved to a workout
     */
    fun getListOfExercises(): List<Exercise> {
        return exerciseList
    }

}