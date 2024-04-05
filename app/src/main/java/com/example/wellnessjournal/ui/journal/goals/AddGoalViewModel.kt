package com.example.wellnessjournal.ui.journal.goals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.JournalRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.GoalDao
import com.example.wellnessjournal.data.daos.ReflectionJournalDao
import com.example.wellnessjournal.data.entities.Goal
import com.example.wellnessjournal.data.entities.ReflectionJournal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGoalViewModel(application: Application) : AndroidViewModel(application) {
    // Get Journal Daos
    private val journalDao: ReflectionJournalDao =
        WellnessJournalDatabase.getDatabase(application)?.ReflectionJournalDao()!!
    private val goalDao: GoalDao =
        WellnessJournalDatabase.getDatabase(application)?.GoalDao()!!

    // Get journal repository
    private val journalRepo: JournalRepository = JournalRepository(journalDao, goalDao)

    /**
     * Create a new goal
     */
    fun addGoal(goal: Goal) {
        viewModelScope.launch(Dispatchers.IO) {
            journalRepo.createGoal(goal)
        }
    }
}