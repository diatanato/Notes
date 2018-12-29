package com.diatanato.android.notes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diatanato.android.notes.data.database.AppDatabase;

public class MainCategoriesFragment extends Fragment
{
    private RecyclerView mCategoriesList;

    public MainCategoriesFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.main_categories_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        MainCategoriesDataAdapter adapter = new MainCategoriesDataAdapter();
        AppDatabase.getInstance(getContext()).getCategoryDao().findAll().observe(this, categories ->
        {
            adapter.setData(categories);
        });
        mCategoriesList = getView().findViewById(R.id.recyclerview);
        mCategoriesList.setAdapter(adapter);
    }
}
