package com.example.wellnessjournal.data

import androidx.lifecycle.LiveData
import com.example.wellnessjournal.data.daos.WorkoutBuildDao
import com.example.wellnessjournal.data.daos.WorkoutDao
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild
import com.example.wellnessjournal.data.entityrelations.WorkoutWithWorkoutBuild

class WorkoutRepository( private val workoutDao: WorkoutDao,
    private val workoutBuildDao: WorkoutBuildDao) {
    private lateinit var exercisesForBuild: LiveData<List<Exercise>>

    // List of all saved workouts
    val listWorkouts: LiveData<List<Workout>> = workoutDao.getWorkouts()
    val listWorkoutWithWorkoutBuild: LiveData<List<WorkoutWithWorkoutBuild>> = workoutDao.getWorkoutBuildWithWorkout()

    /**
     * Create a new workout
     */
    suspend fun createWorkout(workout: Workout) {
        workoutDao.insertWorkout(workout)
    }

    /**
     * Update existing workout
     */
    suspend fun updateWorkout(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }

    /**
     * Delete existing workout
     */
    suspend fun deleteWorkout(workout: Workout) {
        workoutDao.deleteWorkout(workout)
    }

    // List of all saved workout builds (workouts built with saved exercises)
    val listWorkoutBuilds: LiveData<List<WorkoutBuild>> = workoutBuildDao.getWorkoutBuilds()
    /**
     * Create a new workout build
     */
    suspend fun createWorkoutBuild(workoutBuild: WorkoutBuild) {
        workoutBuildDao.insertWorkoutBuild(workoutBuild)
    }

    /**
     * Update existing workout build
     */
    suspend fun updateWorkoutBulid(workoutBuild: WorkoutBuild) {
        workoutBuildDao.updateWorkoutBuild(workoutBuild)
    }

    /**
     * Delete existing workout build
     */
    suspend fun deleteWorkoutBuild(workoutBuild: WorkoutBuild) {
        workoutBuildDao.deleteWorkoutBuild(workoutBuild)
    }
}