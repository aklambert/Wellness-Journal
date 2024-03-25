package com.example.wellnessjournal.ui.fitness.exercises
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.databinding.FragmentUpdateExerciseBinding

class UpdateExerciseFragment : Fragment() {
    private var _binding: FragmentUpdateExerciseBinding? = null
    private val savedExercise by navArgs<UpdateExerciseFragmentArgs>()

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

        // Listen for when someone deletes an exercise
        val btnDelete: Button = root.findViewById(R.id.btn_delete_exercise)
        btnDelete.setOnClickListener {
            // Delete exercise
            deleteExercise(updateExerciseVM)
            findNavController().navigate(R.id.navigation_exercises)
        }

        return root
    }

    /**
     * Show currently saved exercise data in the input fields
     */
    private fun currentInfo(root: View) {
        root.findViewById<EditText>(R.id.input_exercise_name).setText(savedExercise.selectedExercise.exerciseName)
        root.findViewById<Spinner>(R.id.input_exercise_type).setSelection(savedExercise.selectedExercise.exerciseTypeId)
        root.findViewById<EditText>(R.id.input_exercise_intensity).setText(savedExercise.selectedExercise.exerciseIntensity)
        root.findViewById<EditText>(R.id.input_exercise_time).setText(savedExercise.selectedExercise.exerciseTime)
        root.findViewById<EditText>(R.id.input_exercise_volume).setText(savedExercise.selectedExercise.exerciseVolume)
        root.findViewById<EditText>(R.id.input_exercise_note).setText(savedExercise.selectedExercise.exerciseNote)
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

        val exercise = Exercise(savedExercise.selectedExercise.exerciseId, name, typeId, intensity, time, volume, note)
        viewModel.updateExercise(exercise)
    }

    /**
     * Delete exercise
     */
    private fun deleteExercise(viewModel: UpdateExerciseViewModel) {
        // Get exercise to delete
        val id = savedExercise.selectedExercise.exerciseId
        val name = savedExercise.selectedExercise.exerciseName
        val type = savedExercise.selectedExercise.exerciseTypeId
        val intensity = savedExercise.selectedExercise.exerciseIntensity
        val time = savedExercise.selectedExercise.exerciseTime
        val volume = savedExercise.selectedExercise.exerciseVolume
        val note = savedExercise.selectedExercise.exerciseNote

        val exercise = Exercise(id, name, type, intensity, time, volume, note)
        viewModel.deleteExercise(exercise)
    }
}