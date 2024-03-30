package com.example.wellnessjournal.ui.journal

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.ReflectionJournal
import com.example.wellnessjournal.databinding.FragmentUpdateReflectionJournalBinding
import com.example.wellnessjournal.ui.fitness.exercises.UpdateExerciseViewModel
import java.time.LocalDate

class UpdateReflectionJournalFragment : Fragment() {
    private var _binding: FragmentUpdateReflectionJournalBinding? = null
    private val savedReflectionJournal by navArgs<UpdateReflectionJournalFragmentArgs>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateReflectionJournalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel setup
        val updateReflectionJournalVM: UpdateReflectionJournalViewModel = ViewModelProvider(this)[UpdateReflectionJournalViewModel::class.java]

        // Show currently saved reflection journal entry
        root.findViewById<EditText>(R.id.input_reflection_journal_entry).setText(savedReflectionJournal.savedReflectionJournal.reflectionJournalEntry)

        // listen for someone saving changes to the journal entry
        val saveBtn: Button = root.findViewById(R.id.btn_save_reflection_journal)
        saveBtn.setOnClickListener {
            saveEditedJournalEntry(root, updateReflectionJournalVM)
            findNavController().navigate(R.id.navigation_journal)
        }

        // Listen for someone deleting a journal entry
        val deleteBtn: Button = root.findViewById(R.id.btn_delete_reflection_journal_entry)
        deleteBtn.setOnClickListener {
            deleteJournalEntry(updateReflectionJournalVM)
            findNavController().navigate(R.id.navigation_journal)
        }

        return root
    }

    private fun saveEditedJournalEntry(view: View, viewModel: UpdateReflectionJournalViewModel) {
        // Get data to save
        val entryET: EditText = view.findViewById(R.id.input_reflection_journal_entry)
        val entry = entryET.text.toString()
        val currentId = savedReflectionJournal.savedReflectionJournal.reflectionJournalId
        val currentDate = LocalDate.now().toString()
        val reflectionJournal: ReflectionJournal = ReflectionJournal(currentId, entry, currentDate)

        viewModel.updateReflectionJournal(reflectionJournal)
    }

    private fun deleteJournalEntry(viewModel: UpdateReflectionJournalViewModel) {
        val currentID = savedReflectionJournal.savedReflectionJournal.reflectionJournalId
        val currentEntry = savedReflectionJournal.savedReflectionJournal.reflectionJournalEntry
        val currentDate = savedReflectionJournal.savedReflectionJournal.reflectionJournalDate
        val fullEntry = ReflectionJournal(currentID, currentEntry, currentDate)

        viewModel.deleteReflectionJournal(fullEntry)
    }
}