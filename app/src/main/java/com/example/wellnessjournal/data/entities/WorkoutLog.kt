package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "workoutLog")
@Parcelize
data class WorkoutLog(
    @PrimaryKey(autoGenerate = true) val workoutLogId: Int,
    val workoutId: Int,
    val workoutLogDate: String
): Parcelable
