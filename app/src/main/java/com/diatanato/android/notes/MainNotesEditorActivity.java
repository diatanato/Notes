package com.diatanato.android.notes;

import android.app.AlertDialog;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainNotesEditorActivity extends SwipeDismissBaseActivity
{
    private EditText mTitle;
    private EditText mContent;
    private TextView mCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_notes_editor_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mTitle = findViewById(R.id.title);
        mContent = findViewById(R.id.content);
        mCollection = findViewById(R.id.collection);

        mCollection.setOnClickListener(view -> Snackbar.make(view, "go to collection fragment", Snackbar.LENGTH_LONG).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_notes_editor, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if (!isNoteEmpty() && isNoteChanged())
        {
            new AlertDialog.Builder(this)
                .setMessage("Сохранить изменения ?")
                .setNeutralButton(getString(R.string.action_cancel), null)
                .setNegativeButton(getString(R.string.action_delete), (arg0, arg1) -> MainNotesEditorActivity.super.onBackPressed())
                .setPositiveButton(getString(R.string.action_save), (arg0, arg1) -> MainNotesEditorActivity.super.onBackPressed())
                .create()
                .show();
            
            return;
        }
        super.onBackPressed();
    }

    private boolean isNoteEmpty()
    {
        if (mTitle != null && !TextUtils.isEmpty(mTitle.getText()))
        {
            return false;
        }
        if (mContent != null && !TextUtils.isEmpty(mContent.getText()))
        {
            return false;
        }
        return true;
    }

    private boolean isNoteChanged()
    {
        return !isNoteEmpty(); //TODO
    }
}
