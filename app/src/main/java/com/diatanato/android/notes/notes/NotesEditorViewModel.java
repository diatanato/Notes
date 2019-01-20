package com.diatanato.android.notes.notes;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.NewInstanceFactory;
import android.content.Context;
import android.support.annotation.NonNull;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.database.AppDatabase;
import com.diatanato.android.notes.database.entities.Note;
import com.diatanato.android.notes.database.models.NoteWithCollectionName;

public class NotesEditorViewModel extends ViewModel
{
    public Note note;
    public String collection;

    private NotesEditorViewModel(Context context, Integer id)
    {
        if (id > 0)
        {
            NoteWithCollectionName data = AppDatabase.getInstance(context).getNoteDao().getNoteWithCollection(id);

            note = data.note;
            collection = data.collectionName;
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
