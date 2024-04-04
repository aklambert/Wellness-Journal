package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutLog
import com.example.wellnessjournal.ui.fitness.workouts.WorkoutLogViewModel

/**
 * Custom adapter for list of workout logs
 */
class WorkoutLogListAdapter: RecyclerView.Adapter<WorkoutLogListAdapter.ViewHolder>() {
    private var workoutLogs = listOf<WorkoutLog>()
    private var workouts = listOf<Workout>()

    /**
     * ViewHolder setup
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.list_item_txt)
        val imageView: ImageView = view.findViewById(R.id.list_item_img)

    }

    /**
     * Create new views for each list item
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
     * Replace the new list item content with data
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val logItem = workoutLogs[position]

        if (workouts.isNotEmpty() && position < workouts.size) {
            val logItemWorkout = workouts[position]
            val itemText = logItem.workoutLogDate + ": " + logItemWorkout.workoutName
            holder.textView.text = itemText
        }

        holder.imageView.setImageResource(R.drawable.checkmark_icon)
    }

    /**
     * Set workout log data
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