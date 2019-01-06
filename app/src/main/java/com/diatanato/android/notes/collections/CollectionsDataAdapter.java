package com.diatanato.android.notes.collections;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.data.database.Collection;

public class CollectionsDataAdapter extends PagedListAdapter<Collection, CollectionsDataAdapter.ViewHolder>
{
    private static final String TAG = "myLog";

    private final int TYPE_ITEM  = 0;
    private final int TYPE_GROUP = 1;

    private static DiffUtil.ItemCallback<Collection> DIFF_CALLBACK = new DiffUtil.ItemCallback<Collection>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull Collection oldCategory, @NonNull Collection newCategory)
        {
            return oldCategory.id == newCategory.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Collection oldCategory, @NonNull Collection newCategory)
        {
            return
                oldCategory.type == newCategory.type &&
                oldCategory.name == newCategory.name;
        }
    };

    protected CollectionsDataAdapter()
    {
        super(DIFF_CALLBACK);
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
        Log.d(TAG, "bind, position = " + position);

        //TODO: holder.bind(getItem(position));

        holder.title.setText(getItem(position).name);

        //if (holder.getType() == 0)
        //{
        //    int next = position + 1;
        //    ((ItemViewHolder)holder).divider.setVisibility(mCategories.size() > next && mCategories.get(next).type == 0 ? View.VISIBLE : View.GONE);
        //}
    }

    @Override
    public int getItemViewType(int position)
    {
        return getItem(position).type;
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
