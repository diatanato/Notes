package com.diatanato.android.notes.data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase INSTANCE;

    private final static List<Category> CATEGORIES = Arrays.asList(
        new Category("Фильтры", 1),
        new Category("Избранное", 0),
        new Category("Blackdale", 1),
        new Category("Blackdale - The Speckled Chasm", 0),
        new Category("Blackdale - The Sapphire Pit", 0),
        new Category("Blackdale - The Obsidian Trail", 0),
        new Category("Blackdale - The Cuspate Post", 0),
        new Category("Mumor Mine", 1),
        new Category("Mumor Mine - The Great Tunnel", 0),
        new Category("Mumor Mine - Undeveloped Zone", 0),
        new Category("Mumor Mine - Operations Area No.5", 0),
        new Category("Mumor Mine - Mining Zone", 0),
        new Category("Mumor Mine - Excavation Area", 0)
    );

    public abstract CategoryDao getCategoryDao();

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
