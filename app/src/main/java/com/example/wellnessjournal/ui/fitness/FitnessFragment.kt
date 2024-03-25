package com.example.wellnessjournal.ui.fitness

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentFitnessBinding
import com.example.wellnessjournal.ui.fitness.exercises.ExercisesFragment

class FitnessFragment : Fragment() {

    private var _binding: FragmentFitnessBinding? = null

    // Valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFitnessBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val exerciseCard: CardView = root.findViewById(R.id.exercises_card)

        // Listen for when user is moving to the Exercises screen
        exerciseCard.setOnClickListener {
            findNavController().navigate(R.id.navigation_exercises)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}