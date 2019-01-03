package com.diatanato.android.notes;

import android.arch.paging.ItemKeyedDataSource;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.diatanato.android.notes.data.database.AppDatabase;
import com.diatanato.android.notes.data.database.Collection;
import com.diatanato.android.notes.data.database.CollectionDao;

public class MainCollectionsDataSource extends ItemKeyedDataSource<Integer, Collection>
{
    private static final String TAG = "myLog";

    private CollectionDao mCollections;

    public MainCollectionsDataSource(Context context)
    {
        mCollections = AppDatabase.getInstance(context).getCategoryDao();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Collection> callback)
    {
        Log.d(TAG, "loadInitial, key = " + params.requestedInitialKey + ", loadSize = " + params.requestedLoadSize);

        callback.onResult(mCollections.getPageById(0, params.requestedLoadSize));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Collection> callback)
    {
        Log.d(TAG, "loadAfter, key = " + params.key + ", loadSize = " + params.requestedLoadSize);

        callback.onResult(mCollections.getPageById(params.key, params.requestedLoadSize));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Collection> callback)
    {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Collection item)
    {
        return item.id;
    }
}
