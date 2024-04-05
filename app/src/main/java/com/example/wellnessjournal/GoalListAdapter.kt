package com.example.wellnessjournal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wellnessjournal.data.entities.Goal
import com.example.wellnessjournal.data.entities.ReflectionJournal
import com.example.wellnessjournal.ui.journal.goals.GoalsFragmentDirections
import com.example.wellnessjournal.ui.journal.reflectionJournal.JournalFragmentDirections

class GoalListAdapter: RecyclerView.Adapter<GoalListAdapter.ViewHolder>() {
    private var goals = listOf<Goal>()

    /**
     * ViewHolder Setup
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val goalTv: TextView = view.findViewById(R.id.journal_item_text)
        val goalCreationDateTv:TextView = view.findViewById(R.id.journal_item_date)
    }

    /**
     * Create new view for list items
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.journal_entry_layout, viewGroup, false)
        return GoalListAdapter.ViewHolder(view)
    }

    /**
     * Get size of the list of goals
     */
    override fun getItemCount(): Int {
        return goals.size
    }

    /**
     * Replace the list content with new data
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from dataset at this position, and replace the
        // contents of the view with that element
        val item = goals[position]
        holder.goalTv.text = item.goalDescription.toString()
        holder.goalCreationDateTv.text = item.goalCreationDate

        holder.itemView.findViewById<ConstraintLayout>(R.id.journal_item_constraintlayout).setOnClickListener {
            val action = GoalsFragmentDirections.actionNavigationGoalsToNavigationUpdateGoal(item)
            holder.itemView.findNavController().navigate(action)
        }

    }

    /**
     * Set data to use and display, and notify data was changed
     */
    @SuppressLint("NotifyDataSetChanged")
    fun data(goalList: List<Goal>) {
        this.goals = goalList
        notifyDataSetChanged()
    }
}