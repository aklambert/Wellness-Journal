package com.example.wellnessjournal.ui.journal.goals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.JournalRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.GoalDao
import com.example.wellnessjournal.data.daos.ReflectionJournalDao
import com.example.wellnessjournal.data.entities.Goal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for Methods/Variables Pertaining to Updaating Goals
 */
class UpdateGoalViewModel(application: Application) : AndroidViewModel(application) {
    // Get journal related Daos
    private val journalDao: ReflectionJournalDao =
        WellnessJournalDatabase.getDatabase(application)?.ReflectionJournalDao()!!
    private val goalDao: GoalDao =
        WellnessJournalDatabase.getDatabase(application)?.GoalDao()!!

    // Get journal repository
    private val journalRepo: JournalRepository = JournalRepository(journalDao, goalDao)

    /**
     * Update an existing goal
     */
    fun updateGoal(goal: Goal) {
        viewModelScope.launch(Dispatchers.IO) {
            journalRepo.updateGoal(goal)
        }
    }

    /**
     * Delete existing goal
     */
    fun deleteGoal(goal: Goal) {
        viewModelScope.launch(Dispatchers.IO) {
            journalRepo.deleteGoal(goal)
        }
    }
}