package com.diatanato.android.notes.collections;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.base.SwipeDismissBaseActivity;

public class CollectionsSelectorActivity extends SwipeDismissBaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collections_selector_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
