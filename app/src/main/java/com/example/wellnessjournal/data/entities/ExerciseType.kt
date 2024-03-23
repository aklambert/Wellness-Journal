package com.example.wellnessjournal.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_type")
data class ExerciseType(
    @PrimaryKey(autoGenerate = true) val exerciseTypeId: Int,
    val exerciseTypeName: String?
)
