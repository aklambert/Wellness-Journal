package com.example.wellnessjournal.ui.welcome

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentWelcomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Check if user has clicked the BEGIN button before, and move right to Home Screen if they have
        val prefs = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)

        if (prefs.contains("welcome_screen_disable") && prefs.getBoolean("welcome_screen_disable", true)) {
            // Startup screen for user is the home screen
            findNavController().navigate(R.id.navigation_home)
        }

        /* Listen for when the user clicks the BEGIN button on the welcome screen, and take note so that the welcome screen
         does not show again */
        val btnBegin = root.findViewById<Button>(R.id.btn_start_timer) as Button
        btnBegin.setOnClickListener {

            // Set shared preference setting to prevent welcome screen from appearing again
            val prefsEditor: SharedPreferences.Editor = prefs.edit()
            prefsEditor.putBoolean("welcome_screen_disable", true)
            prefsEditor.apply()

            // Move to home screen
            findNavController().navigate(R.id.navigation_home)

            // Make sure bottom navigation bar is showing
            val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
            navView.visibility = View.VISIBLE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}