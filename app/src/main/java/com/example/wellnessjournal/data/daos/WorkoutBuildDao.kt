package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.wellnessjournal.data.entities.WorkoutBuild
import com.example.wellnessjournal.data.entityrelations.WorkoutBuildWithExercise
import com.example.wellnessjournal.data.entityrelations.WorkoutWithWorkoutBuild

@Dao
interface WorkoutBuildDao {
    /**
     * Insert WorkoutBuild
     */
    @Insert
    suspend fun insertWorkoutBuild(workoutBuild: WorkoutBuild)

    /**
     * Update WorkoutBuild
     */
    @Update
    suspend fun updateWorkoutBuild(workoutBuild: WorkoutBuild)

    /**
     * Delete WorkoutBuild
     */
    @Delete
    suspend fun deleteWorkoutBuild(workoutBuild: WorkoutBuild)

    /**
     * Delete WorkoutBuild for a certain workout
     */
    @Query("DELETE FROM workoutBuild WHERE workoutId = :workoutId")
    suspend fun deleteWorkoutBuildsForWorkout(workoutId: Int)

    /**
     * Get a list of all saved WorkoutBuilds
     */
    @Query ("SELECT * FROM workoutBuild ORDER BY workoutId")
    fun getWorkoutBuilds(): LiveData<List<WorkoutBuild>>

    /**
     * Get workout builds paired with exercises
     */
    @Transaction
    @Query("SELECT * FROM exercise")
    fun getWorkoutBuildWithExercise(): LiveData<List<WorkoutBuildWithExercise>>

}