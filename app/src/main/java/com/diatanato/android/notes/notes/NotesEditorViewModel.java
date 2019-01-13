package com.diatanato.android.notes.notes;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.NewInstanceFactory;
import android.content.Context;
import android.support.annotation.NonNull;

import com.diatanato.android.notes.data.database.AppDatabase;
import com.diatanato.android.notes.data.database.Note;

public class NotesEditorViewModel extends ViewModel
{
    public Note note;

    private NotesEditorViewModel(Context context, Integer id)
    {
        if (id > 0)
        {
            note = AppDatabase.getInstance(context).getNoteDao().get(id);
        }
        if (note == null)
        {
            note = new Note();
        }
    }

    public static class Factory extends NewInstanceFactory
    {
        private Integer mId;
        private Context mContext;

        Factory(Context context, Integer id)
        {
            mId      = id;
            mContext = context;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> model)
        {
            return (T) new NotesEditorViewModel(mContext, mId);
        }
    }
}
