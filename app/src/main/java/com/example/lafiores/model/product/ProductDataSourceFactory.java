package com.example.lafiores.model.product;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.lafiores.service.ProductApiService;

public class ProductDataSourceFactory extends DataSource.Factory {

    private ProductDataSource productDataSource;
    private Application application;
    private ProductApiService productApiService;
    private MutableLiveData<ProductDataSource> mutableLiveData;

    public ProductDataSourceFactory(Application application, ProductApiService productApiService) {
        this.application = application;
        this.productApiService = productApiService;
        this.mutableLiveData = new MutableLiveData<>();
    }

    public ProductDataSourceFactory() {
    }

    @NonNull
    @Override
    public DataSource create() {
        productDataSource = new ProductDataSource(productApiService, application);
        mutableLiveData.postValue(productDataSource);
        return productDataSource;
    }

    public MutableLiveData<ProductDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
