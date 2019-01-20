package com.diatanato.android.notes.notes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.diatanato.android.notes.database.AppDatabase;
import com.diatanato.android.notes.database.entities.Note;

public class NotesViewModel extends AndroidViewModel
{
    public LiveData<PagedList<Note>> notes;

    public NotesViewModel(@NonNull Application application)
    {
        super(application);

        PagedList.Config config = new PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(15)
            .setPageSize(15)
            .build();

        notes = new LivePagedListBuilder<>(AppDatabase.getInstance(application).getNoteDao().getDataSource(), config).build();
    }
}