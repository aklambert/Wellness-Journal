package com.example.wellnessjournal.data

import androidx.lifecycle.LiveData
import com.example.wellnessjournal.data.daos.GoalDao
import com.example.wellnessjournal.data.daos.ReflectionJournalDao
import com.example.wellnessjournal.data.entities.Goal
import com.example.wellnessjournal.data.entities.ReflectionJournal

/**
 * Repository for methods accessing Dao methods for the Journal Related Entities
 */
class JournalRepository(private val reflectionJournalDao: ReflectionJournalDao,
    private val goalDao: GoalDao) {

    // List of all currently saved reflection journals and goals
    val listReflectionJournals: LiveData<List<ReflectionJournal>> = reflectionJournalDao.getReflectionJournals()
    val listGoals: LiveData<List<Goal>> = goalDao.getGoals()

    /**
     * Create journal entry
     */
    suspend fun createReflectionJournal(reflectionJournal: ReflectionJournal) {
        reflectionJournalDao.insertReflectionJournal(reflectionJournal)
    }

    /**
     * Update existing journal entry
     */
    suspend fun updateReflectionJournal(reflectionJournal: ReflectionJournal) {
        reflectionJournalDao.updateReflectionJournal(reflectionJournal)
    }

    /**
     * Delete a journal entry
     */
    suspend fun deleteReflectionJournal(reflectionJournal: ReflectionJournal) {
        reflectionJournalDao.deleteReflectionJournal(reflectionJournal)
    }

    /**
     * Create a goal
     */
    suspend fun createGoal(goal: Goal) {
        goalDao.insertGoal(goal)
    }

    /**
     * Update a goal
     */
    suspend fun updateGoal(goal:Goal) {
        goalDao.updateGoal(goal)
    }

    /**
     * Delete existing goal
     */
    suspend fun deleteGoal(goal: Goal) {
        goalDao.deleteGoal(goal)
    }
}