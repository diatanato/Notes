package com.diatanato.android.notes.collections;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.database.AppDatabase;

import java.util.List;

public class CollectionsFragment extends Fragment implements OnCollectionBind
{
    public static final int action_add = 101;

    public static final String EXTRA_COLLECTION_ID   = "selected_id";
    public static final String EXTRA_COLLECTION_NAME = "selected_name";

    private RecyclerView mRecyclerView;
    private CollectionsDialogFragment mDialogFragment;

    private int mCollectionId;

    public static CollectionsFragment newInstance(int id)
    {
        CollectionsFragment fragment = new CollectionsFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_COLLECTION_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.collections_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null)
        {
            mCollectionId = getArguments().getInt(EXTRA_COLLECTION_ID, 0);
        }
        setHasOptionsMenu(true);

        CollectionsViewModel model = ViewModelProviders.of(this).get(CollectionsViewModel.class);

        CollectionsDataAdapter adapter = new CollectionsDataAdapter(this);
        model.collections.observe(this, collections ->
        {
            adapter.submitList(collections);
        });
        mRecyclerView = getView().findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter(adapter);
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

    @Override
    public void onCollectionBind(@NonNull CollectionsItemViewHolder holder)
    {
        int color =
            mCollectionId == holder.getCollectionId()
                ? R.color.collection_text_selected
                : R.color.collection_text;

        holder.setColor(ContextCompat.getColor(getContext(), color));
    }

    private void addCollection()
    {
        if (mDialogFragment == null)
        {
            mDialogFragment = new CollectionsDialogFragment();
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
        List<String> categories = AppDatabase.getInstance(getContext()).getCategoryDao().findNamesLike(name.substring(0, name.length() - 2) + "%");

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
