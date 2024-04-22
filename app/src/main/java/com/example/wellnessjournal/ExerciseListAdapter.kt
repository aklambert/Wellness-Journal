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
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.ui.fitness.exercises.ExercisesFragmentDirections

/**
 * Define custom adapter for RecyclerView for listing exercises
 */
class ExerciseListAdapter: RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {
    // List of all saved exercises
    private var exercises = listOf<Exercise>()

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
            contents of the view with that element */
        val item = exercises[position]
        val name = item.exerciseName.toString()
        val intensity = item.exerciseIntensity.toString()
        val duration = item.exerciseTime.toString()
        val volume = item.exerciseVolume.toString()

        // Hide image and show the title text
        viewHolder.imageView.visibility = View.GONE
        viewHolder.textViewTitle.visibility = View.VISIBLE
        viewHolder.textViewTitle.text = name

        var infoItems = 0
        var cardInfo: String = ""
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

        viewHolder.textView.text = cardInfo

        // Listen for navigation from Exercises to UpdateExercise, to pass the current exercise data
        if (exercises.isNotEmpty()) {
            viewHolder.itemView.findViewById<ConstraintLayout>(R.id.list_item_constraintlayout).setOnClickListener {
                val action = ExercisesFragmentDirections.actionNavigationExercisesToNavigationUpdateExercise(item)
                viewHolder.itemView.findNavController().navigate(action)
            }
        }
    }

    /**
     *  Set data to use and display, and notify data was changed
     */
    @SuppressLint("NotifyDataSetChanged")
    fun data(exercise: List<Exercise>) {
        this.exercises = exercise
        notifyDataSetChanged()
    }

    /**
     * Get size of exercises list
     */
    override fun getItemCount(): Int {
        return exercises.size
    }
}
