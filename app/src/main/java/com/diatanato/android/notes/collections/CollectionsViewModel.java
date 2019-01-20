package com.diatanato.android.notes.collections;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.diatanato.android.notes.database.AppDatabase;
import com.diatanato.android.notes.database.entities.Collection;

public class CollectionsViewModel extends AndroidViewModel
{
    public LiveData<PagedList<Collection>> collections;

    public CollectionsViewModel(@NonNull Application application)
    {
        super(application);

        //CollectionsDataSourceFactory factory = new CollectionsDataSourceFactory(context);

        PagedList.Config config = new PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(15)
            .setPageSize(15)
            .build();

        //collections = new LivePagedListBuilder<>(factory, config).build();
        collections = new LivePagedListBuilder<>(AppDatabase.getInstance(application).getCategoryDao().getDataSource(), config).build();
    }
}
