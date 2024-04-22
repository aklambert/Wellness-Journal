package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutLog
import com.example.wellnessjournal.ui.fitness.workouts.WorkoutLogFragmentDirections

/**
 * Define custom adapter for RecyclerView for listing workout logs
 */
class WorkoutLogListAdapter: RecyclerView.Adapter<WorkoutLogListAdapter.ViewHolder>() {
    // Lists for saved workout logs, and workouts saved in logs
    private var workoutLogs = listOf<WorkoutLog>()
    private var workouts = listOf<Workout>()

    /**
     *  Setup ViewHolder(s) (view items used in each list item such as TextViews, CheckBoxes, etc.)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.list_item_txt)
        val title: TextView = view.findViewById(R.id.list_item_text_title)
        val imageView: ImageView = view.findViewById(R.id.list_item_img)

    }

    /**
     *  Create new views for list items with the layout manager
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    /**
     *  Get size of the list of workout logs
     */
    override fun getItemCount(): Int {
        return workoutLogs.size
    }

    /**
     * Replace the list content with new data
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val logItem = workoutLogs[position]
        holder.title.text = logItem.workoutLogDate

        if (workouts.isNotEmpty() && position < workouts.size) {
            val logItemWorkout = workouts[position]
            holder.textView.text = logItemWorkout.workoutName
            holder.imageView.setImageResource(R.drawable.checkmark_icon)
            holder.imageView.visibility = View.VISIBLE
        }
        else {
            holder.textView.text = "Deleted workout"
        }

        // Listen for user seeing details for specific workout log
        if (workoutLogs.isNotEmpty()) {
                holder.itemView.findViewById<ConstraintLayout>(R.id.list_item_constraintlayout).setOnClickListener {
                val action = WorkoutLogFragmentDirections.actionNavigationWorkoutLogsToNavigationWorkoutLogDetails(logItem)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    /**
     * Set workout log data and notify the data was changed
     */
    @SuppressLint("NotifyDataSetChanged")
    fun data(workout: List<WorkoutLog>) {
        this.workoutLogs = workout
        notifyDataSetChanged()
    }

    /**
     * Add workout to list of workouts corresponding with the logs
     */
    @SuppressLint("NotifyDataSetChanged")
    fun workoutForLog(workout: Workout) {
        this.workouts += workout
        notifyDataSetChanged()
    }
}