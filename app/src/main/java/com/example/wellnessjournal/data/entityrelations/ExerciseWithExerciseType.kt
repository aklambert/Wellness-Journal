package com.example.wellnessjournal.data.entityrelations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.ExerciseType
import kotlinx.coroutines.flow.Flow

data class ExerciseWithExerciseType(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "exerciseTypeId"
    )
    val exerciseTypes: List<ExerciseType>
)
