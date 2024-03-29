package com.example.wellnessjournal.ui.journal

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
import com.example.wellnessjournal.JournalEntryAdapter
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentJournalBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class JournalFragment : Fragment() {
    private var _binding: FragmentJournalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel setup
        val journalVM: JournalViewModel = ViewModelProvider(this)[JournalViewModel::class.java]

        // Adapter setup
        val customAdapter = JournalEntryAdapter()
        val recyclerView: RecyclerView = root.findViewById(R.id.journal_recyclerView)
        val manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager
        recyclerView.adapter = customAdapter

        journalVM.listReflectionJournalsa.observe(viewLifecycleOwner, Observer { reflectionJournal ->
            customAdapter.data(reflectionJournal)
        })

        // Listen for when user is moving to the Add ReflectionJournal
        val addJournalBtn: FloatingActionButton = root.findViewById(R.id.btn_add_journal_entry)
        addJournalBtn.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_reflection_journal)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
