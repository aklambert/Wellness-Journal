package com.example.wellnessjournal.ui.fitness.weightTracking

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.GoalWeightAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.GoalWeight
import com.example.wellnessjournal.databinding.FragmentEditGoalWeightBinding


class EditGoalWeightFragment : Fragment() {
    private var _binding: FragmentEditGoalWeightBinding? = null
    private val savedGoalWeight by navArgs<EditGoalWeightFragmentArgs>()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditGoalWeightBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Get ViewModels
        val editGoalWeightVM: EditGoalWeightViewModel =
            ViewModelProvider(this)[EditGoalWeightViewModel::class.java]
        val editGoalWeightViewModel: EditGoalWeightViewModel =
            ViewModelProvider(this)[EditGoalWeightViewModel::class.java]

        // Listen for when someone is saving a goal weight edit
        val savebtn: Button = root.findViewById(R.id.btn_save_goal_weight)

        // Set up adapter for goal weight changes
        val customAdapter = GoalWeightAdapter()

        editGoalWeightViewModel.listGoalWeight.observe(viewLifecycleOwner, Observer { goalWeight ->
            customAdapter.data(goalWeight)
        })

        savebtn.setOnClickListener {
            saveGoalWeight(root, editGoalWeightVM)
            findNavController().navigate(R.id.navigation_weight_tracking)
        }

        return root
    }

    private fun saveGoalWeight(view: View, viewModel: EditGoalWeightViewModel) {
        // Get value to save, making sure to only save value if there is one
        val goalWeightET: EditText = view.findViewById(R.id.input_goalweight)

        if (goalWeightET.text.isNotEmpty()) {

        }
    }
}