package com.diatanato.android.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainCategoriesDataAdapter extends RecyclerView.Adapter<MainCategoriesDataAdapter.ViewHolder>
{
    private final int TYPE_GROUP = 0;
    private final int TYPE_ITEM  = 1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type)
    {
        switch (type)
        {
            case TYPE_ITEM:  return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_categories_item, parent, false));
            case TYPE_GROUP: return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_categories_group, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.title.setText("Наименование категории");
    }

    @Override
    public int getItemCount()
    {
        return 50;
    }

    @Override
    public int getItemViewType(int position)
    {
        //TODO: забираем тип из коллекции без лишних проверок
        switch (position)
        {
            case 0:
            case 4:
                return TYPE_GROUP;
        }
        return TYPE_ITEM;
    }

    class GroupViewHolder extends  ViewHolder
    {

        GroupViewHolder(View view)
        {
            super(view);
        }
    }

    class ItemViewHolder extends  ViewHolder
    {

        ItemViewHolder(View view)
        {
            super(view);
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
    }
}
