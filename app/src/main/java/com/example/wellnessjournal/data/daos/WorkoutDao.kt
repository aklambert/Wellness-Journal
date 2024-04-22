package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.wellnessjournal.data.entities.Workout

/**
 * Data Access Object for Workout Entity
 */
@Dao
interface WorkoutDao {
    // Insert a workout item
    @Insert
    suspend fun insertWorkout(workout: Workout)

    // Update an existing workout
    @Update
    suspend fun updateWorkout(workout: Workout)

    // Get a list of all saved workouts in order of their ID
    @Query ("SELECT * FROM workout ORDER BY workoutId")
    fun getWorkouts(): LiveData<List<Workout>>

    // Get last workoutId
    @Query("SELECT workoutId FROM workout ORDER BY workoutId DESC LIMIT 1")
    fun getLastWorkoutId(): LiveData<Int>

    // Delete workout with a certain workoutId
    @Query("DELETE FROM workout WHERE workoutId = :workoutId")
    suspend fun deleteWorkoutWithId(workoutId: Int)

    // Get workout by id
    @Query("SELECT * FROM workout WHERE workoutId = :workoutId")
    fun getWorkoutById(workoutId: Int): LiveData<Workout>
}