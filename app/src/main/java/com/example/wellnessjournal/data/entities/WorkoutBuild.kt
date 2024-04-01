package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "workoutBuild")
@Parcelize
data class WorkoutBuild(
    @PrimaryKey(autoGenerate = true) val workoutBuildId: Int,
    val workoutId: Int,
    val exerciseId: Int
) : Parcelable
