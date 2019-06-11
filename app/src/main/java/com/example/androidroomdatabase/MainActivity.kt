package com.example.androidroomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }

        fab.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // code to insert note
            val noteId:String = UUID.randomUUID().toString() // *** UUID generate random id
            val note = Note(noteId,data!!.getStringExtra(NewNoteActivity.NOTE_ADDED))
            noteViewModel.insert(note)

            Toast.makeText(applicationContext, R.string.saved, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, R.string.not_saved, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    }
}
