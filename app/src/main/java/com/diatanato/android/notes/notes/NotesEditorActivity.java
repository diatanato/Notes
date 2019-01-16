package com.diatanato.android.notes.notes;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.diatanato.android.notes.R;
import com.diatanato.android.notes.base.SwipeDismissBaseActivity;
import com.diatanato.android.notes.collections.CollectionsSelectorActivity;
import com.diatanato.android.notes.data.database.AppDatabase;

public class NotesEditorActivity extends SwipeDismissBaseActivity
{
    public final static String EXTRA_NOTE_ID = "id";

    private EditText mTitle;
    private EditText mContent;
    private TextView mCollection;
    private TextView mModificationDate;

    private NotesEditorViewModel mModel;

    /************************************************************************
    *                                                                       *
    *                                                                       *
    *                                                                       *
    ************************************************************************/

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
        mModificationDate = findViewById(R.id.modification_date);

        //TODO: открываем список коллекций для выбора существующей или создания новой
        mCollection.setOnClickListener(view ->
        {
            Intent intent = new Intent(this, CollectionsSelectorActivity.class);
            startActivity(intent);
        });
        mModel =
            ViewModelProviders
                .of(this, new NotesEditorViewModel.Factory(this, getIntent().getIntExtra(EXTRA_NOTE_ID, 0)))
                .get(NotesEditorViewModel.class);

        mTitle.setText(mModel.note.title);
        mContent.setText(mModel.note.content);

        if (mModel.note.dateModification > 0)
        {
            mModificationDate.setText(getString(R.string.notes_editor_last_modification, getDate(mModel.note.dateModification)));
        }
    }

    /************************************************************************
    *                                                                       *
    *                                                                       *
    *                                                                       *
    ************************************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_notes_editor, menu);
        return true;
    }

    /************************************************************************
    *                                                                       *
    *                                                                       *
    *                                                                       *
    ************************************************************************/

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    /************************************************************************
    *                                                                       *
    *                                                                       *
    *                                                                       *
    ************************************************************************/

    @Override
    public void onBackPressed()
    {
        if (isChanged())
        {
            new AlertDialog.Builder(this)
                .setMessage("Сохранить изменения ?")
                .setNeutralButton(getString(R.string.action_cancel), null)
                .setNegativeButton(getString(R.string.action_delete), (arg0, arg1) -> super.onBackPressed())
                .setPositiveButton(getString(R.string.action_save), (arg0, arg1) -> save())
                .create()
                .show();
            
            return;
        }
        super.onBackPressed();
    }

    /************************************************************************
    *                                                                       *
    *                                                                       *
    *                                                                       *
    ************************************************************************/

    private void  save()
    {
        mModel.note.title = mTitle.getText().toString();
        mModel.note.content = mContent.getText().toString();
        mModel.note.dateModification = System.currentTimeMillis();

        if (mModel.note.id > 0)
        {
            if (isEmpty())
            {
                AppDatabase.getInstance(this).getNoteDao().delete(mModel.note);
            }
            else
            {
                AppDatabase.getInstance(this).getNoteDao().update(mModel.note);
            }
        }
        else
        {
            mModel.note.dateCreation = mModel.note.dateModification;
            AppDatabase.getInstance(this).getNoteDao().insert(mModel.note);
        }
        super.onBackPressed();
    }

    /************************************************************************
    *                                                                       *
    *                                                                       *
    *                                                                       *
    ************************************************************************/

    private boolean isEmpty()
    {
        return TextUtils.isEmpty(mTitle.getText()) && TextUtils.isEmpty(mContent.getText());
    }

    /************************************************************************
    *                                                                       *
    *                                                                       *
    *                                                                       *
    ************************************************************************/

    private boolean isChanged()
    {
        return !(TextUtils.equals(mTitle.getText(), mModel.note.title) && TextUtils.equals(mContent.getText(), mModel.note.content));
    }

    /************************************************************************
    *                                                                       *
    *                                                                       *
    *                                                                       *
    ************************************************************************/

    private String getDate(long date)
    {
        if (DateUtils.isToday(date))
        {
            return DateFormat.getTimeFormat(this).format(date);
        }
        return DateFormat.getMediumDateFormat(this).format(date);
    }
}
