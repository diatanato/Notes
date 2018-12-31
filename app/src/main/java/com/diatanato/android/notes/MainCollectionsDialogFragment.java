package com.diatanato.android.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.diatanato.android.notes.data.database.AppDatabase;
import com.diatanato.android.notes.data.database.Category;

public class MainCollectionsDialogFragment extends DialogFragment
{
    private String mText;

    public MainCollectionsDialogFragment()
    {
        // Required empty public constructor
    }

    public void setText(String text)
    {
        mText = text;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View view = getActivity().getLayoutInflater().inflate(R.layout.main_categories_dialog_fragment, null);
        EditText text = view.findViewById(R.id.collection_name);

        text.setText(mText);
        text.selectAll();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setMessage("Добавить коллекцию")
            .setPositiveButton("Создать", (dialog, id) ->
            {
                AppDatabase.getInstance(getActivity()).getCategoryDao().insert(new Category(text.getText().toString(), 0));
            })
            .setNegativeButton("Отмена", (dialog, id) ->
            {
                // User cancelled the dialog
            });

        Dialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return dialog;
    }
}
