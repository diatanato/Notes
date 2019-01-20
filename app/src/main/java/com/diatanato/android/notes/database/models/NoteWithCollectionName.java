package com.diatanato.android.notes.database.models;

import android.arch.persistence.room.Embedded;

import com.diatanato.android.notes.database.entities.Note;

public class NoteWithCollectionName
{
    @Embedded
    public Note note;
    public String collectionName;
}
