package com.example.wellnessjournal.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int,
    val exerciseName: String?,
    val exerciseTypeId: Int,
    val exerciseIntensity: String?,
    val exerciseTime: String?,
    val exerciseVolume: String?,
    val exerciseNote: String?
)
