package com.diatanato.android.notes.data.database;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

@Dao
public abstract class NoteDao implements BaseDao<Note>
{
    @Query("SELECT * FROM Note WHERE id = :id")
    public abstract Note get(int id);

    @Query("SELECT * FROM Note")
    public abstract DataSource.Factory<Integer, Note> getDataSource();
}
