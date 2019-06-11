package com.example.androidroomdatabase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new.*

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        bSave.setOnClickListener {
            val resultIntent = Intent()
            if(TextUtils.isEmpty(etNote.text)){
                setResult(Activity.RESULT_CANCELED,resultIntent)
            }else{
                val note:String = etNote.text.toString()
                resultIntent.putExtra(NOTE_ADDED,note)
                setResult(Activity.RESULT_OK,resultIntent)
            }
            finish()
        }

        bCancel.setOnClickListener {
            finish()
        }


    }

    companion object {
        val NOTE_ADDED = "new_note"
    }
}