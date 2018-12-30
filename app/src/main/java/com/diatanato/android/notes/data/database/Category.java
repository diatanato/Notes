package com.diatanato.android.notes.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Category
{
    public Category()
    {
    }

    @Ignore
    public Category(String name, int type)
    {
        this.name = name;
        this.type = type;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int type;

    public String name;
}

