package com.example.androidroomdatabase

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao: NoteDao

    init {
        val noteDB: NoteRoomDatabase? = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDB!!.noteDao()
    }

    fun insert(note: Note) {
        InsertAsyncTask(noteDao).execute(note)
    }

    companion object {
        private class InsertAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
            override fun doInBackground(vararg params: Note): Void? {
                mNoteDao.insert(params[0])
                return null
            }

        }
    }
}