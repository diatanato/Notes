package com.diatanato.android.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diatanato.android.notes.data.database.Category;

import java.util.ArrayList;
import java.util.List;

public class MainCategoriesDataAdapter extends RecyclerView.Adapter<MainCategoriesDataAdapter.ViewHolder>
{
    private final int TYPE_ITEM  = 0;
    private final int TYPE_GROUP = 1;

    private List<Category> mCategories;

    public MainCategoriesDataAdapter()
    {
        mCategories = new ArrayList<>();
    }

    public void setData(List<Category> categories)
    {
        mCategories = categories;
        notifyDataSetChanged();
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
        holder.title.setText(mCategories.get(position).name);

        if (holder.getType() == 0)
        {
            int next = position + 1;
            ((ItemViewHolder)holder).divider.setVisibility(mCategories.size() > next && mCategories.get(next).type == 0 ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public int getItemCount()
    {
        return mCategories.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return mCategories.get(position).type;
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
