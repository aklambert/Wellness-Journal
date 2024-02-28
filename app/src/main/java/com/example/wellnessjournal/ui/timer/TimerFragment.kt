package com.example.wellnessjournal.ui.timer

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    private var _binding: FragmentTimerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)

        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding.textTimer
        timerViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        val btnTimer: Button = root.findViewById(R.id.btn_timer)

        // Start a timer when the START TIMER button is clicked
        btnTimer.setOnClickListener{
            timer(root, btnTimer)
        }



        return root
    }

    /**
     * Starts or stops the timer, depending on if the timer is running or not
     */
    fun timer(root: View, timer_btn: Button) {
        // Get view items
        val editTxtHours: EditText = root.findViewById(R.id.input_hours)
        val editTxtMinutes: EditText = root.findViewById(R.id.input_minutes)
        val editTextSeconds: EditText = root.findViewById(R.id.input_seconds)
        val colon1: TextView = root.findViewById(R.id.colon1)
        val colon2: TextView = root.findViewById(R.id.colon2)

        val hours: String = editTxtHours.toString()
        val minutes: String = editTxtMinutes.toString()

        // Check shared preferences to see if timer is currently running
        val prefs = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)

        // If timer is currently running, the user is stopping the timer
        if (prefs.contains("timer_running") && prefs.getBoolean("timer_running", true)) {

            // Show the input items when the timer is stopped
            editTxtHours.visibility = View.VISIBLE
            editTxtMinutes.visibility = View.VISIBLE
            editTextSeconds.visibility = View.VISIBLE
            colon1.visibility = View.VISIBLE
            colon2.visibility = View.VISIBLE

            // Show the button as a START TIMER button
            timer_btn.text = getString(R.string.start_timer)
            timer_btn.text = getString(R.string.start_timer)
            context?.let { ContextCompat.getColor(it, R.color.primary_color) }
                ?.let { timer_btn.setBackgroundColor(it) }

            // Set shared preference setting to tell that the timer has been set and is running
            val prefsEditor: SharedPreferences.Editor = prefs.edit()
            prefsEditor.putBoolean("timer_running", false)
            prefsEditor.apply()
        }

        // If timer is not running, the user is starting a timer
        else {

            // Do not show the input items when the timer is running
            editTxtHours.visibility = View.GONE
            editTxtMinutes.visibility = View.GONE
            editTextSeconds.visibility = View.GONE
            colon1.visibility = View.GONE
            colon2.visibility = View.GONE

            // Show the button as a STOP TIMER button
            timer_btn.text = getString(R.string.stop_timer)
            context?.let { ContextCompat.getColor(it, R.color.danger) }
                ?.let { timer_btn.setBackgroundColor(it) }

            // Set shared preference setting to tell that the timer has been set and is running
            val prefsEditor: SharedPreferences.Editor = prefs.edit()
            prefsEditor.putBoolean("timer_running", true)
            prefsEditor.apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}