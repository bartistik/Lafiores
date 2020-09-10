package com.example.lafiores.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.lafiores.model.product.Product;
import com.example.lafiores.model.product.ProductDataSource;
import com.example.lafiores.model.product.ProductDataSourceFactory;
import com.example.lafiores.model.product.ProductRepository;
import com.example.lafiores.service.ProductApiService;
import com.example.lafiores.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    ProductRepository productRepository;
    private LiveData<ProductDataSource> productDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Product>> pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        ProductApiService productApiService = RetrofitInstance.getService();
        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory(application, productApiService);
        productDataSourceLiveData = productDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(3)
                .build();

        executor = Executors.newCachedThreadPool();
        pagedListLiveData = new LivePagedListBuilder<Integer, Product>(productDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build();
    }

    public MutableLiveData<List<Product>> getAllProductData() {

        return productRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Product>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
