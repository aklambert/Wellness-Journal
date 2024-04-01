package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Workout

/**
 * Define custom adapter for RecyclerView for listing data items
 */
class WorkoutListAdapter: RecyclerView.Adapter<WorkoutListAdapter.ViewHolder>() {
    private var workouts = listOf<Workout>()

    // Setup ViewHolder(s) (view items used in each list item)
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.list_item_txt)
    }

    // Create new views for list items with the layout manager
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of the new list item with data
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val workoutItem = workouts[position]
        viewHolder.textView.text = workoutItem.workoutName.toString()

    }

    // Set data to use and display, and notify data was changed
    @SuppressLint("NotifyDataSetChanged")
    fun data(workout: List<Workout>) {
        this.workouts = workout
        notifyDataSetChanged()
    }

    // Get exercise list size
    override fun getItemCount(): Int {
        return workouts.size
    }
}
