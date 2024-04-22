package com.example.wellnessjournal.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Exercise Type Entity Definition
 */
@Entity(tableName = "exercise_type")
data class ExerciseType(
    @PrimaryKey(autoGenerate = true) val exerciseTypeId: Int = 0,
    val exerciseTypeName: String?
)
