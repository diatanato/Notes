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

import java.util.List;

public class MainCategoriesFragment extends Fragment
{
    public static final int action_add = 101;

    private RecyclerView mCollectionsList;
    private MainCollectionsDialogFragment mDialogFragment;

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
        AppDatabase.getInstance(getContext()).getCategoryDao().getAll().observe(this, collections ->
        {
            adapter.setData(collections);
        });
        setHasOptionsMenu(true);

        mCollectionsList = getView().findViewById(R.id.recyclerview);
        mCollectionsList.setAdapter(adapter);
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
                addCollection();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addCollection()
    {
        if (mDialogFragment == null)
        {
            mDialogFragment = new MainCollectionsDialogFragment();
        }
        if (mDialogFragment.getTag() == null)
        {
            mDialogFragment.setText(getDefaultCollectionName());
            mDialogFragment.show(getFragmentManager(), "collection-dialog");
        }
    }

    private void renameCollection()
    {
        //TODO: mDialogFragment.setText(имя редактируемой коллекции)
    }

    @NonNull
    private String getDefaultCollectionName()
    {
        int index = 0;
        String name = getString(R.string.default_collection_name);
        List<String> categories = AppDatabase.getInstance(getActivity()).getCategoryDao().findNamesLike(name.substring(0, name.length() - 2) + "%");

        for (int i = 1; i <= categories.size() + 1; i++)
        {
            if (!categories.contains(String.format(name, i)))
            {
                index = i;
                break;
            }
        }
        return String.format(name, index);
    }
}
