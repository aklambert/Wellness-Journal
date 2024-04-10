package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.data.entities.WorkoutLog
import com.example.wellnessjournal.ui.fitness.workouts.WorkoutLogFragmentDirections
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
        val title: TextView = view.findViewById(R.id.list_item_text_title)
        val imageView: ImageView = view.findViewById(R.id.list_item_img)

    }

    /**
     * Create new view for list items
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