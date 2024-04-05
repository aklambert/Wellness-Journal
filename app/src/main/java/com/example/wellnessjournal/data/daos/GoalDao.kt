package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.wellnessjournal.data.entities.Goal

@Dao
interface GoalDao {
    /**
     * Insert a goal item
     */
    @Insert
    suspend fun insertGoal(goal: Goal)

    /**
     * Update a goal item
     */
    @Update
    suspend fun updateGoal(goal: Goal)

    /**
     * Delete a goal item
     */
    @Delete
    suspend fun deleteGoal(goal: Goal)

    /**
     * Get a list of all goals
     */
    @Query("SELECT * FROM goal ORDER BY goalId")
    fun getGoals(): LiveData<List<Goal>>
}