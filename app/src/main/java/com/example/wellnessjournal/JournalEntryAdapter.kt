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
import com.example.wellnessjournal.data.entities.ReflectionJournal
import com.example.wellnessjournal.ui.fitness.exercises.ExercisesFragmentDirections
import com.example.wellnessjournal.ui.journal.reflectionJournal.JournalFragmentDirections

/**
 * Define custom adapter for RecyclerView for listing journal entries
 */
class JournalEntryAdapter: RecyclerView.Adapter<JournalEntryAdapter.ViewHolder>() {
    // List of all saved reflection journal entries
    private var reflectionJournals = listOf<ReflectionJournal>()

    /**
     *  Setup ViewHolder(s) (view items used in each list item such as TextViews, CheckBoxes, etc.)
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val entryTV: TextView = view.findViewById(R.id.journal_item_text)
        val dateEntry: TextView = view.findViewById(R.id.journal_item_date)
    }

    /**
     *  Create new views for list items with the layout manager
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.journal_entry_layout, viewGroup, false)

        return JournalEntryAdapter.ViewHolder(view)
    }

    /**
     * Get list size of reflection journals list
     */
    override fun getItemCount(): Int {
        return reflectionJournals.size
    }

    // Replace the contents of the new list item with data
    override fun onBindViewHolder(viewHolder: JournalEntryAdapter.ViewHolder, position: Int) {
        /* Get element from dataset at this position, and replace the
           contents of the view with that element*/
        val item = reflectionJournals[position]
        viewHolder.entryTV.text = item.reflectionJournalEntry.toString()
        viewHolder.dateEntry.text = item.reflectionJournalDate

        /* Listen for when a journal entry is clicked on to edit or delete, and pass the associated
            journal entry information over to the update fragment*/
        viewHolder.itemView.findViewById<ConstraintLayout>(R.id.journal_item_constraintlayout).setOnClickListener {
            val action = JournalFragmentDirections.actionNavigationJournalToNavigationUpdateReflectionJournal(item)
            viewHolder.itemView.findNavController().navigate(action)
        }
    }

    /**
     * Set data to use and display, and notify data was changed
     */
    @SuppressLint("NotifyDataSetChanged")
    fun data(reflectionJournal: List<ReflectionJournal>) {
        this.reflectionJournals = reflectionJournal
        notifyDataSetChanged()
    }
}