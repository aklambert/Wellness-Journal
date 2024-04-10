package com.example.wellnessjournal.ui.fitness.workouts

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.wellnessjournal.ExerciseCheckboxListAdapter
import com.example.wellnessjournal.ExercisesForWorkoutListAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.WorkoutLog
import com.example.wellnessjournal.databinding.FragmentPlayWorkoutBinding
import java.time.LocalDate

class PlayWorkoutFragment : Fragment() {
    private var _binding: FragmentPlayWorkoutBinding? = null
    private val workout by navArgs<PlayWorkoutFragmentArgs>()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayWorkoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel setup
        val playWorkoutVM = ViewModelProvider(this)[PlayWorkoutViewModel::class.java]

        // Custom adapter setup
        val customAdapter = ExercisesForWorkoutListAdapter()
        val recyclerView: RecyclerView = root.findViewById(R.id.exercises_for_workout_recyclerView)
        val manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager
        recyclerView.adapter = customAdapter

        // Get list of workoutBuilds for Workout
        val workoutBuilds = playWorkoutVM.getWorkoutBuildsForWorkout(workout.workoutToPlay.workoutId)
        workoutBuilds.observe(viewLifecycleOwner, Observer { builds ->
            if (builds.isNotEmpty()) {
                for (build in builds) {

                    // Get exercises associated with this workout, and show it in the recyclerview
                    val exercises = playWorkoutVM.getExerciseById(build.exerciseId)
                    exercises.observe(viewLifecycleOwner, Observer { exercise ->
                        if (exercise !== null) {
                            customAdapter.addExerciseToList(exercise)
                        }
                   })
                }
            }
        })

        // Show current workout name and notes
        root.findViewById<TextView>(R.id.name_workout).text = workout.workoutToPlay.workoutName
        root.findViewById<TextView>(R.id.workout_note).text = workout.workoutToPlay.workoutNote

        // Listen for when someone logs a workout
        root.findViewById<Button>(R.id.btn_log_workout).setOnClickListener {
            // Get date to save in the log, and save it in the database
            val date = LocalDate.now().toString()
            val workoutLog = WorkoutLog(0, workout.workoutToPlay.workoutId, date)
            playWorkoutVM.logWorkout(workoutLog)
            findNavController().navigate(R.id.navigation_workouts)
        }

        return root
    }
}