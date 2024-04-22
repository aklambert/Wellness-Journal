package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Exercise

/**
 * Define custom adapter for RecyclerView for listing exercise data items associated with a workout
 */
class ExercisesForWorkoutListAdapter: RecyclerView.Adapter<ExercisesForWorkoutListAdapter.ViewHolder>() {
    // List of exercises saved to a specific workout being looked at
    private var exercisesForWorkout = listOf<Exercise>()

    /**
     *  Setup ViewHolder(s) (view items used in each list item such as TextViews, CheckBoxes, etc.)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.exercise_checkBox)
    }

    /**
     *  Create new views for list items with the layout manager
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.workout_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    /**
     * Get list size of exercisesForWorkout
     */
    override fun getItemCount(): Int {
        return exercisesForWorkout.size
    }

    /**
     * Replace the contents of the new list item with saved data
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /* Get element from your dataset at this position and replace the
            contents of the view with that element*/
        val item = exercisesForWorkout[position]
        holder.checkBox.text = item.exerciseName.toString()
        val name = item.exerciseName.toString()
        val intensity = item.exerciseIntensity.toString()
        val duration = item.exerciseTime.toString()
        val volume = item.exerciseVolume.toString()

        /* Track number of exercise components that have info to display, to manage vertical spacing
            between exercise name/ title and the rest of the exercise information*/
        var cardInfo: String = name
        if (intensity.isNotEmpty()) {
            cardInfo += "\n$intensity"
        }
        if (duration.isNotEmpty()) {
            cardInfo += "\n$duration"
        }
        if (volume.isNotEmpty()) {
            cardInfo += "\n$volume"
        }

        holder.checkBox.text = cardInfo
    }

    /**
     *  Set data to use and display, and notify data was changed
     */
    @SuppressLint("NotifyDataSetChanged")
    fun addExerciseToList(exercise: Exercise) {
        this.exercisesForWorkout += exercise
        notifyDataSetChanged()
    }
}