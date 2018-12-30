package com.diatanato.android.notes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.diatanato.android.notes.data.database.AppDatabase;

public class MainCategoriesFragment extends Fragment
{
    public static final int action_add = 101;

    private RecyclerView mCategoriesList;
    private MainCategoriesDialogFragment mDialogFragment;

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
        setHasOptionsMenu(true);

        mCategoriesList = getView().findViewById(R.id.recyclerview);
        mCategoriesList.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.add(Menu.NONE, action_add, Menu.NONE, R.string.action_add);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case action_add:
                add();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void add()
    {
        if (mDialogFragment == null)
        {
            mDialogFragment = new MainCategoriesDialogFragment();
        }
        if (mDialogFragment.getTag() == null)
        {
            mDialogFragment.show(getFragmentManager(), "add_category_dialog");
        }
    }
}
