package com.example.wellnessjournal.ui.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentFitnessBinding

/**
 * Fragment for showing all fitness related features and providing access to those features
 */
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

        // Get feature cards
        val exerciseCard: CardView = root.findViewById(R.id.exercises_card)
        val workoutsCard: CardView = root.findViewById(R.id.workouts_card)
        val workoutLogCard: CardView = root.findViewById(R.id.workout_log_card)

        // Listen for when user is moving to the Exercises screen
        exerciseCard.setOnClickListener {
            findNavController().navigate(R.id.navigation_exercises)
        }

        // Listen for when user is moving to the Workouts screen
        workoutsCard.setOnClickListener {
            findNavController().navigate(R.id.navigation_workouts)
        }

        // Listen for when user is moving to the Workout Logs screen
        workoutLogCard.setOnClickListener {
            findNavController().navigate(R.id.navigation_workout_logs)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}