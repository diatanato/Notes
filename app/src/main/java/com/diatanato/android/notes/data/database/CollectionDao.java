package com.diatanato.android.notes.data.database;

import android.arch.lifecycle.LiveData;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CollectionDao
{
    @Update
    void update(Collection collection);

    @Delete
    void delete(Collection collection);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Collection... collections);

    @Query("SELECT COUNT(1) FROM Collection WHERE name = :collection_name")
    Boolean isCollectionNameExist(String collection_name);

    @Query("SELECT * FROM Collection")
    LiveData<List<Collection>> getAll();

    @Query("SELECT * FROM Collection")
    DataSource.Factory<Integer, Collection> getCollectionsDataSource();

    @Query("SELECT * FROM Collection WHERE  id > :id LIMIT :size")
    List<Collection> getPageById(int id, int size);

    @Query("SELECT name FROM Collection WHERE name LIKE :collection_name")
    List<String> findNamesLike(String collection_name);
}
