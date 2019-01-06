package com.diatanato.android.notes.data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

@Database(entities = {Note.class, Collection.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase INSTANCE;

    private final static Collection[] CATEGORIES =
    {
        new Collection("Фильтры", 1),
        new Collection("Избранное", 0),
        new Collection("Blackdale", 1),
        new Collection("Blackdale - The Speckled Chasm", 0),
        new Collection("Blackdale - The Sapphire Pit", 0),
        new Collection("Blackdale - The Obsidian Trail", 0),
        new Collection("Blackdale - The Cuspate Post", 0),
        new Collection("Mumor Mine", 1),
        new Collection("Mumor Mine - The Great Tunnel", 0),
        new Collection("Mumor Mine - Undeveloped Zone", 0),
        new Collection("Mumor Mine - Operations Area No.5", 0),
        new Collection("Mumor Mine - Mining Zone", 0),
        new Collection("Mumor Mine - Excavation Area", 0)
    };

    public abstract NoteDao       getNoteDao();
    public abstract CollectionDao getCategoryDao();

    public synchronized static AppDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE =
                Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "notes-database.db")
                    .allowMainThreadQueries()
                    .addCallback(new Callback()
                    {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db)
                        {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() -> getInstance(context).getCategoryDao().insert(CATEGORIES));
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}
