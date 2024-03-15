package com.example.wellnessjournal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_type")
data class ExerciseType(
    @PrimaryKey(autoGenerate = true) val exerciseTypeId: Int = 1,
    val exerciseTypeName: String
)
