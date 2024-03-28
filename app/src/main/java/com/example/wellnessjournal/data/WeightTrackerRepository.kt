package com.example.wellnessjournal.data

import androidx.lifecycle.LiveData
import com.example.wellnessjournal.data.daos.GoalWeightDao
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.GoalWeight

class WeightTrackerRepository(private val goalWeightDao: GoalWeightDao) {
    val listGoalWeight: LiveData<List<GoalWeight>> = goalWeightDao.getGoalWeights()

    suspend fun addGoalWeight(goalWeight: GoalWeight) {
        goalWeightDao.addGoalWeight(goalWeight)
    }

    suspend fun updateGoalWeight(goalWeight: GoalWeight) {
        goalWeightDao.updateGoalWeight(goalWeight)
    }

    fun isGoalWeightSet(): Boolean {
        return false
    }
}