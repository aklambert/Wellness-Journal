package com.example.wellnessjournal.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Reflection Journal Entity Definition -
 *  Making this entity parcelable allows for objects of this data class to be used in different
 *  activities or fragments of the app
 */
@Entity(tableName = "reflectionJournal")
@Parcelize
data class ReflectionJournal(
    @PrimaryKey(autoGenerate = true) val reflectionJournalId: Int,
    val reflectionJournalEntry: String?,
    val reflectionJournalDate: String
) : Parcelable