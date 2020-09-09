package com.example.lafiores.model.product;

import android.app.Application;

import com.example.lafiores.service.ProductApiService;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

public class ProductDataSource extends PageKeyedDataSource<Integer, Product> {

    private ProductApiService productApiService;
    private Application application;
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Product> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {

    }
}
