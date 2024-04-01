package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "workout")
@Parcelize
data class Workout(
    @PrimaryKey(autoGenerate = true) val workoutId: Int,
    val workoutName: String?,
    val workoutNote: String?
) : Parcelable

