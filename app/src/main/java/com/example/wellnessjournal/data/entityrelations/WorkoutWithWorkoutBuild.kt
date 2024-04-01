package com.example.wellnessjournal.data.entityrelations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild

data class WorkoutWithWorkoutBuild(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "workoutId"
    )
    val workoutBuild: List<WorkoutBuild>
)
