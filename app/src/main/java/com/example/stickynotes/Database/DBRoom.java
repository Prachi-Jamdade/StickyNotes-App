package com.example.stickynotes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.stickynotes.Models.Notes;

@Database(entities = Notes.class, version = 1, exportSchema = false)
public abstract class DBRoom extends RoomDatabase {
    private static DBRoom database;
    private static String DATABASE_NAME = "NotesApp";

    public synchronized static DBRoom getInstance(Context context) {
        if(database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    DBRoom.class, DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }

    public abstract MainDAO mainDAO();
}
