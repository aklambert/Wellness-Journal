package com.example.wellnessjournal.ui.fitness.weightTracking

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.WeightTrackerRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.GoalWeightDao
import com.example.wellnessjournal.data.entities.GoalWeight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGoalWeightViewModel(application: Application) : AndroidViewModel(application) {
    // Get goalweight dao
    private val goalWeightDao: GoalWeightDao =
        WellnessJournalDatabase.getDatabase(application)?.GoalWeightDao()!!

    // Get repository
    private val weightRepository: WeightTrackerRepository = WeightTrackerRepository(goalWeightDao)

    val listGoalWeight: LiveData<List<GoalWeight>> = weightRepository.listGoalWeight
    fun addGoalWeight(goalWeight: GoalWeight) {
        viewModelScope.launch(Dispatchers.IO) {
            weightRepository.addGoalWeight(goalWeight)
        }
    }
}