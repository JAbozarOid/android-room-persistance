package com.example.androidroomdatabase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class NoteListAdapter(private val mContext: Context,private val onDeleteClickLis: onDeleteClickListener?) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private var noteList: List<Note> = mutableListOf()

    // for delete note we need an interface so this interface implement in MainActivity class
    interface onDeleteClickListener {
        fun onDeleteClickListener(mynote: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListAdapter.NoteViewHolder {
        val itemView: View = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteListAdapter.NoteViewHolder, position: Int) {
        val note: Note = noteList[position]
        holder.setData(note.note, position)
        holder.setListeners()
    }

    // this method call from mainActivity to populate list of notes
    fun setNotes(notes: List<Note>) {
        noteList = notes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var pos: Int = 0

        fun setData(note: String, position: Int) {
            itemView.txvNote.text = note
            pos = position
        }

        fun setListeners() {
            itemView.setOnClickListener {
                // fetch the data related to that item and send it to editActivity
                val intent = Intent(mContext,EditNoteActivity::class.java)
                intent.putExtra("note_id",noteList[pos].id)
                intent.putExtra("note",noteList[pos].note)
                (mContext as Activity).startActivityForResult(intent,MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)

            }
            itemView.ivDelete.setOnClickListener {
                onDeleteClickLis?.onDeleteClickListener(noteList[pos])
            }
        }
    }
}