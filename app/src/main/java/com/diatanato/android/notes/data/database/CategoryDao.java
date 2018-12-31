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
    void update(Category category);

    @Delete
    void delete(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Category> categories);

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getAll();

    @Query("SELECT name FROM Category WHERE name LIKE :category_name")
    List<String> findNamesLike(String category_name);
}
