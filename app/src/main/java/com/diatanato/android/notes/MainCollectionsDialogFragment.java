package com.diatanato.android.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import com.diatanato.android.notes.data.database.AppDatabase;
import com.diatanato.android.notes.data.database.Collection;

public class MainCollectionsDialogFragment extends AppCompatDialogFragment
{
    private static final int ERROR_NONE  = 0;
    private static final int ERROR_EXIST = 1;
    private static final int ERROR_EMPTY = 2;

    private String mText;
    private AlertDialog mDialog;
    private TextInputLayout mInputError;
    private TextInputEditText mInputText;
    private int mError;

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
        mInputText  = view.findViewById(R.id.input_text);
        mInputError = view.findViewById(R.id.input_error);

        mInputText.setText(mText);
        mInputText.selectAll();
        mInputText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.length() == 0)
                {
                    setError(ERROR_EMPTY);
                    return;
                }
                if (AppDatabase.getInstance(getActivity()).getCategoryDao().isCollectionNameExist(s.toString()))
                {
                    setError(ERROR_EXIST);
                    return;
                }
                setError(ERROR_NONE);
            }
        });
        mDialog = new AlertDialog.Builder(getActivity()).create();
        mDialog.setView(view);
        mDialog.setMessage("Добавить коллекцию");
        mDialog.setButton(Dialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (DialogInterface.OnClickListener) null);
        mDialog.setButton(Dialog.BUTTON_POSITIVE, getString(R.string.action_create), (dialog, id) ->
        {
            AppDatabase.getInstance(getActivity()).getCategoryDao().insert(new Collection(mInputText.getText().toString(), 0));
        });
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return mDialog;
    }

    private void setError(int error)
    {
        if (mError != error)
        {
            switch (error)
            {
                case ERROR_NONE:
                    mInputError.setErrorEnabled(false);
                    mDialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled(true);
                    break;
                case ERROR_EXIST:
                    mInputError.setError("Имя коллекции уже используется");
                    mDialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled(false);
                    break;
                case ERROR_EMPTY:
                    mInputError.setErrorEnabled(false);
                    mDialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled(false);
                    break;
            }
            mError = error;
        }
    }
}
