package ru.pe9.android.aadatabaseexercise;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {MyNoteEntity.class}, version = 1)
public abstract class MyApplicationDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "PEVNotesDB.db";

    private static MyApplicationDatabase _instance = null;
    public static MyApplicationDatabase getInstance(Context context) {
        if (_instance == null) {
            synchronized (MyApplicationDatabase.class) {
                if (_instance == null) {
                    _instance = Room.databaseBuilder(context.getApplicationContext(),
                            MyApplicationDatabase.class,
                            DATABASE_NAME)
                            .allowMainThreadQueries() /// FOR TEST PURPOSES ///
                            .build();
                }
            } // syncronized //
        }
        return _instance;
    }


    public abstract MyNotesDao getMyNotesDao();

} // MyApplicationDatabase ////
