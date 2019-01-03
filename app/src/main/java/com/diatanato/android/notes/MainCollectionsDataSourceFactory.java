package com.diatanato.android.notes;

import android.arch.paging.DataSource;
import android.content.Context;

import com.diatanato.android.notes.data.database.Collection;

public class MainCollectionsDataSourceFactory extends DataSource.Factory<Integer, Collection>
{
    private Context mContext;
    private MainCollectionsDataSource mCollectionsDataSource;

    public MainCollectionsDataSourceFactory(Context context)
    {
        mContext = context;
    }
    @Override
    public DataSource<Integer, Collection> create()
    {
        if(mCollectionsDataSource == null)
        {
            mCollectionsDataSource = new MainCollectionsDataSource(mContext);
        }
        return mCollectionsDataSource;
    }
}
