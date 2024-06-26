package com.example.wellnessjournal.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wellnessjournal.data.daos.ExerciseDao
import com.example.wellnessjournal.data.daos.ExerciseTypeDao
import com.example.wellnessjournal.data.daos.GoalDao
import com.example.wellnessjournal.data.daos.WorkoutBuildDao
import com.example.wellnessjournal.data.daos.WorkoutDao
import com.example.wellnessjournal.data.daos.ReflectionJournalDao
import com.example.wellnessjournal.data.daos.WorkoutLogDao
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.ExerciseType
import com.example.wellnessjournal.data.entities.Goal
import com.example.wellnessjournal.data.entities.ReflectionJournal
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild
import com.example.wellnessjournal.data.entities.WorkoutLog

/**
 * Database construction
 */
@Database(
    entities = [Exercise::class,
                ExerciseType::class,
                WorkoutBuild::class,
                Workout::class,
                ReflectionJournal::class,
                WorkoutLog::class, Goal::class],
    version = 6
)

/**
 * Database class Definition
 */
abstract class WellnessJournalDatabase : RoomDatabase() {
    // All Daos
    abstract fun ExerciseDao(): ExerciseDao
    abstract fun ExerciseTypeDao(): ExerciseTypeDao
    abstract fun WorkoutBuildDao(): WorkoutBuildDao
    abstract fun WorkoutDao(): WorkoutDao
    abstract fun ReflectionJournalDao(): ReflectionJournalDao
    abstract fun WorkoutLogDao(): WorkoutLogDao
    abstract fun GoalDao(): GoalDao

    /**
     * Companion object for implementing the singleton design pattern (to only have one instance of
     * the database being used)
     */
    companion object {
        @Volatile
        private var INSTANCE: WellnessJournalDatabase? = null

        /**
         * Get instance of database, or create one if it doesn't exist
         */
        fun getDatabase(context: Application): WellnessJournalDatabase? {

            if (INSTANCE == null) {

                // Make sure this only runs on one thread
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WellnessJournalDatabase::class.java, "WellnessJournalDatabase"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}