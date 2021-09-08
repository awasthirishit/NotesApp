package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity(), INoteRVAdapter {

    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        val ourRecyclerView: RecyclerView = findViewById(R.id.recyclerView)
        ourRecyclerView.layoutManager = LinearLayoutManager(this)
        val ourAdapter = NoteRVAdapter(this)
        ourRecyclerView.adapter = ourAdapter

        viewModel.allNotes.observe(this, Observer {
                list ->
            list?.let {
                ourAdapter.update(it)
            }
        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.delete(note)
    }

    fun submit(view: View) {
        val noteTextValue: TextView = findViewById(R.id.noteTextValue)
        if(noteTextValue.text.isNotEmpty()) {
            viewModel.insert(Note(noteTextValue.text.toString()))
            noteTextValue.text = ""
        }
    }
}