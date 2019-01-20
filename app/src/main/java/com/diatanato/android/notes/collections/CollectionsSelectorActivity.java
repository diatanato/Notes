package com.diatanato.android.notes.collections;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.base.SwipeDismissBaseActivity;

public class CollectionsSelectorActivity extends SwipeDismissBaseActivity
{
    public static final String EXTRA_COLLECTION_ID = "collection_id";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collections_selector_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.fragment, CollectionsFragment.newInstance(getIntent().getIntExtra(EXTRA_COLLECTION_ID, 0)))
            .commit();
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
