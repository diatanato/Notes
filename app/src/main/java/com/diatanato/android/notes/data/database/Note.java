package com.diatanato.android.notes.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Note
{
    public Note()
    {
    }

    @Ignore
    public Note(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int collection;

    public String title;
    public String content;

    public long dateCreation;
    public long dateModification;
}
