package com.example.wellnessjournal.ui.fitness.workouts
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.R
import com.example.wellnessjournal.WorkoutListAdapter
import com.example.wellnessjournal.databinding.FragmentWorkoutsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WorkoutsFragment : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null

    // Valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Get associated ViewModels
        val workoutsViewModel = ViewModelProvider(this)[WorkoutsViewModel::class.java]
        val addWorkoutViewModel = ViewModelProvider(this)[AddWorkoutViewModel::class.java]

        // Set up workout adapter with RecyclerView to show workouts from database
        val customAdapter = WorkoutListAdapter()
        val workoutRecyclerView: RecyclerView = root.findViewById(R.id.workout_recyclerView)
        val manager = LinearLayoutManager(context)
        workoutRecyclerView.layoutManager = manager
        workoutRecyclerView.adapter = customAdapter

        // Observe for when the workout list is updated
        workoutsViewModel.listOfWorkouts.observe(viewLifecycleOwner, Observer { workout ->
            // Create a workoutBuild to associate exercises with workouts
            customAdapter.data(workout)
        })

        // Listen for when someone is going to the Add Workout Screen
        val btnAddWorkout: FloatingActionButton = root.findViewById(R.id.btn_add_workout)
        btnAddWorkout.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_workout)
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}