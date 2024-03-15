package com.example.wellnessjournal.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int = 1,
    val exerciseName: String,
    val exerciseTypeId: Int,
    val exerciseIntensity: String,
    val exerciseTimeMs: Long,
    val exerciseVolume: String,
    val exerciseNote: String

)
