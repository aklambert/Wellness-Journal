package com.example.wellnessjournal.ui.journal

import android.icu.util.LocaleData
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.JournalEntryAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.data.entities.ReflectionJournal
import com.example.wellnessjournal.databinding.FragmentAddReflectionJournalBinding
import com.example.wellnessjournal.ui.fitness.exercises.AddExerciseViewModel
import java.time.LocalDate
import java.util.Date

class AddReflectionJournalFragment : Fragment() {
    private var _binding: FragmentAddReflectionJournalBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddReflectionJournalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Get viewModel
        val addReflectionJournalVM: AddReflectionJournalViewModel = ViewModelProvider(this)[AddReflectionJournalViewModel::class.java]


        // Listen for when someone saves a reflection journal entry
        val saveBtn: Button = root.findViewById(R.id.btn_save_reflection_journal)
        saveBtn.setOnClickListener {
            saveEntry(root, addReflectionJournalVM)
            findNavController().navigate(R.id.navigation_journal)
        }

        return root
    }

    private fun saveEntry(view: View, viewModel: AddReflectionJournalViewModel) {
        // Get info to save
        val entryEditText = view.findViewById<EditText>(R.id.input_reflection_journal_entry)
        val entryVal = entryEditText.text.toString()
        val date = LocalDate.now().toString()

        val journalEntry = ReflectionJournal(0, entryVal, date)

       viewModel.addReflectionJournal(journalEntry)
    }
}