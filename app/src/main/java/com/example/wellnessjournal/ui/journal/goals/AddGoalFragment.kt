package com.example.wellnessjournal.ui.journal.goals

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
import com.example.wellnessjournal.data.entities.Goal
import com.example.wellnessjournal.databinding.FragmentAddGoalBinding
import java.time.LocalDate

class AddGoalFragment : Fragment() {
    private var _binding: FragmentAddGoalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddGoalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel Setup
        val addGoalVM: AddGoalViewModel = ViewModelProvider(this)[AddGoalViewModel::class.java]

        // Listen for someone saving a new goal
        val saveBtn: Button = root.findViewById(R.id.btn_save_goal)
        saveBtn.setOnClickListener {
            saveGoal(root, addGoalVM)
            findNavController().navigate(R.id.navigation_goals)
        }

        return root
    }

    /**
     * Get goal information and save it in database
     */
    private fun saveGoal(view: View, viewModel: AddGoalViewModel) {
        // Get goal information
        val goalDescription: String = view.findViewById<EditText>(R.id.input_goal_description).text.toString()
        val goalCreationDate: String = LocalDate.now().toString()

        // Create the goal
        val goal: Goal = Goal(0, goalDescription, goalCreationDate)
        viewModel.addGoal(goal)
    }
}