package com.example.wellnessjournal.ui.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentExercisesBinding

class ExercisesFragment : Fragment() {

    private var _binding: FragmentExercisesBinding? = null

    // Valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExercisesBinding.inflate(inflater,container, false)
        val root: View = binding.root



        return root
    }
}