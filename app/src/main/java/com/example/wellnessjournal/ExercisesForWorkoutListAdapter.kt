package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Exercise

class ExercisesForWorkoutListAdapter: RecyclerView.Adapter<ExercisesForWorkoutListAdapter.ViewHolder>() {
    private var exercisesForWorkout = listOf<Exercise>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.exercise_checkBox)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.workout_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exercisesForWorkout.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = exercisesForWorkout[position]
        holder.checkBox.text = item.exerciseName.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addExerciseToList(exercise: Exercise) {
        this.exercisesForWorkout += exercise
        notifyDataSetChanged()
    }
}