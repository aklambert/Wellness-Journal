package com.example.wellnessjournal.ui.journal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentJournalFeaturesBinding

/**
 * Fragment for showing journal related features, and providing access to those features
 */
class JournalFeaturesFragment : Fragment() {
    private var _binding: FragmentJournalFeaturesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalFeaturesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Get feature cards
        val journalCard: CardView = root.findViewById(R.id.reflection_journal_card)
        val goalsCard: CardView = root.findViewById(R.id.goals_card)

        // Listen for when user is moving to the Journal screen
        journalCard.setOnClickListener {
            findNavController().navigate(R.id.navigation_journal)
        }

        // Listen for someone going to the Goals screen
        goalsCard.setOnClickListener {
            findNavController().navigate(R.id.navigation_goals)
        }

        return root
    }
}