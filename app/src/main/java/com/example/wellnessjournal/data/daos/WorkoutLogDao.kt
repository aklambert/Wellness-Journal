package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutLog

@Dao
interface WorkoutLogDao {
    /**
     * Insert workout log
     */
    @Insert
    suspend fun insertWorkoutLog(workoutLog: WorkoutLog)

    /**
     * Update existing workout log
     */
    @Update
    suspend fun updateWorkoutLog(workoutLog: WorkoutLog)

    /**
     * Delete workout log
     */
    @Delete
    suspend fun deleteWorkoutLog(workoutLog: WorkoutLog)

    /**
     * List all workout logs
     */
    @Query("SELECT * FROM workoutLog")
    fun getWorkoutLogs(): LiveData<List<WorkoutLog>>
}