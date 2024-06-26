package com.example.wellnessjournal.ui.journal.goals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.wellnessjournal.data.JournalRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.GoalDao
import com.example.wellnessjournal.data.daos.ReflectionJournalDao
import com.example.wellnessjournal.data.entities.Goal

/**
 * ViewModel for Methods/Variables Pertaining to Viewing Goals
 */
class GoalsViewModel(application: Application) : AndroidViewModel(application) {
    // Get Journal Related Daos
    private val journalDao: ReflectionJournalDao =
        WellnessJournalDatabase.getDatabase(application)?.ReflectionJournalDao()!!
    private val goalDao: GoalDao =
        WellnessJournalDatabase.getDatabase(application)?.GoalDao()!!

    // Get journal repository
    private val journalRepo: JournalRepository = JournalRepository(journalDao, goalDao)

    // List of all goals
    val listGoals: LiveData<List<Goal>> = journalRepo.listGoals
}