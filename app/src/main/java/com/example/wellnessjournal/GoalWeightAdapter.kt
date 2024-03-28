package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Exercise
import com.example.wellnessjournal.data.entities.GoalWeight


/**
 * Define custom adapter for RecyclerView for listing goal weight
 */
class GoalWeightAdapter: RecyclerView.Adapter<GoalWeightAdapter.ViewHolder>() {
    private var goalWeights = listOf<GoalWeight>()

    // Setup ViewHolders (view items used in each list item)
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.list_item_text)
    }

    // Create new views for list items with the layout manager
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.goal_weight_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of the new list item with data
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = goalWeights[position]
        viewHolder.textView.text = item.goalWeightValue.toString()

        /*viewHolder.itemView.findViewById<Button>(R.id.update_goal_weight_button).setOnClickListener {
            val action = WeightTrackingFragmentDirections.actionNavigationWeightTrackingToNavigationEditGoalweight(item)
            viewHolder.itemView.findNavController().navigate(action)
        }*/
    }

    // Set data to use and display, and notify data was changed
    @SuppressLint("NotifyDataSetChanged")
    fun data(goalWeight: List<GoalWeight>) {
        this.goalWeights = goalWeight
        notifyDataSetChanged()
    }

    // Get database size
    override fun getItemCount() = goalWeights.size
}
