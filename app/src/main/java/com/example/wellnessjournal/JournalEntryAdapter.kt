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

class JournalEntryAdapter: RecyclerView.Adapter<JournalEntryAdapter.ViewHolder>() {
    private var reflectionJournals = listOf<ReflectionJournal>()

    /**
     * ViewHolder Setup
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val entryTV: TextView = view.findViewById(R.id.journal_item_text)
        val dateEntry: TextView = view.findViewById(R.id.journal_item_date)
    }

    /**
     *
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.journal_entry_layout, viewGroup, false)

        return JournalEntryAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reflectionJournals.size
    }

    // Replace the contents of the new list item with data
    override fun onBindViewHolder(viewHolder: JournalEntryAdapter.ViewHolder, position: Int) {
        // Get element from dataset at this position, and replace the
        // contents of the view with that element
        val item = reflectionJournals[position]
        viewHolder.entryTV.text = item.reflectionJournalEntry.toString()
        viewHolder.dateEntry.text = item.reflectionJournalDate.toString()

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