package com.diatanato.android.notes.notes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.support.annotation.NonNull;

import com.diatanato.android.notes.data.database.AppDatabase;
import com.diatanato.android.notes.data.database.Note;

public class NotesViewModel extends ViewModel
{
    public LiveData<PagedList<Note>> notes;

    public NotesViewModel(Context context)
    {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(15)
                .setPageSize(15)
                .build();

        notes = new LivePagedListBuilder<>(AppDatabase.getInstance(context).getNoteDao().getDataSource(), config).build();
    }

    public static class NotesViewModelFactory extends ViewModelProvider.NewInstanceFactory
    {
        private Context mContext;

        public NotesViewModelFactory(Context context)
        {
            mContext = context;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(Class<T> viewModel)
        {
            return (T) new NotesViewModel(mContext);
        }
    }
}