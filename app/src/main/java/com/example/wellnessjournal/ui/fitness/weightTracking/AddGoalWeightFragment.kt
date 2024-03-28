package com.example.wellnessjournal.ui.fitness.weightTracking

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.GoalWeight
import com.example.wellnessjournal.databinding.FragmentAddExerciseBinding
import com.example.wellnessjournal.databinding.FragmentAddGoalWeightBinding

class AddGoalWeightFragment : Fragment() {
    private var _binding: FragmentAddGoalWeightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddGoalWeightBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Get ViewModels
        val addGoalWeightVM: AddGoalWeightViewModel =
            ViewModelProvider(this)[AddGoalWeightViewModel::class.java]

        // Listen for when someone is saving a goal weight
        val addBtn: Button = root.findViewById(R.id.btn_save_goal_weight)

        addBtn.setOnClickListener {
            createGoalWeight(root, addGoalWeightVM)
            findNavController().navigate(R.id.navigation_weight_tracking)
        }

        return root
    }

    private fun createGoalWeight(view: View, viewModel: AddGoalWeightViewModel) {
        // Get saved value for goal weight
        val goalWeightET = view.findViewById<EditText>(R.id.input_goalweight)

        // Save the new goal weight, if there is
        if (goalWeightET.text.isNotEmpty()) {
            val goalWeightVal = goalWeightET.text.toString().toDouble()

            // Save the new goal weight
            val goalWeight = GoalWeight(0, goalWeightVal)
            viewModel.addGoalWeight(goalWeight)
        }
    }
}