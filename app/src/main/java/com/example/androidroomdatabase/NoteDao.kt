package com.example.androidroomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @get:Query("SELECT * FROM notes")
    val allNotes: LiveData<List<Note>>

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}