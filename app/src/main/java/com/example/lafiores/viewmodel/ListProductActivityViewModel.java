package com.example.lafiores.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
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

public class ListProductActivityViewModel extends AndroidViewModel {

    private final ProductRepository productRepository;
    private final LiveData<ProductDataSource> productDataSourceLiveData;
    private final Executor executor;
    private final LiveData<PagedList<Product>> pagedListLiveData;
    private LiveData<String> progressLoadStatus = new MutableLiveData<>();

    public ListProductActivityViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        ProductApiService productApiService = RetrofitInstance.getService();
        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory(application, productApiService);
        productDataSourceLiveData = productDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setEnablePlaceholders(true)
                .setPageSize(10)
                .setPrefetchDistance(5)
                .build();

        executor = Executors.newCachedThreadPool();
        pagedListLiveData = new LivePagedListBuilder<Integer, Product>(productDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build();
        progressLoadStatus = Transformations.switchMap(productDataSourceFactory.getMutableLiveData(),
                ProductDataSource::getProgressLiveStatus);
    }

    public LiveData<String> getProgressLoadStatus() {
        return progressLoadStatus;
    }

    public MutableLiveData<List<Product>> getAllProductData() {
        return productRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Product>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
