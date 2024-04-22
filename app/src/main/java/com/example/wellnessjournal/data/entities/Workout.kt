package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Workout Entity Definition -
 *  Making this entity parcelable allows for objects of this data class to be used in different
 *  activities or fragments of the app
 */
@Entity(tableName = "workout")
@Parcelize
data class Workout(
    @PrimaryKey(autoGenerate = true) val workoutId: Int,
    val workoutName: String?,
    val workoutNote: String?
) : Parcelable

