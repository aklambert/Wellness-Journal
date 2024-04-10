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
        val name = item.exerciseName.toString()
        val intensity = item.exerciseIntensity.toString()
        val duration = item.exerciseTime.toString()
        val volume = item.exerciseVolume.toString()

        // Track number of exercise components that have info to display, to manage vertical spacing
        // between exercise name/ title and the rest of the exercise information
        var infoItems = 0
        var cardInfo: String = name
        if (intensity.isNotEmpty()) {
            cardInfo += intensity
            infoItems++
        }
        if (duration.isNotEmpty()) {
            cardInfo += if(infoItems >= 1) {
                "\n$duration"
            } else {
                duration
            }
            infoItems++
        }
        if (volume.isNotEmpty()) {
            cardInfo += if (infoItems >= 1) {
                "\n$volume"
            } else {
                volume
            }
        }

        holder.checkBox.text = cardInfo
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addExerciseToList(exercise: Exercise) {
        this.exercisesForWorkout += exercise
        notifyDataSetChanged()
    }
}