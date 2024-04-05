package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "goal")
@Parcelize
data class Goal(
    @PrimaryKey(autoGenerate = true) val goalId: Int,
    val goalDescription: String?,
    val goalCreationDate: String
) : Parcelable
