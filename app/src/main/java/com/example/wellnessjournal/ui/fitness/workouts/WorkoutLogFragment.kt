package com.example.wellnessjournal.ui.fitness.workouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.R
import com.example.wellnessjournal.WorkoutLogListAdapter
import com.example.wellnessjournal.databinding.FragmentWorkoutLogBinding

/**
 * Fragment for Showing Saved Workout Logs
 */
class WorkoutLogFragment : Fragment() {
    private var _binding: FragmentWorkoutLogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutLogBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel setup
        val workoutLogVM: WorkoutLogViewModel = ViewModelProvider(this)[WorkoutLogViewModel::class.java]

        // Custom adapter setup
        val customAdapter = WorkoutLogListAdapter()
        val recyclerView: RecyclerView = root.findViewById(R.id.workout_log_recyclerView)
        val manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager
        recyclerView.adapter = customAdapter

        // Show workout log list in adapter
        workoutLogVM.listWorkoutLogs.observe(viewLifecycleOwner, Observer { workoutLogs ->
            // Put workout logs in adapter
            if (workoutLogs.isNotEmpty()) {
                customAdapter.data(workoutLogs)

                // Get workouts for workout logs to put in adapter
                for (log in workoutLogs) {

                    // Get workout for this workoutlog
                    val workoutItem = workoutLogVM.getWorkoutById(log.workoutId)
                    workoutItem.observe(viewLifecycleOwner, Observer { workout ->
                        if (workout !== null) {
                            customAdapter.workoutForLog(workout)
                        }
                    })
                }
            }
        })

        return root
    }
}