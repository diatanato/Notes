package com.diatanato.android.notes.database.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.diatanato.android.notes.database.entities.Note;
import com.diatanato.android.notes.database.models.NoteWithCollectionName;

@Dao
public abstract class NoteDao implements BaseDao<Note>
{
    @Query
    (
        "SELECT Note.*, Collection.name AS collectionName " +
        "FROM Note " +
        "LEFT JOIN Collection ON Note.collection = Collection.id " +
        "WHERE Note.id = :id"
    )
    public abstract NoteWithCollectionName getNoteWithCollection(int id);

    @Query
    (
        "SELECT * FROM Note"
    )
    public abstract DataSource.Factory<Integer, Note> getDataSource();
}
