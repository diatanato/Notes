package com.diatanato.android.notes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;

import com.diatanato.android.notes.data.database.AppDatabase;
import com.diatanato.android.notes.data.database.Collection;

public class MainCollectionsViewModel extends ViewModel
{
    public LiveData<PagedList<Collection>> collections;

    public MainCollectionsViewModel(Context context)
    {
        //MainCollectionsDataSourceFactory factory = new MainCollectionsDataSourceFactory(context);

        PagedList.Config config = new PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(15)
            .setPageSize(15)
            .build();

        //collections = new LivePagedListBuilder<>(factory, config).build();
        collections = new LivePagedListBuilder<>(AppDatabase.getInstance(context).getCategoryDao().getDataSource(), config).build();
    }

    public static class MainCollectionsViewModelFactory extends ViewModelProvider.NewInstanceFactory
    {
        private Context mContext;

        public MainCollectionsViewModelFactory(Context context)
        {
            mContext = context;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> viewModel)
        {
            return (T) new MainCollectionsViewModel(mContext);
        }
    }
}
