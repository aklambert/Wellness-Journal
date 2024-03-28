package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.Workout
import com.example.wellnessjournal.ui.fitness.exercises.ExercisesFragmentDirections

/**
 * Define custom adapter for RecyclerView for listing data items
 */
class ExerciseListAdapter: RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {
    private var exercises = listOf<Exercise>()
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
        if (exercises.size > 0) {
            val item = exercises[position]
            viewHolder.textView.text = item.exerciseName.toString()

            viewHolder.itemView.findViewById<ConstraintLayout>(R.id.list_item_constraintlayout).setOnClickListener {
                val action = ExercisesFragmentDirections.actionNavigationExercisesToNavigationUpdateExercise(item)
                viewHolder.itemView.findNavController().navigate(action)
            }
        }

        if (workouts.size > 0) {
            val workoutItem = workouts[position]
            viewHolder.textView.text = workoutItem.workoutName.toString()
        }
    }

    // Set data to use and display, and notify data was changed
    @SuppressLint("NotifyDataSetChanged")
    fun data(exercise: List<Exercise>) {
        this.exercises = exercise
        notifyDataSetChanged()
    }

    // Get exercise list size
    override fun getItemCount(): Int {
        return exercises.size
    }
}
