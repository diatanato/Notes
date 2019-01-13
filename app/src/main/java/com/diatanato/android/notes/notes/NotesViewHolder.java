package com.diatanato.android.notes.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.data.database.Note;

class NotesViewHolder extends RecyclerView.ViewHolder
{
    private int mId;

    private Context mContext;

    private View mCard;

    private TextView mTitle;
    private TextView mContent;

    private TextView mDateCreated;
    private TextView mDateModified;

    NotesViewHolder(@NonNull View view)
    {
        super(view);

        mContext = view.getContext();

        mCard = view.findViewById(R.id.card);

        mTitle = view.findViewById(R.id.title);
        mContent = view.findViewById(R.id.content);

        mDateCreated = view.findViewById(R.id.date_created);
        mDateModified = view.findViewById(R.id.date_modified);
    }

    void bind(Note note)
    {
        mId = note.id;

        mTitle.setText(note.title);
        mContent.setText(note.content);

        mDateCreated.setText(mContext.getString(R.string.notes_item_created, getDate(note.dateCreation)));
        mDateModified.setText(mContext.getString(R.string.notes_item_modified, getDate(note.dateModification)));
    }

    void setOnClickListener(View.OnClickListener listener)
    {
        mCard.setOnClickListener(listener);
    }

    public int getId()
    {
        return mId;
    }

    private String getDate(long date)
    {
        if (DateUtils.isToday(date))
        {
            return DateFormat.getTimeFormat(mContext).format(date);
        }
        return DateFormat.getMediumDateFormat(mContext).format(date);
    }
}
