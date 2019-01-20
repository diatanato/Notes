package com.diatanato.android.notes.database.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.diatanato.android.notes.database.entities.Collection;
import com.diatanato.android.notes.database.entities.Note;

import java.util.List;

public class CollectionWithNotes
{
    @Embedded
    public Collection collection;

    @Relation(parentColumn = "id", entityColumn = "collection")
    public List<Note> notes;
}
