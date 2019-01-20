package com.diatanato.android.notes.database.dao;

import android.arch.lifecycle.LiveData;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.diatanato.android.notes.database.entities.Collection;
import com.diatanato.android.notes.database.models.CollectionWithNotes;

import java.util.List;

@Dao
public abstract class CollectionDao implements BaseDao<Collection>
{
    @Query("SELECT COUNT(1) FROM Collection WHERE name = :collection_name")
    public abstract Boolean isCollectionNameExist(String collection_name);

    @Query("SELECT * FROM Collection")
    public abstract LiveData<List<Collection>> getAll();

    @Query("SELECT * FROM Collection")
    public abstract DataSource.Factory<Integer, Collection> getDataSource();

    @Query("SELECT * FROM Collection WHERE  id > :id LIMIT :size")
    public abstract List<Collection> getPageById(int id, int size);

    @Query("SELECT name FROM Collection WHERE name LIKE :collection_name")
    public abstract List<String> findNamesLike(String collection_name);
}
