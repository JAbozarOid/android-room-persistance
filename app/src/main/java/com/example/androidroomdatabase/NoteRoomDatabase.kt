package com.example.androidroomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class],version = 1)
abstract class NoteRoomDatabase : RoomDatabase(){

    /**
     * we should instantiated as a singleton , we work this singleton instance of this database class
     * before that this database class should contain a list of dao associated with that
     */

    // this is the dao we have currently
    abstract fun noteDao() : NoteDao

    // *** this is the code created singleton of room
    companion object {
        @Volatile
        private var noteRoomInstance: NoteRoomDatabase? = null

        internal fun getDatabase(context: Context) : NoteRoomDatabase? {
            if (noteRoomInstance == null){
                synchronized(NoteRoomDatabase::class.java){
                    if (noteRoomInstance == null){
                        noteRoomInstance = Room.databaseBuilder(context.applicationContext,NoteRoomDatabase::class.java,"note_db").build()
                    }
                }
            }

            return noteRoomInstance
        }
    }
}