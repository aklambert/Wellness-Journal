package com.example.wellnessjournal.ui.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wellnessjournal.databinding.FragmentFitnessBinding

class FitnessFragment : Fragment() {

    private var _binding: FragmentFitnessBinding? = null

    // Valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFitnessBinding.inflate(inflater, container, false)



        return binding.root
    }
}