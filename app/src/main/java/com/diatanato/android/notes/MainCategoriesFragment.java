package com.diatanato.android.notes;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainCategoriesFragment extends Fragment
{
    private RecyclerView mCategoriesList;

    public MainCategoriesFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.main_categories_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mCategoriesList = (RecyclerView) getView().findViewById(R.id.recyclerview);
        mCategoriesList.setAdapter(new MainCategoriesDataAdapter(CreateDummyData()));
    }

    private MatrixCursor CreateDummyData()
    {
        MatrixCursor cursor = new MatrixCursor(new String[]{"name", "type"});

        cursor.addRow(new Object[]{"Фильтры", 1});
        cursor.addRow(new Object[]{"Избранное (1)", 0});
        cursor.addRow(new Object[]{"Категории", 1});
        cursor.addRow(new Object[]{"Категория 1 (0)", 0});
        cursor.addRow(new Object[]{"Категория 2 (0)", 0});
        cursor.addRow(new Object[]{"Категория 3 (0)", 0});
        cursor.addRow(new Object[]{"Blackdale", 1});
        cursor.addRow(new Object[]{"Blackdale - The Speckled Chasm", 0});
        cursor.addRow(new Object[]{"Blackdale - The Sapphire Pit", 0});
        cursor.addRow(new Object[]{"Blackdale - The Obsidian Trail", 0});
        cursor.addRow(new Object[]{"Blackdale - The Cuspate Post", 0});
        cursor.addRow(new Object[]{"Mumor Mine", 1});
        cursor.addRow(new Object[]{"Mumor Mine - The Great Tunnel", 0});
        cursor.addRow(new Object[]{"Mumor Mine - Undeveloped Zone", 0});
        cursor.addRow(new Object[]{"Mumor Mine - Operations Area No.5", 0});
        cursor.addRow(new Object[]{"Mumor Mine - Mining Zone", 0});
        cursor.addRow(new Object[]{"Mumor Mine - Excavation Area", 0});

        return cursor;
    }
}
