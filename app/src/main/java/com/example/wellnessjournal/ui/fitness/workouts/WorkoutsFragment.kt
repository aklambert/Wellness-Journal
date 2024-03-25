package com.example.wellnessjournal.ui.fitness.workouts
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.CustomAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentWorkoutsBinding

class WorkoutsFragment : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null

    // Valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Get associated ViewModel
        val workoutsViewModel = ViewModelProvider(this)[WorkoutsViewModel::class.java]

        // Set up adapter with RecyclerView to show workouts from database
        val customAdapter = CustomAdapter()
        val workoutRecyclerView: RecyclerView = root.findViewById(R.id.workout_recyclerView)
        val manager = LinearLayoutManager(context)
        workoutRecyclerView.layoutManager = manager
        workoutRecyclerView.adapter = customAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}