package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Workout Log Entity Definition -
 *  Making this entity parcelable allows for objects of this data class to be used in different
 *  activities or fragments of the app
 */
@Entity(tableName = "workoutLog")
@Parcelize
data class WorkoutLog(
    @PrimaryKey(autoGenerate = true) val workoutLogId: Int,
    val workoutId: Int,
    val workoutLogDate: String
): Parcelable
