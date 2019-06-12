package com.example.androidroomdatabase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new.*

class EditNoteActivity : AppCompatActivity() {

    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val bundle: Bundle? = intent.extras
        bundle?.let {
            id = bundle.getString("note_id")
            val note:String? = bundle.getString("note")
            etNote.setText(note)
        }

        bSave.setOnClickListener {

            // we need to update the data
            val updateNote:String = etNote!!.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra(NOTE_ID,id)
            resultIntent.putExtra(UPDATE_NOTE,updateNote)
            setResult(Activity.RESULT_OK,resultIntent)

            finish()
        }

        bCancel.setOnClickListener {
            finish()
        }


    }

    companion object {
        val NOTE_ID = "note_id"
        internal val UPDATE_NOTE = "note_text"
    }
}