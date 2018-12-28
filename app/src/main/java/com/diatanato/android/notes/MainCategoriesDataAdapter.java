package com.diatanato.android.notes;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainCategoriesDataAdapter extends RecyclerView.Adapter<MainCategoriesDataAdapter.ViewHolder>
{
    private final int TYPE_ITEM  = 0;
    private final int TYPE_GROUP = 1;

    private Cursor mCursor;

    public MainCategoriesDataAdapter(Cursor cursor)
    {
        mCursor = MergeCursor(cursor);
    }

    //TODO: добавляем группы
    public Cursor MergeCursor(Cursor cursor)
    {
        return cursor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type)
    {
        switch (type)
        {
            case TYPE_ITEM: return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_categories_item, parent, false));
            case TYPE_GROUP: return new GroupViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_categories_group, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        mCursor.moveToPosition(position);
        holder.title.setText(mCursor.getString(mCursor.getColumnIndex("name")));

        if (holder.getType() == 0)
        {
            ((ItemViewHolder)holder).divider.setVisibility(mCursor.moveToNext() && mCursor.getInt(mCursor.getColumnIndex("type")) == 1 ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public int getItemCount()
    {
        return mCursor.getCount();
    }

    @Override
    public int getItemViewType(int position)
    {
        mCursor.moveToPosition(position);
        return mCursor.getInt(mCursor.getColumnIndex("type"));
    }

    class GroupViewHolder extends  ViewHolder
    {

        GroupViewHolder(View view)
        {
            super(view);
        }

        @Override
        public int getType()
        {
            return 1;
        }
    }

    class ItemViewHolder extends  ViewHolder
    {
        final View divider;

        ItemViewHolder(View view)
        {
            super(view);
            divider = view.findViewById(R.id.divider);
        }

        @Override
        public int getType()
        {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView title;

        ViewHolder(View view)
        {
            super(view);
            title = view.findViewById(R.id.title);
        }

        public int getType()
        {
            return -1;
        }
    }
}
