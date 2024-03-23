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
import com.example.wellnessjournal.CustomAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentExercisesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        val exercisesViewModel = ViewModelProvider(this)[ExercisesViewModel::class.java]

        // Setup adapter with RecyclerView to show data from database
        val customAdapter = CustomAdapter()

        val exerciseRecyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
        val manager = LinearLayoutManager(context)
        exerciseRecyclerView.layoutManager = manager
        exerciseRecyclerView.adapter = customAdapter

        exercisesViewModel.listOfExercises.observe(viewLifecycleOwner, Observer { exercise ->
            customAdapter.data(exercise)
        })

        // Listen for when user is moving to the Exercises screen
        val btnAddExercise: FloatingActionButton = root.findViewById(R.id.btn_add_exercise)
        btnAddExercise.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_exercise)
        }

        return binding.root
    }
}