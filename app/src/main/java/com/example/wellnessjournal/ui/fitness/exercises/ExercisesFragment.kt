package com.example.wellnessjournal.ui.fitness.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.ExerciseListAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentExercisesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Fragment for Showing All Saved Exercises
 */
class ExercisesFragment : Fragment() {

    private var _binding: FragmentExercisesBinding? = null

    // Valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExercisesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Get associated ViewModel
        val exercisesViewModel = ViewModelProvider(this)[ExercisesViewModel::class.java]

        // Setup adapter with RecyclerView to show exercises from database
        val customAdapter = ExerciseListAdapter()
        val exerciseRecyclerView: RecyclerView = root.findViewById(R.id.exercise_recyclerView)
        val manager = LinearLayoutManager(context)
        exerciseRecyclerView.layoutManager = manager
        exerciseRecyclerView.adapter = customAdapter

        // Observe for when the exercise list is updated
        exercisesViewModel.listOfExercises.observe(viewLifecycleOwner, Observer { exercise ->
            customAdapter.data(exercise)
        })

        // Listen for when user is moving to the Add Exercise screen
        val btnAddExercise: FloatingActionButton = root.findViewById(R.id.btn_add_exercise)
        btnAddExercise.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_exercise)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}