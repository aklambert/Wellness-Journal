package com.example.wellnessjournal.ui.home

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wellnessjournal.R
import com.example.wellnessjournal.databinding.FragmentHomeBinding
import com.example.wellnessjournal.ui.fitness.workouts.WorkoutLogViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Calendar
import kotlin.time.Duration.Companion.days

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // Valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel Setup
        val workoutLogVM: WorkoutLogViewModel = ViewModelProvider(this)[WorkoutLogViewModel::class.java]

        // Get checkmark image views
        val sundayCheck: ImageView = root.findViewById(R.id.sunday_checkmark)
        val mondayCheck: ImageView = root.findViewById(R.id.monday_checkmark)
        val tuesCheck: ImageView = root.findViewById(R.id.tuesday_checkmark)
        val wednesdayCheck: ImageView = root.findViewById(R.id.wednesday_checkmark)
        val thursdayCheck: ImageView = root.findViewById(R.id.thursday_checkmark)
        val fridayCheck: ImageView = root.findViewById(R.id.friday_checkmark)
        val saturdayCheck: ImageView = root.findViewById(R.id.saturday_checkmark)

        // Get dates for this week
        val thisWeek = getDatesThisWeek()
        val sunday = thisWeek?.get(0)
        val saturday = thisWeek?.get(1)

        // List of workout logs for displaying data
        workoutLogVM.listWorkoutLogs.observe(viewLifecycleOwner, Observer { workoutLogs ->
            // Reset the checkmarks showing
            resetCheckMarks(root)

            if (workoutLogs.isNotEmpty()) {
                for (log in workoutLogs) {

                    // Check if the log is for this week
                    val date = LocalDate.parse(log.workoutLogDate)

                    if ((date.isEqual(sunday) || date.isAfter(sunday)) &&
                        (date.isEqual(saturday) || date.isBefore(saturday))) {

                        // Show the workout completed checkmark for this day
                        showCheckMark(root, date)
                    }
                }
            }
        })

        return root
    }

    /**
     * Reset showing checkmarks to ensure data is always up to date
     */
    private fun resetCheckMarks(root: View) {
        root.findViewById<ImageView>(R.id.sunday_checkmark).visibility = View.INVISIBLE
        root.findViewById<ImageView>(R.id.monday_checkmark).visibility = View.INVISIBLE
        root.findViewById<ImageView>(R.id.tuesday_checkmark).visibility = View.INVISIBLE
        root.findViewById<ImageView>(R.id.wednesday_checkmark).visibility = View.INVISIBLE
        root.findViewById<ImageView>(R.id.thursday_checkmark).visibility = View.INVISIBLE
        root.findViewById<ImageView>(R.id.friday_checkmark).visibility = View.INVISIBLE
        root.findViewById<ImageView>(R.id.saturday_checkmark).visibility = View.INVISIBLE
    }

    /**
     * Show a completion checkmark next to the day a workout was logged
     */
    private fun showCheckMark(root: View, date: LocalDate) {
        // Get day of week for the given date
        val dayOfWeek = dayOfWeek(date)

        when (dayOfWeek) {
            Calendar.SUNDAY -> {
                root.findViewById<ImageView>(R.id.sunday_checkmark).visibility = View.VISIBLE
            }
            Calendar.MONDAY -> {
                root.findViewById<ImageView>(R.id.monday_checkmark).visibility = View.VISIBLE
            }
            Calendar.TUESDAY -> {
                root.findViewById<ImageView>(R.id.tuesday_checkmark).visibility = View.VISIBLE
            }
            Calendar.WEDNESDAY -> {
                root.findViewById<ImageView>(R.id.wednesday_checkmark).visibility = View.VISIBLE
            }
            Calendar.THURSDAY -> {
                root.findViewById<ImageView>(R.id.thursday_checkmark).visibility = View.VISIBLE
            }
            Calendar.FRIDAY -> {
                root.findViewById<ImageView>(R.id.friday_checkmark).visibility = View.VISIBLE
            }

            Calendar.SATURDAY -> {
                root.findViewById<ImageView>(R.id.saturday_checkmark).visibility = View.VISIBLE
            }
            else -> {
                // do nothing
                return
            }
        }
    }

    /**
     * Get the day of week from a LocalDate, adding a day to represent a week starting on Sunday
     * rather than Monday
     */
    private fun dayOfWeek(date: LocalDate): Int {
        return if(date.dayOfWeek.value == 7) {
            1
        }
        else {
            date.dayOfWeek.value + 1
        }
    }

    /**
     * Get the first and last dates from this week
     * (First day is Sunday, last day is Saturday)
     */
    private fun getDatesThisWeek(): Array<LocalDate>?  {
        lateinit var sunday: LocalDate
        lateinit var saturday: LocalDate

        // Get today's day of the week represented as an integer (SUNDAY = 1, MONDAY = 2, .... SATURDAY = 7)
        val today = dayOfWeek(LocalDate.now())

        when (today) {
            Calendar.SUNDAY -> {
                sunday = LocalDate.now()
                saturday = LocalDate.now().plusDays(6)

            }
            Calendar.MONDAY -> {
                sunday = LocalDate.now().minusDays(1)
                saturday = LocalDate.now().plusDays(5)
            }
            Calendar.TUESDAY -> {
                sunday = LocalDate.now().minusDays(2)
                saturday = LocalDate.now().plusDays(4)
            }
            Calendar.WEDNESDAY -> {
                sunday = LocalDate.now().minusDays(3)
                saturday = LocalDate.now().plusDays(3)
            }
            Calendar.THURSDAY -> {
                sunday = LocalDate.now().minusDays(4)
                saturday = LocalDate.now().plusDays(2)
            }
            Calendar.FRIDAY -> {
                sunday = LocalDate.now().minusDays(5)
                saturday = LocalDate.now().plusDays(1)
            }

            Calendar.SATURDAY -> {
                sunday = LocalDate.now().minusDays(6)
                saturday = LocalDate.now()
            }
            else -> {
                return null
            }
        }

        return arrayOf(sunday, saturday)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}