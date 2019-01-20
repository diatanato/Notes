package com.diatanato.android.notes.collections;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.database.entities.Collection;

import java.util.Objects;

public class CollectionsDataAdapter extends PagedListAdapter<Collection, CollectionsViewHolder>
{
    private final int TYPE_ITEM  = 0;
    private final int TYPE_GROUP = 1;

    private OnCollectionBind mListener;

    private static DiffUtil.ItemCallback<Collection> DIFF_CALLBACK = new DiffUtil.ItemCallback<Collection>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull Collection a, @NonNull Collection b)
        {
            return a.id == b.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Collection a, @NonNull Collection b)
        {
            return
                a.type == b.type &&
                Objects.equals(a.name, b.name);
        }
    };

    CollectionsDataAdapter(OnCollectionBind listener)
    {
        super(DIFF_CALLBACK);

        mListener = listener;
    }

    @NonNull
    @Override
    public CollectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type)
    {
        switch (type)
        {
            case TYPE_ITEM: return new CollectionsItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.collections_item, parent, false));
            case TYPE_GROUP: return new CollectionsGroupViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.collections_group, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsViewHolder holder, int position)
    {
        holder.bind(getItem(position));

        if (holder.getType() == 0)
        {
            //int next = position + 1;
            //(ItemViewHolder)holder).divider.setVisibility(mCategories.size() > next && mCategories.get(next).type == 0 ? View.VISIBLE : View.GONE);

            mListener.onCollectionBind((CollectionsItemViewHolder)holder);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        return getItem(position).type;
    }
}
