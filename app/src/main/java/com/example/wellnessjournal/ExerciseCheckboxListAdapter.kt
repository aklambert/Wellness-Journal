package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Exercise

/**
 * Define custom adapter for RecyclerView for listing exercise data items
 */
class ExerciseCheckboxListAdapter: RecyclerView.Adapter<ExerciseCheckboxListAdapter.ViewHolder>() {
    /* Adapter lists of saved exercises to choose from (exerciseChoices), and exercises selected to
       save to a workout (selectedExercises)*/
    private var exerciseChoices = listOf<Exercise>()
    private var selectedExercises = listOf<Exercise>()

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
     * Replace the contents of the new list item with saved data
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = exerciseChoices[position]
        viewHolder.checkBox.text = item.exerciseName.toString()

        viewHolder.checkBox.setOnClickListener {
            if (viewHolder.checkBox.isChecked) {
                selectedExercises += item
            }
            else {
                selectedExercises -= item
            }
        }
    }

    /**
     *  Set data to use and display, and notify data was changed
     */
    @SuppressLint("NotifyDataSetChanged")
    fun data(exercise: List<Exercise>) {
        this.exerciseChoices = exercise
        notifyDataSetChanged()
    }

    /**
     * Get list size of exerciseChoices
     */
    override fun getItemCount(): Int {
        return exerciseChoices.size
    }

    /**
     * Get list of selected items
     */
    fun getSelectedItems(): List<Exercise> {
        return selectedExercises
    }
}
