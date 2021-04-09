package com.example.bookshelfapp.ui.main.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bookshelfapp.ui.main.data.dao.BookDao;
import com.example.bookshelfapp.ui.main.data.entities.BookEntity;

@androidx.room.Database(entities = {BookEntity.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public static final String NAME = "Database";
    private static Database INSTANCE;

    public static Database getDatabase(final Context context){
        if(INSTANCE == null) {
            synchronized (Database.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, Database.NAME).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract BookDao bookDao();
}
