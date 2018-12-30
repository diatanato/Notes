package com.diatanato.android.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

import com.diatanato.android.notes.data.database.AppDatabase;
import com.diatanato.android.notes.data.database.Category;

public class MainCategoriesDialogFragment extends DialogFragment
{
    public MainCategoriesDialogFragment()
    {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View view = getActivity().getLayoutInflater().inflate(R.layout.main_categories_dialog_fragment, null);
        EditText text = view.findViewById(R.id.category_name);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setMessage("Добавить категорию")
            .setPositiveButton("Создать", (dialog, id) ->
            {
                AppDatabase.getInstance(getActivity()).getCategoryDao().insert(new Category(text.getText().toString(), 0));
            })
            .setNegativeButton("Отмена", (dialog, id) ->
            {
                // User cancelled the dialog
            });

        return builder.create();
    }
}
