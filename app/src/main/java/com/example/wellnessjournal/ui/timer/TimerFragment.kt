package com.example.wellnessjournal.ui.timer

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
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
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnTimer: Button = root.findViewById(R.id.btn_timer)

        // Initialize timer
        timer = object : CountDownTimer(0, 1) {
            override fun onTick(millisUntilFinished: Long) {
                // do nothing
            }

            override fun onFinish() {
                // do nothing
            }
        }

        // Start a timer when the START TIMER button is clicked
        btnTimer.setOnClickListener{
            toggleTimer(root, btnTimer)
        }

        return root
    }

    /**
     * Starts or stops the timer, depending on if the timer is running or not
     */
    private fun toggleTimer(root: View, timerBtn: Button) {
        // Get view items
        val msTotal: Long = getTimeForTimer(root)

        // Check shared preferences to see if timer is currently running
        val prefs = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)

        // If timer is currently running, the user is stopping the timer
        if (prefs.contains("timer_running") && prefs.getBoolean("timer_running", true)) {
            stopTimer(root, timerBtn, prefs)
        }

        // If timer is not running, the user is starting a timer
        else {
            startTimer(root, timerBtn, prefs, msTotal)
        }
    }

    /**
     * Get a time to set the timer to have ready for if the START TIMER button is clicked
     */
    private fun getTimeForTimer(root: View): Long {
        val editTextHours: TextView  = root.findViewById(R.id.input_hours)
        val editTextMinutes: TextView  = root.findViewById(R.id.input_minutes)
        val editTextSeconds: TextView  = root.findViewById(R.id.input_seconds)

        // Default timer values set to 0 hours, 0 minutes, 0 seconds
        var msHrs: Long = 0
        var msMin: Long = 0
        var msSec: Long = 0

        // Get entered user input for a time to set timer to
        if (editTextHours.text.toString() !== "") {

            val hrsLong: Long? = editTextHours.text.toString().toLongOrNull()
            if (hrsLong != null) {
                msHrs = hrsLong * 3600000
            }
        }
        if (editTextMinutes.text.toString() !== "") {
            val minLong: Long? = editTextMinutes.text.toString().toLongOrNull()
            if (minLong != null) {
                msMin = minLong * 60000
            }
        }
        if (editTextSeconds.text.toString() !== "") {
            val secLong: Long? = editTextSeconds.text.toString().toLongOrNull()
            if (secLong != null) {
                msSec = secLong * 1000
            }
        }

        // Convert input time to one value in milliseconds for countdown timer
        return msHrs + msMin + msSec
    }

    /**
     * Start the timer
     */
    private fun startTimer(root: View, timer_btn: Button, prefs: SharedPreferences, time: Long) {

        val editTextHours: TextView = root.findViewById(R.id.input_hours)
        val editTextMinutes: TextView = root.findViewById(R.id.input_minutes)
        val editTextSeconds: TextView = root.findViewById(R.id.input_seconds)

        val colon1: TextView = root.findViewById(R.id.colon1)
        val colon2: TextView = root.findViewById(R.id.colon2)
        val timerProgressView: TextView = root.findViewById(R.id.timer_progress_view)

        // Do not show the input items when the timer is running
        editTextHours.visibility = View.GONE
        editTextMinutes.visibility = View.GONE
        editTextSeconds.visibility = View.GONE
        colon1.visibility = View.GONE
        colon2.visibility = View.GONE

        // Show the button as a STOP TIMER button
        timer_btn.text = getString(R.string.stop_timer)
        context?.let { ContextCompat.getColor(it, R.color.danger) }
            ?.let { timer_btn.setBackgroundColor(it) }

        // Start the timer and show timer remaining
        timer = object : CountDownTimer(time, 1) {
            override fun onTick(millisUntilFinished: Long) {
                updateTimeRemaining(root, millisUntilFinished)
            }

            override fun onFinish() {
                timerProgressView.text = "Timer Finished!"
                val alarm: MediaPlayer = MediaPlayer.create(context, R.raw.simple_notification)
                alarm.start()
            }
        }.start()

        timerProgressView.visibility = View.VISIBLE

        // Set shared preference setting to tell that the timer has been set and is running
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        prefsEditor.putBoolean("timer_running", true)
        prefsEditor.apply()
    }

    /**
     * Update the time remaining that is shown to the user
     */
    private fun updateTimeRemaining(root: View, time: Long) {
        val timerProgressView: TextView = root.findViewById(R.id.timer_progress_view)
        val hrs: Long = time / 3600000
        val mins: Long = ((time / 1000) % 3600) / 60
        val seconds: Long = (time / 1000) % 60

        val progress: String = hrs.toString() + " : " + mins.toString() +
                " : " + seconds.toString()

        timerProgressView.text = progress
    }

    /**
     * Cancel the timer
     */
     private fun stopTimer(root: View, timer_btn: Button, prefs: SharedPreferences) {

        val editTextHours: EditText = root.findViewById(R.id.input_hours)
        val editTextMinutes: EditText = root.findViewById(R.id.input_minutes)
        val editTextSeconds: EditText = root.findViewById(R.id.input_seconds)

        val colon1: TextView = root.findViewById(R.id.colon1)
        val colon2: TextView = root.findViewById(R.id.colon2)
        val timerProgressView: TextView = root.findViewById(R.id.timer_progress_view)

        // Show the input items when the timer is stopped
        editTextHours.visibility = View.VISIBLE
        editTextMinutes.visibility = View.VISIBLE
        editTextSeconds.visibility = View.VISIBLE
        colon1.visibility = View.VISIBLE
        colon2.visibility = View.VISIBLE

        // Show the button as a START TIMER button and hide the time remaining
        timer_btn.text = getString(R.string.start_timer)
        timer_btn.text = getString(R.string.start_timer)
        context?.let { ContextCompat.getColor(it, R.color.primary_color) }
            ?.let { timer_btn.setBackgroundColor(it) }

        timerProgressView.visibility = View.GONE
        if (timer !== null) {
            timer.cancel()
        }

        // Set shared preference setting to tell that the timer has been set and is running
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        prefsEditor.putBoolean("timer_running", false)
        prefsEditor.apply()
    }

    /**
     * Enable START TIMER button if there is an entered time value, otherwise disable it.
     * This is for when a timer is not running.
     */
    private fun startTimerEnableDisable(btn: Button, time: Long) {
        if (time == 0.toLong()) {
            btn.isEnabled = false
            btn.isClickable = false
            context?.let { ContextCompat.getColor(it, R.color.primary_color_variant1) }
                ?.let { btn.setBackgroundColor(it) }
        }
        else {
            btn.isEnabled = true
            btn.isClickable = true
            context?.let { ContextCompat.getColor(it, R.color.primary_color) }
                ?.let { btn.setBackgroundColor(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}