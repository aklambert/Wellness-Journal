package com.example.wellnessjournal.ui.fitness.weightTracking

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.GoalWeightAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentWeightTrackingBinding

class WeightTrackingFragment : Fragment() {


    private var _binding: FragmentWeightTrackingBinding? = null

    // Valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeightTrackingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel setup
        val weightTrackingViewModel: WeightTrackingViewModel =
            ViewModelProvider(this)[WeightTrackingViewModel::class.java]

        val goalWeightBtn: Button = root.findViewById(R.id.update_goal_weight_button)

        // Set up adapter for goal weight changes
        val customAdapter = GoalWeightAdapter()
        val exerciseRecyclerView: RecyclerView = root.findViewById(R.id.goal_weight_recyclerView)
        val manager = LinearLayoutManager(context)
        exerciseRecyclerView.layoutManager = manager
        exerciseRecyclerView.adapter = customAdapter

        weightTrackingViewModel.listGoalWeight.observe(viewLifecycleOwner, Observer { goalWeight ->
            customAdapter.data(goalWeight)
            customAdapter.itemCount
        })

        // figure out if there is a saved goal weight already or not
        val exists = weightTrackingViewModel.isThereAGoalWeight()

        if (exists) {
            // Listen for when someone is adjusting an existing goal weight
            goalWeightBtn.setOnClickListener {
                findNavController().navigate(R.id.navigation_edit_goalweight)
            }
        }
        else {
            // Listen for someone establishing an initial goal weight
            goalWeightBtn.text = getString(R.string.add_goal_weight)
            goalWeightBtn.setOnClickListener {
                findNavController().navigate(R.id.navigation_add_goalweight)
            }
        }
        return root
    }
}