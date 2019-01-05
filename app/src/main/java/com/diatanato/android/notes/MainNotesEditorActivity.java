package com.diatanato.android.notes;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainNotesEditorActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_notes_editor_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView collection = findViewById(R.id.collection);
        collection.setOnClickListener(view -> Snackbar.make(view, "go to collection fragment", Snackbar.LENGTH_LONG).show());
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        return true;
    }
}
