package com.example.wellnessjournal.data.entityrelations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.WorkoutBuild

data class WorkoutBuildWithExercise(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "exerciseId"
    )
    val workoutBuild: List<WorkoutBuild>
)
