package com.example.wellnessjournal.ui.journal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessjournal.data.JournalRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.ReflectionJournalDao
import com.example.wellnessjournal.data.entities.ReflectionJournal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateReflectionJournalViewModel(application: Application) : AndroidViewModel(application) {
    private val journalDao: ReflectionJournalDao =
        WellnessJournalDatabase.getDatabase(application)?.ReflectionJournalDao()!!

    private val journalRepo: JournalRepository = JournalRepository(journalDao)

    fun updateReflectionJournal(reflectionJournal: ReflectionJournal) {
        viewModelScope.launch(Dispatchers.IO) {
            journalRepo.updateReflectionJournal(reflectionJournal)
        }
    }

    fun deleteReflectionJournal(reflectionJournal: ReflectionJournal) {
        viewModelScope.launch(Dispatchers.IO) {
            journalRepo.deleteReflectionJournal(reflectionJournal)
        }
    }
}