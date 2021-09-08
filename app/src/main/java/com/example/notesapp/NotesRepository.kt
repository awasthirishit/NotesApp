package com.example.notesapp

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao:NotesDao){
    val getallNotes : LiveData<List<Note>> = noteDao.getAllnotes()

    suspend fun insert(note:Note){
        noteDao.insert(note)
    }
    suspend fun delete(note:Note){
        noteDao.delete(note)
    }
}