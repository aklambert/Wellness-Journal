package com.example.wellnessjournal.ui.journal.reflectionJournal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wellnessjournal.data.JournalRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.GoalDao
import com.example.wellnessjournal.data.daos.ReflectionJournalDao
import com.example.wellnessjournal.data.entities.ReflectionJournal

class JournalViewModel(application: Application) : AndroidViewModel(application) {
    // Get Journal Daos
    private val reflectionJournalDao: ReflectionJournalDao =
        WellnessJournalDatabase.getDatabase(application)?.ReflectionJournalDao()!!
    private val goalDao: GoalDao =
        WellnessJournalDatabase.getDatabase(application)?.GoalDao()!!

    private val journalRepository: JournalRepository = JournalRepository(reflectionJournalDao, goalDao)
    val listReflectionJournals: LiveData<List<ReflectionJournal>> = journalRepository.listReflectionJournals
}