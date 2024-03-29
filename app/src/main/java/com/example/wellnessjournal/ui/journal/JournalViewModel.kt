package com.example.wellnessjournal.ui.journal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wellnessjournal.data.JournalRepository
import com.example.wellnessjournal.data.WellnessJournalDatabase
import com.example.wellnessjournal.data.daos.ReflectionJournalDao
import com.example.wellnessjournal.data.entities.ReflectionJournal

class JournalViewModel(application: Application) : AndroidViewModel(application) {
    private val reflectionJournalDao: ReflectionJournalDao =
        WellnessJournalDatabase.getDatabase(application)?.ReflectionJournalDao()!!

    private val journalRepository: JournalRepository = JournalRepository(reflectionJournalDao)
    val listReflectionJournalsa: LiveData<List<ReflectionJournal>> = journalRepository.listReflectionJournals
}