package com.example.wellnessjournal.ui.journal.goals

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.Goal
import com.example.wellnessjournal.databinding.FragmentUpdateGoalBinding
import java.time.LocalDate

class UpdateGoalFragment : Fragment() {
    private var _binding: FragmentUpdateGoalBinding? = null
    private val binding get() = _binding!!

    private val savedGoal by navArgs<UpdateGoalFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateGoalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel setup
        val updateGoalVM: UpdateGoalViewModel = ViewModelProvider(this)[UpdateGoalViewModel::class.java]

        // Show currently saved goal information
        root.findViewById<EditText>(R.id.input_goal_description).setText(savedGoal.savedGoal.goalDescription)

        // Listen for someone saving changes to the journal entry
        val saveBtn: Button = root.findViewById(R.id.btn_save_goal)
        saveBtn.setOnClickListener {
            saveGoal(root, updateGoalVM)
            findNavController().navigate(R.id.navigation_goals)
        }

        // Listen for someone deleting a goal
        val deleteBtn: Button = root.findViewById(R.id.btn_delete_goal)
        deleteBtn.setOnClickListener {
            val thisGoal = Goal(savedGoal.savedGoal.goalId, savedGoal.savedGoal.goalDescription,
                savedGoal.savedGoal.goalCreationDate)
            updateGoalVM.deleteGoal(thisGoal)
            findNavController().navigate(R.id.navigation_goals)
        }

        return root
    }

    /**
     * Update selected goal
     */
    private fun saveGoal(view: View, viewModel: UpdateGoalViewModel) {
        // Get data to save
        val goalDescription: String = view.findViewById<EditText>(R.id.input_goal_description).text.toString()
        val id: Int = savedGoal.savedGoal.goalId
        val goalDate: String = LocalDate.now().toString()

        // Update the goal
        val updatedGoal: Goal = Goal(id, goalDescription, goalDate)
        viewModel.updateGoal(updatedGoal)
    }
}