package com.diatanato.android.notes.data.database;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface NoteDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note... notes);

    @Query("SELECT * FROM Note")
    DataSource.Factory<Integer, Note> getNotesDataSource();
}
