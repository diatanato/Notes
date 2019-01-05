package com.diatanato.android.notes.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Note
{
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int collection;

    public String title;
    public String content;

    public Date dateCreation;
    public Date dateModification;
}
