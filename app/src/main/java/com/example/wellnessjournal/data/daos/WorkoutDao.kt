package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entityrelations.WorkoutWithWorkoutBuild

@Dao
interface WorkoutDao {
    /**
     * Insert a workout item
     */
    @Insert
    suspend fun insertWorkout(workout: Workout)

    /**
     * Update an existing workout
     */
    @Update
    suspend fun updateWorkout(workout: Workout)

    /**
     * Delete a workout item
     */
    @Delete
    suspend fun deleteWorkout(workout: Workout)

    /**
     * Get a list of all saved workouts in order of their ID
     */
    @Query ("SELECT * FROM workout ORDER BY workoutId")
    fun getWorkouts(): LiveData<List<Workout>>

    /**
     * Get workout paired with workout builds
     */
    @Transaction
    @Query("SELECT * FROM workoutBuild")
    fun getWorkoutBuildWithWorkout(): LiveData<List<WorkoutWithWorkoutBuild>>
}