package com.diatanato.android.notes.notes;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diatanato.android.notes.R;

public class NotesFragment extends Fragment
{
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.main_notes_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        NotesViewModel model =
            ViewModelProviders
                .of(this, new NotesViewModel.Factory(getContext()))
                .get(NotesViewModel.class);

        NotesDataAdapter adapter = new NotesDataAdapter();
        model.notes.observe(this, adapter::submitList);

        mRecyclerView = getView().findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter(adapter);

        adapter.getItemClick().subscribe(id -> editNote(id));

        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(view -> createNote());
    }

    public void createNote()
    {
        Intent intent = new Intent(getActivity(), NotesEditorActivity.class);
        startActivity(intent);
    }

    public void editNote(int id)
    {
        Intent intent = new Intent(getActivity(), NotesEditorActivity.class);
        intent.putExtra(NotesEditorActivity.EXTRA_NOTE_ID, id);
        startActivity(intent);
    }
}
