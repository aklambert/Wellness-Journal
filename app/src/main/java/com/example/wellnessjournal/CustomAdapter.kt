package com.example.wellnessjournal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Exercise

/**
 * Define custom adapter for RecyclerView for listing data items
 */
class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var exercises = listOf<Exercise>()

     // Setup ViewHolders (view items used in each list item)
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val cardView: CardView = view.findViewById(R.id.list_item_cardview)
         val textView: TextView = view.findViewById(R.id.list_item_txt)
         val imageView: ImageView = view.findViewById(R.id.list_item_img)
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
        val item = exercises[position]
        viewHolder.textView.text = item.exerciseName.toString()
    }

    // Set data to use and display
    fun data(exercise: List<Exercise>) {
        this.exercises = exercise
    }

    // Get database size
    override fun getItemCount() = exercises.size
}
