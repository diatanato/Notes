package com.diatanato.android.notes.notes;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.data.database.Note;

import java.util.Objects;

public class NotesDataAdapter extends PagedListAdapter<Note, NotesViewHolder>
{
    private final PublishSubject<Integer> onItemClick = PublishSubject.create();

    private static DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull Note a, @NonNull Note b)
        {
            return a.id == b.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note a, @NonNull Note b)
        {
            return
                Objects.equals(a.title, b.title) &&
                Objects.equals(a.content, b.content);

                //a.dateCreation == b.dateCreation &&
                //a.dateModification == b.dateModification;
        }
    };

    public NotesDataAdapter()
    {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type)
    {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_notes_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position)
    {
        holder.bind(getItem(position));
    }

    @Override
    public void onViewAttachedToWindow(@NonNull NotesViewHolder holder)
    {
        holder.setOnClickListener(view ->
        {
            onItemClick.onNext(holder.getId());
        });
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull NotesViewHolder holder)
    {
        holder.setOnClickListener(null);
    }

    public Observable<Integer> getItemClick()
    {
        return onItemClick;
    }
}
