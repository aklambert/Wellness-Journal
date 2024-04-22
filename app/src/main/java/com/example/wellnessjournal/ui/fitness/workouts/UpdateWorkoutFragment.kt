package com.example.wellnessjournal.ui.fitness.workouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.ExerciseCheckboxListAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutBuild
import com.example.wellnessjournal.databinding.FragmentUpdateWorkoutBinding
import com.example.wellnessjournal.ui.fitness.exercises.ExercisesViewModel

/**
 * Fragment for updating workout information and saving it to the WellnessJournal database
 */
class UpdateWorkoutFragment : Fragment() {
private var _binding: FragmentUpdateWorkoutBinding? = null
    private val savedWorkout by navArgs<UpdateWorkoutFragmentArgs>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateWorkoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel Setup
        val updateWorkoutVM: UpdateWorkoutViewModel =
            ViewModelProvider(this)[UpdateWorkoutViewModel::class.java]
        val exercisesViewModel =
            ViewModelProvider(this)[ExercisesViewModel::class.java]
        val addWorkoutVM: AddWorkoutViewModel =
            ViewModelProvider(this)[AddWorkoutViewModel::class.java]


        // Setup adapter with RecyclerView to show exercises for workout
        val customAdapter = ExerciseCheckboxListAdapter()
        val exerciseRecyclerView: RecyclerView = root.findViewById(R.id.exercises_for_workout_recyclerView)
        val manager = LinearLayoutManager(context)
        exerciseRecyclerView.layoutManager = manager
        exerciseRecyclerView.adapter = customAdapter

        // Listen for when exercise list is updated
        exercisesViewModel.listOfExercises.observe(viewLifecycleOwner, Observer { exercise ->
            customAdapter.data(exercise)
        })

        // Get last saved workoutId
        var lastId = 0
        addWorkoutVM.lastWorkoutId.observe(viewLifecycleOwner, Observer { id  ->
            if (id != null) {
                lastId = id
            }
        })

        // Show currently saved workout info
        showCurrentWorkout(root)

        // Listen for when someone updates a workout
        val updateBtn: Button = root.findViewById(R.id.btn_save_workout)
        updateBtn.setOnClickListener {
            val selectedExercises = customAdapter.getSelectedItems()

            // Update the workout
            updateWorkout(root, updateWorkoutVM)

            // Delete old workout builds
            updateWorkoutVM.deleteWorkoutBuildsForWorkout(savedWorkout.selectedWorkout.workoutId)

            // Create new workout builds
            for (exercise in selectedExercises) {
                val workoutBuild = WorkoutBuild(0, savedWorkout.selectedWorkout.workoutId, exercise.exerciseId)
                addWorkoutVM.buildWorkout(workoutBuild)
            }

            // Go back to workout list page
            findNavController().navigate(R.id.navigation_workouts)
        }

        // Listen for when someone deletes a workout
        val deleteBtn: Button = root.findViewById(R.id.btn_delete_workout)
        deleteBtn.setOnClickListener {

            // Don't delete the latest workout saved (for now this is how the correct workoutId for workout builds can be obtained)
            if (savedWorkout.selectedWorkout.workoutId != lastId) {
                // Delete associated workout builds
                updateWorkoutVM.deleteWorkoutBuildsForWorkout(savedWorkout.selectedWorkout.workoutId)

                // Delete this workout
                updateWorkoutVM.deleteWorkoutWithId(savedWorkout.selectedWorkout.workoutId)
                // Go back to workout list page
                findNavController().navigate(R.id.navigation_workouts)
            }
            else {
                // Workout cannot be deleted, so show user a message to communicate that
                root.findViewById<TextView>(R.id.banner_msg).visibility = View.VISIBLE
                root.findViewById<ScrollView>(R.id.update_workout_scrollview).fullScroll(ScrollView.FOCUS_UP);
            }
        }
        return root
    }

    /**
     * Show currently saved workout information
     */
    private fun showCurrentWorkout(view: View) {
        view.findViewById<EditText>(R.id.input_workout_name).setText(savedWorkout.selectedWorkout.workoutName.toString())
        view.findViewById<EditText>(R.id.input_workout_note).setText(savedWorkout.selectedWorkout.workoutNote)
    }

    /**
     * Update the workout
     */
    private fun updateWorkout(view: View, viewModel: UpdateWorkoutViewModel) {
        // Get workout info
        val name: String = view.findViewById<EditText>(R.id.input_workout_name).text.toString()
        val notes: String = view.findViewById<EditText>(R.id.input_workout_note).text.toString()

        // Save workout
        val workout: Workout = Workout(savedWorkout.selectedWorkout.workoutId, name, notes)
        viewModel.updateWorkout(workout)
    }
}