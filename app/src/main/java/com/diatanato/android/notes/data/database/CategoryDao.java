package com.diatanato.android.notes.data.database;

import android.arch.lifecycle.LiveData;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CategoryDao
{
    @Update
    void update(Category collection);

    @Delete
    void delete(Category collection);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category collection);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Category> collection);

    @Query("SELECT COUNT(1) FROM Category WHERE name = :collection_name")
    Boolean isCollectionNameExist(String collection_name);

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getAll();

    @Query("SELECT name FROM Category WHERE name LIKE :collection_name")
    List<String> findNamesLike(String collection_name);
}
