package com.example.wellnessjournal.data

import androidx.lifecycle.LiveData
import com.example.wellnessjournal.data.daos.ReflectionJournalDao
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.ReflectionJournal

class JournalRepository(private val reflectionJournalDao: ReflectionJournalDao) {
    val listReflectionJournals: LiveData<List<ReflectionJournal>> = reflectionJournalDao.getReflectionJournals()

    suspend fun createReflectionJournal(reflectionJournal: ReflectionJournal) {
        reflectionJournalDao.insertReflectionJournal(reflectionJournal)
    }

    suspend fun updateReflectionJournal(reflectionJournal: ReflectionJournal) {
        reflectionJournalDao.updateReflectionJournal(reflectionJournal)
    }

    suspend fun deleteReflectionJournal(reflectionJournal: ReflectionJournal) {
        reflectionJournalDao.deleteReflectionJournal(reflectionJournal)
    }
}