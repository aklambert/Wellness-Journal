package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wellnessjournal.data.entities.WorkoutBuild

/**
 * Data Access Object for WorkoutBuild Entity
 */
@Dao
interface WorkoutBuildDao {
    // Insert WorkoutBuild
    @Insert
    suspend fun insertWorkoutBuild(workoutBuild: WorkoutBuild)

    // Delete WorkoutBuild for a certain workout
    @Query("DELETE FROM workoutBuild WHERE workoutId = :workoutId")
    suspend fun deleteWorkoutBuildsForWorkout(workoutId: Int)

    // Get a list of all saved WorkoutBuilds
    @Query ("SELECT * FROM workoutBuild ORDER BY workoutId")
    fun getWorkoutBuilds(): LiveData<List<WorkoutBuild>>

    // Get workout builds for certain workout
    @Query("SELECT * FROM workoutBuild WHERE workoutId = :workoutId")
    fun getWorkoutBuildsForWorkout(workoutId: Int): LiveData<List<WorkoutBuild>>

    // Get list of Workout Builds that have a certain exercise
    @Query("SELECT * FROM workoutBuild WHERE exerciseId = :exerciseId")
    fun getWorkoutBuildsWithExercise(exerciseId: Int): LiveData<List<WorkoutBuild>>
}