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
import com.example.wellnessjournal.ui.fitness.workouts.WorkoutsFragmentDirections

/**
 * Define custom adapter for RecyclerView for listing workouts
 */
class WorkoutListAdapter: RecyclerView.Adapter<WorkoutListAdapter.ViewHolder>() {
    // List of all saved workouts
    private var workouts = listOf<Workout>()

    /**
     *  Setup ViewHolder(s) (view items used in each list item such as TextViews, CheckBoxes, etc.)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.list_item_txt)
        val textViewTitle: TextView = view.findViewById(R.id.list_item_text_title)
        val imageView: ImageView = view.findViewById(R.id.list_item_img)
    }

    /**
     *  Create new views for list items with the layout manager
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(view)
    }

    /**
     * Replace the contents of the new list item with saved data
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        /* Get element from your dataset at this position and replace the
             contents of the view with that element*/
        val workoutItem = workouts[position]

        // Setup list item text
        viewHolder.textView.text = workoutItem.workoutName.toString()
        viewHolder.textViewTitle.visibility = View.GONE

        // Setup list item image
        viewHolder.imageView.setImageResource(R.drawable.play_icon)
        viewHolder.imageView.visibility = View.VISIBLE
        viewHolder.imageView.layoutParams.height = 150
        viewHolder.imageView.layoutParams.width = 150

        // Listen for navigation from Workouts to UpdateWorkout, and pass the selected workout data
        if (workouts.isNotEmpty()) {
            viewHolder.itemView.findViewById<ConstraintLayout>(R.id.list_item_constraintlayout).setOnClickListener {
                val action = WorkoutsFragmentDirections.actionNavigationWorkoutsToNavigationUpdateWorkouts(workoutItem)
                viewHolder.itemView.findNavController().navigate(action)
            }

            // Listen for navigation from Workouts to PlayWorkout, and pass selected workout
            viewHolder.imageView.setOnClickListener {
                val action = WorkoutsFragmentDirections.actionNavigationWorkoutsToNavigationPlayWorkout(workoutItem)
                viewHolder.itemView.findNavController().navigate(action)
            }
        }
    }

    /**
     *  Set data to use and display, and notify data was changed
     */
    @SuppressLint("NotifyDataSetChanged")
    fun data(workout: List<Workout>) {
        this.workouts = workout
        notifyDataSetChanged()
    }

    /**
     * Get list size of workouts
     */
    override fun getItemCount(): Int {
        return workouts.size
    }
}
