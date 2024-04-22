package com.example.wellnessjournal.data

import androidx.lifecycle.LiveData
import com.example.wellnessjournal.data.daos.WorkoutBuildDao
import com.example.wellnessjournal.data.daos.WorkoutDao
import com.example.wellnessjournal.data.daos.WorkoutLogDao
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild
import com.example.wellnessjournal.data.entities.WorkoutLog

/**
 * Repository for methods accessing Dao methods for the Workout Related Entities
 */
class WorkoutRepository( private val workoutDao: WorkoutDao, private val workoutBuildDao: WorkoutBuildDao,
    private val workoutLogDao: WorkoutLogDao) {

    // Currently saved dat for workouts, workouts logs, and workout builds
    val listWorkouts: LiveData<List<Workout>> = workoutDao.getWorkouts()
    val lastWorkoutId: LiveData<Int> = workoutDao.getLastWorkoutId()
    val listWorkoutLogs: LiveData<List<WorkoutLog>> = workoutLogDao.getWorkoutLogs()
    val listWorkoutBuilds: LiveData<List<WorkoutBuild>> = workoutBuildDao.getWorkoutBuilds()

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
     * Delete workout with certain workoutId
     */
    suspend fun deleteWorkoutWithId(workoutId: Int) {
        workoutDao.deleteWorkoutWithId(workoutId)
    }

    /**
     * Create a new workout build
     */
    suspend fun createWorkoutBuild(workoutBuild: WorkoutBuild) {
        workoutBuildDao.insertWorkoutBuild(workoutBuild)
    }

    /**
     * Delete existing workout build relating to a specific workout
     */
    suspend fun deleteWorkoutWorkoutBuild(workoutId: Int) {
        workoutBuildDao.deleteWorkoutBuildsForWorkout(workoutId)
    }

    /**
     * Get workout builds for a certain workout
     */
    fun getWorkoutBuildsForWorkout(workoutId: Int): LiveData<List<WorkoutBuild>> {
        return workoutBuildDao.getWorkoutBuildsForWorkout(workoutId)
    }

    /**
     * Add workout log
     */
    suspend fun addWorkoutLog(workoutLog: WorkoutLog) {
        workoutLogDao.insertWorkoutLog(workoutLog)
    }

    /**
     * Delete workout log
     */
    suspend fun deleteWorkoutLog(workoutLog: WorkoutLog) {
        workoutLogDao.deleteWorkoutLog(workoutLog)
    }

    /**
     * Get a workout by id
     */
    fun getWorkoutById(workoutId: Int): LiveData<Workout> {
        return workoutDao.getWorkoutById(workoutId)
    }

    /**
     * Get workout builds containing a certain exercise
     */
    fun getWorkoutBuildsWithExercise(exerciseId: Int): LiveData<List<WorkoutBuild>> {
        return workoutBuildDao.getWorkoutBuildsWithExercise(exerciseId)
    }
}