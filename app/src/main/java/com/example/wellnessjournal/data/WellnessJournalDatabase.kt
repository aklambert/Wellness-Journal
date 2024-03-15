package com.example.wellnessjournal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.ExerciseType

@Database(
    entities = [Exercise::class, ExerciseType::class],
    version = 1
)
abstract class WellnessJournalDatabase: RoomDatabase() {
    abstract val dao: ExerciseDao
}