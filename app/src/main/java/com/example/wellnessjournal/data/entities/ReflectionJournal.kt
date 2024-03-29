package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName = "reflectionJournal")
@Parcelize
data class ReflectionJournal(
    @PrimaryKey(autoGenerate = true) val reflectionJournalId: Int,
    val reflectionJournalEntry: String?,
    val reflectionJournalDate: String?
) : Parcelable