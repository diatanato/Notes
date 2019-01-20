package com.diatanato.android.notes.database.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

public interface BaseDao<T>
{
    @Insert
    void insert(T entity);

    @Insert
    void insert(T... entity);

    @Update
    void update(T entity);

    @Delete
    void delete(T entity);
}
