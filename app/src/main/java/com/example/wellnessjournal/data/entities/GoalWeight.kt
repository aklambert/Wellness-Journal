package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "goalWeight")
@Parcelize
data class GoalWeight(
    @PrimaryKey(autoGenerate = true) val goalWeightId: Int,
    val goalWeightValue: Double?
) : Parcelable
