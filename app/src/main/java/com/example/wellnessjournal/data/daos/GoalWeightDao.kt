package com.example.wellnessjournal.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.wellnessjournal.data.entities.GoalWeight

@Dao
interface GoalWeightDao {
    /**
     * Insert a goalWeight item
     */
    @Insert
    suspend fun addGoalWeight(goalWeight: GoalWeight)

    /**
     * Update a goalWeight item
     */
    @Update
    suspend fun updateGoalWeight(goalWeight: GoalWeight)


    /**
     * Get the saved goal weight
     */
    @Query("SELECT * FROM goalWeight")
    fun getGoalWeights(): LiveData<List<GoalWeight>>

}