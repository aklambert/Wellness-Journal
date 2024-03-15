package com.example.wellnessjournal.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int,
    val exerciseTypeId: Int,
    val exerciseIntensity: String?,
    val exerciseTimeMs: Long?,
    val exerciseVolume: String?,
    val exerciseNote: String?
)
