package com.example.wellnessjournal.ui.journal.goals

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.GoalListAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentGoalsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GoalsFragment : Fragment() {
    private var _binding: FragmentGoalsBinding? = null
    private val binding get () = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel Setup
        val goalsVM: GoalsViewModel = ViewModelProvider(this)[GoalsViewModel::class.java]

        // Setup recyclerview and the adapter
        val customAdapter = GoalListAdapter()
        val recyclerView: RecyclerView = root.findViewById(R.id.goals_recyclerView)
        val manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager
        recyclerView.adapter = customAdapter

        goalsVM.listGoals.observe(viewLifecycleOwner, Observer { goals ->
            customAdapter.data(goals)
        })

        // Listen for someone going to add a goal
        val addGoalBtn: FloatingActionButton = root.findViewById(R.id.btn_add_goal)
        addGoalBtn.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_goal)
        }

        return root
    }
}