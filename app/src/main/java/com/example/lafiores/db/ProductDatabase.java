package com.example.lafiores.db;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lafiores.model.product.Product;
import com.example.lafiores.model.product.ProductDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    private static ProductDatabase instance;

    public abstract ProductDao getProductDao();

    private LiveData<PagedList<Product>> productsListPaged;

    public static ProductDatabase getInstance(Context context) {

            if (instance == null) {
                synchronized (ProductDatabase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        ProductDatabase.class, "productDB")
                        .fallbackToDestructiveMigration()
                        .build();
                instance.init();
                Log.d("ROOM", "Create DB");
            }
        }
        return instance;
    }

    private void init() {
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Integer.MAX_VALUE).setPageSize(Integer.MAX_VALUE).build();
        Executor executor = Executors.newFixedThreadPool(3);
        ProductDataSourceFactory dataSourceFactory = new ProductDataSourceFactory();
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory, pagedListConfig);
        productsListPaged = livePagedListBuilder.setFetchExecutor(executor).build();
    }
}
