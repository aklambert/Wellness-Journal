package com.example.wellnessjournal.ui.fitness.workouts

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.ExerciseCheckboxListAdapter
import com.example.wellnessjournal.ExerciseListAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.WorkoutListAdapter
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild
import com.example.wellnessjournal.databinding.FragmentAddWorkoutBinding
import com.example.wellnessjournal.ui.fitness.exercises.ExercisesViewModel

class AddWorkoutFragment : Fragment() {

    private var _binding: FragmentAddWorkoutBinding? = null

    // Valid between onCreateView and onDestroyView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddWorkoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Get associated ViewModels
        val addWorkoutViewModel: AddWorkoutViewModel = ViewModelProvider(this)[AddWorkoutViewModel::class.java]
        val exercisesViewModel = ViewModelProvider(this)[ExercisesViewModel::class.java]

        // Setup adapter with RecyclerView to show exercises from database to add to workouts
        val customAdapter = ExerciseCheckboxListAdapter()
        val exerciseRecyclerView: RecyclerView = root.findViewById(R.id.exercises_for_workout_recyclerView)
        val manager = LinearLayoutManager(context)
        exerciseRecyclerView.layoutManager = manager
        exerciseRecyclerView.adapter = customAdapter

        // Listen for when exercise list is updated
        exercisesViewModel.listOfExercises.observe(viewLifecycleOwner, Observer { exercise ->
            customAdapter.data(exercise)
        })

        // Get last workout Id for building the workout
        var workoutId: Int = 1
        addWorkoutViewModel.lastWorkoutId.observe(viewLifecycleOwner, Observer{ lastWorkoutId ->
             if (lastWorkoutId != null) {
                 workoutId = lastWorkoutId + 1
             }
        })

        // Listen for when user is saving a workout
        val saveBtn: Button = root.findViewById(R.id.btn_save_workout)
        saveBtn.setOnClickListener {
            // Get selected exercises to add to new workout
            val selectedExercises = customAdapter.getSelectedItems()

            // Create a new workout
            createWorkout(root, addWorkoutViewModel)

            // Create workoutBuilds
            for (exercise in selectedExercises) {
                buildWorkout(exercise.exerciseId, workoutId, addWorkoutViewModel)
            }

            findNavController().navigate(R.id.navigation_workouts)
        }

        return root
    }

    /**
     * Get workout info (e.g. name, notes, etc.)
     */
    private fun getWorkoutInfo(root: View): Array<Any> {
        val workoutName: EditText = root.findViewById(R.id.input_workout_name)
        val workoutNotes: EditText = root.findViewById(R.id.input_workout_note)

        return arrayOf(workoutName.text.toString(), workoutNotes.text.toString())
    }

    /**
     * Create a new workout
     */
    private fun createWorkout(root: View, viewModel: AddWorkoutViewModel){
        // Get workout info
        val info = getWorkoutInfo(root)
        val name = info[0].toString()
        val notes = info[1].toString()

        // Save the workout
        val workout = Workout(0, name, notes)
        viewModel.saveWorkout(workout)
    }

    /**
     * Create a workout build to save the exercise to the new workout
     */
    private fun buildWorkout(exerciseId: Int, workoutId: Int, viewModel: AddWorkoutViewModel) {
        // Create workout build
        val workoutBuild = WorkoutBuild(0, workoutId, exerciseId)
        viewModel.buildWorkout(workoutBuild)
    }
}