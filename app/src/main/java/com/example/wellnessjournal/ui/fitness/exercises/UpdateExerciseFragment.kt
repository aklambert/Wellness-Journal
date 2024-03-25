package com.example.wellnessjournal.ui.fitness.exercises

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.databinding.FragmentUpdateExerciseBinding

class UpdateExerciseFragment : Fragment() {
    private var _binding: FragmentUpdateExerciseBinding? = null
    private val args by navArgs<UpdateExerciseFragmentArgs>()

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateExerciseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel setup
        val updateExerciseVM: UpdateExerciseViewModel =
            ViewModelProvider(this)[UpdateExerciseViewModel::class.java]

        // Show current exercise information for exercise being updated
        currentInfo(root)

        // Listen for when someone updates an exercise
        val btnUpdate: Button = root.findViewById(R.id.btn_update_exercise)
        btnUpdate.setOnClickListener {

            // Save updated information
            updateExercise(root, updateExerciseVM)
            findNavController().navigate(R.id.navigation_exercises)
        }

        return root
    }

    /**
     * Show currently saved exercise data in the input fields
     */
    private fun currentInfo(root: View) {
        root.findViewById<EditText>(R.id.input_exercise_name).setText(args.selectedExercise.exerciseName)
        root.findViewById<Spinner>(R.id.input_exercise_type).setSelection(args.selectedExercise.exerciseTypeId)
        root.findViewById<EditText>(R.id.input_exercise_intensity).setText(args.selectedExercise.exerciseIntensity)
        root.findViewById<EditText>(R.id.input_exercise_time).setText(args.selectedExercise.exerciseTime)
        root.findViewById<EditText>(R.id.input_exercise_volume).setText(args.selectedExercise.exerciseVolume)
        root.findViewById<EditText>(R.id.input_exercise_note).setText(args.selectedExercise.exerciseNote)
    }

    /**
     * Get exercise information input to save to database
     */
    private fun getExerciseInput(root: View): Array<Any> {
        // Input items (EditTexts, Spinners, etc.)
        val nameEditText: EditText = root.findViewById(R.id.input_exercise_name)
        val typeSpinner: Spinner = root.findViewById(R.id.input_exercise_type)
        val intensityEditText: EditText = root.findViewById(R.id.input_exercise_intensity)
        val timeEditText: EditText = root.findViewById(R.id.input_exercise_time)
        val volumeEditText: EditText = root.findViewById(R.id.input_exercise_volume)
        val noteEditText: EditText = root.findViewById(R.id.input_exercise_note)

        // Get input values from input items
        val name = nameEditText.text.toString()
        val type = typeSpinner.selectedItem.toString()
        val intensity = intensityEditText.text.toString()
        val time = timeEditText.text.toString()
        val volume = volumeEditText.text.toString()
        val note = noteEditText.text.toString()

        // Match exercise type string with its id number in database
        var id = 0
        id = when (type) {
            "Aerobic Exercise" -> 0
            "Resistance Training" -> 1
            "Stretching" -> 2
            "Balance" -> 3
            "Other" -> 4
            else -> {4}
        }

        return arrayOf(name, id, intensity, time, volume, note)
    }

    /**
     * Update exercise in the database
     */
    private fun updateExercise(root: View, viewModel: UpdateExerciseViewModel) {
        // Get information to save
        val info = getExerciseInput(root)

        val name = info[0].toString()
        val typeId = info[1].toString().toInt()
        val intensity = info[2].toString()
        val time = info[3].toString()
        val volume = info[4].toString()
        val note = info[5].toString()

        val exercise = Exercise(args.selectedExercise.exerciseId, name, typeId, intensity, time, volume, note)
        viewModel.updateExercise(exercise)
    }
}