package com.example.wellnessjournal.ui.fitness.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.databinding.FragmentAddExerciseBinding

/**
 * Fragment for entering exercise information and saving an exercise to the WellnessJournal database
*/
class AddExerciseFragment : Fragment() {
    private var _binding: FragmentAddExerciseBinding? = null

    // Valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddExerciseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel setup
        val addExerciseVM: AddExerciseViewModel = ViewModelProvider(this)[AddExerciseViewModel::class.java]

        // Listen for when someone saves an exercise
        val btnSave: Button = root.findViewById(R.id.btn_save_exercise)
        btnSave.setOnClickListener {
            // Get information to save, and save it to the database
            val info = getExerciseInput(root)
            saveExercise(info, addExerciseVM)
            findNavController().navigate(R.id.navigation_exercises)
        }

        return root
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
        val id = when (type) {
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
     * Save exercise to the database
     */
    private fun saveExercise(info: Array<Any>, viewModel: AddExerciseViewModel) {
        // Info from array
        val name = info[0].toString()
        val typeId = info[1].toString().toInt()
        val intensity = info[2].toString()
        val time = info[3].toString()
        val volume = info[4].toString()
        val note = info[5].toString()

        val exercise = Exercise(0, name, typeId, intensity, time, volume, note)
        viewModel.createExercise(exercise)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
