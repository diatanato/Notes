package com.diatanato.android.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainCategoriesDataAdapter extends RecyclerView.Adapter<MainCategoriesDataAdapter.ViewHolder>
{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type)
    {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_categories_item, parent, false));
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
