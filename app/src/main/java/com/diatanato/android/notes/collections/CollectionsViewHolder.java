package com.diatanato.android.notes.collections;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.database.entities.Collection;

public class CollectionsViewHolder extends RecyclerView.ViewHolder
{
    protected int mId;
    protected final TextView mTitle;

    CollectionsViewHolder(View view)
    {
        super(view);
        mTitle = view.findViewById(R.id.title);
    }

    public void bind(Collection collection)
    {
        mId = collection.id;
        mTitle.setText(collection.name);
    }

    public int getType()
    {
        return -1;
    }
}

class CollectionsGroupViewHolder extends CollectionsViewHolder
{
    CollectionsGroupViewHolder(View view)
    {
        super(view);
    }

    @Override
    public int getType()
    {
        return 1;
    }
}

class CollectionsItemViewHolder extends CollectionsViewHolder
{
    final View divider;

    CollectionsItemViewHolder(View view)
    {
        super(view);
        divider = view.findViewById(R.id.divider);
    }

    public int getCollectionId()
    {
        return mId;
    }

    public void setColor(int color)
    {
        mTitle.setTextColor(color);
    }

    @Override
    public int getType()
    {
        return 0;
    }
}
