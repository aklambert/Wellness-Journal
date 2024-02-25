package com.example.wellnessjournal.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // commenting this for now because I may learn from it/ use it later
        /*
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        // Check if user has clicked the BEGIN button before, and move right to Home Screen if they have
        val prefs = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)


        var tv = root.findViewById<TextView>(R.id.txt_hello) as TextView
        tv.setText("opened app");

        if (prefs.contains("welcome_screen_disable") && prefs.getBoolean("welcome_screen_disable", true)) {
            // Startup screen for user is the home screen
            tv.setText("disabled")
        }

        /* Listen for when the user clicks the BEGIN button on the welcome screen, and take note so that the welcome screen
         does not show again */
        val btnBegin = root.findViewById<Button>(R.id.btn_begin) as Button
        btnBegin.setOnClickListener {

            // Set shared preference setting to prevent welcome screen from appearing again
            val prefsEditor: SharedPreferences.Editor = prefs.edit()
            prefsEditor.putBoolean("welcome_screen_disable", true)
            prefsEditor.commit()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}