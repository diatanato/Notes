package com.diatanato.android.notes.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Collection
{
    public Collection()
    {
    }

    @Ignore
    public Collection(String name, int type)
    {
        this.name = name;
        this.type = type;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int type;

    public String name;
}

