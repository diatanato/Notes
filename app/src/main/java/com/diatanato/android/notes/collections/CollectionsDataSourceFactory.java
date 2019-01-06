package com.diatanato.android.notes.collections;

import android.arch.paging.DataSource;
import android.content.Context;

import com.diatanato.android.notes.data.database.Collection;

public class CollectionsDataSourceFactory extends DataSource.Factory<Integer, Collection>
{
    private Context mContext;
    private CollectionsDataSource mCollectionsDataSource;

    public CollectionsDataSourceFactory(Context context)
    {
        mContext = context;
    }
    @Override
    public DataSource<Integer, Collection> create()
    {
        if(mCollectionsDataSource == null)
        {
            mCollectionsDataSource = new CollectionsDataSource(mContext);
        }
        return mCollectionsDataSource;
    }
}
