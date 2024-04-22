package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Workout Build Entity Definition -
 *  Making this entity parcelable allows for objects of this data class to be used in different
 *  activities or fragments of the app
 */
@Entity(tableName = "workoutBuild")
@Parcelize
data class WorkoutBuild(
    @PrimaryKey(autoGenerate = true) val workoutBuildId: Int,
    val workoutId: Int,
    val exerciseId: Int
) : Parcelable
