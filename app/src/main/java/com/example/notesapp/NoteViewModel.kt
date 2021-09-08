package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    private val repository: NotesRepository

    init {
        val ourDao: NotesDao = NoteDatabase.getDatabase(application.applicationContext).getNoteDao
        repository= NotesRepository(ourDao)

        allNotes = repository.getallNotes
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}