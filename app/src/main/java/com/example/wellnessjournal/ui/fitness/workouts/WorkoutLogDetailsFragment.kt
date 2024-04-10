package com.example.wellnessjournal.ui.fitness.workouts

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.databinding.FragmentWorkoutLogDetailsBinding

class WorkoutLogDetailsFragment : Fragment() {
    private var _binding: FragmentWorkoutLogDetailsBinding? = null
    private val binding get() = _binding!!
    private val selectedWorkoutLog by navArgs<WorkoutLogDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutLogDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel setup
        val workoutLogVM: WorkoutLogViewModel = ViewModelProvider(this)[WorkoutLogViewModel::class.java]

        // Show saved data
        val logTitle: String = "Workout log " + selectedWorkoutLog.selectedWorkoutLog.workoutLogDate + ":"
        root.findViewById<TextView>(R.id.name_workout_log).text = logTitle

        // Get workout to show details
        val workoutLiveData = workoutLogVM.getWorkoutById(selectedWorkoutLog.selectedWorkoutLog.workoutId)
        workoutLiveData.observe(viewLifecycleOwner, Observer { workout ->
            if (workout !== null) {
                val workoutNameText = "Workout: " + workout.workoutName
                root.findViewById<TextView>(R.id.workout_name).text = workoutNameText
            }
            else {
                root.findViewById<TextView>(R.id.workout_name).text = "Deleted workout"
            }
        })

        // Listen for when someone deletes the workout log
        val deleteBtn: Button = root.findViewById(R.id.btn_delete_workout_log)
        deleteBtn.setOnClickListener {
            workoutLogVM.deleteWorkoutLog(selectedWorkoutLog.selectedWorkoutLog)
            findNavController().navigate(R.id.navigation_workout_logs)
        }


        return root
    }
}