package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "exercise")
@Parcelize
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int,
    val exerciseName: String?,
    val exerciseTypeId: Int,
    val exerciseIntensity: String?,
    val exerciseTime: String?,
    val exerciseVolume: String?,
    val exerciseNote: String?
) : Parcelable
