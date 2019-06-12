package com.example.androidroomdatabase

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao: NoteDao
    internal val allNotes: LiveData<List<Note>>

    init {
        val noteDB: NoteRoomDatabase? = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDB!!.noteDao()
        allNotes = noteDao.allNotes
    }

    fun insert(note: Note) {
        InsertAsyncTask(noteDao).execute(note) // this class wrap in this method
    }

    // *** for "update" like "insert" we must write AsyncTask
    fun update(note: Note) {
        UpdateAsyncTask(noteDao).execute(note)  // this class wrap in this method
    }

    fun  delete(note: Note){
        DeleteAsyncTask(noteDao).execute(note)  // this class wrap in this method
    }

    companion object {
        private class InsertAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
            override fun doInBackground(vararg params: Note): Void? {
                mNoteDao.insert(params[0])
                return null
            }

        }

        private class UpdateAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
            override fun doInBackground(vararg params: Note): Void? {
                mNoteDao.update(params[0])
                return null
            }

        }

        private class DeleteAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note,Void,Void>() {
            override fun doInBackground(vararg params: Note): Void? {
                mNoteDao.delete(params[0])
                return null
            }

        }
    }
}