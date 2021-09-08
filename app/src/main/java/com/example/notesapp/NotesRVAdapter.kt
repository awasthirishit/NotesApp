package com.example.notesapp
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(private val listener: INoteRVAdapter): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteText: TextView = itemView.findViewById(R.id.noteText)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentnote = allNotes[position]
       holder.noteText.text =currentnote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun update(newNotes: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newNotes)

        notifyDataSetChanged()
    }
}

interface INoteRVAdapter {
    fun onItemClicked(note: Note)
}