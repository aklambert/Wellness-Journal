package com.example.wellnessjournal.data.entityrelations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutLog

data class WorkoutWithWorkoutLog(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "workoutId"
    )
    val workoutLogs: List<WorkoutLog>
)
