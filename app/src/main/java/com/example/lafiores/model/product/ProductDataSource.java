package com.example.lafiores.model.product;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.lafiores.R;
import com.example.lafiores.db.ProductDao;
import com.example.lafiores.db.ProductDatabase;
import com.example.lafiores.service.Constant;
import com.example.lafiores.service.ProductApiService;
import com.example.lafiores.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDataSource extends PageKeyedDataSource<Integer, Product> {

    private ProductApiService productApiService;
    private Application application;
    private ArrayList<Product> products = new ArrayList<Product>();
    private final MutableLiveData<String> progressLiveStatus;
    private ProductDatabase productDatabase;
    private ProductDataSourceFactory dataSourceFactory;
    private ProductDao productDao;
    private ProductRepository productRepository;


    public ProductDataSource(ProductApiService productApiService, Application application) {
        this.productApiService = productApiService;
        this.application = application;
        progressLiveStatus = new MutableLiveData<>();
        ProductDatabase database = ProductDatabase.getInstance(application);
        productDao = database.getProductDao();
    }

    public ProductDataSource(Context context) {
        progressLiveStatus = new MutableLiveData<>();

        dataSourceFactory = new ProductDataSourceFactory();
        productDatabase = ProductDatabase.getInstance(context.getApplicationContext());
    }

    public MutableLiveData<String> getProgressLiveStatus() {
        return progressLiveStatus;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Product> callback) {
        progressLiveStatus.postValue(Constant.STATE_DATA_START_LOADING);
        productApiService = RetrofitInstance.getService();
        Call<List<Product>> call = productApiService.getAllProductsWithPaging(1,
                application.getApplicationContext().getString(R.string.consumer_key),
                application.getApplicationContext().getString(R.string.consumer_secret),
                application.getApplicationContext().getString(R.string.lang));

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();

                if (products != null) {
                    callback.onResult(products, null, 2);
                    progressLiveStatus.postValue(Constant.STATE_DATA_LOADED);
                } else {
                    progressLiveStatus.postValue(Constant.STATE_DATA_ERROR);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressLiveStatus.postValue(Constant.STATE_DATA_ERROR);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {
        callback.onResult(products, params.key - 1);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {
        productApiService = RetrofitInstance.getService();
        Call<List<Product>> call = productApiService.getAllProductsWithPaging(params.key,
                application.getApplicationContext().getString(R.string.consumer_key),
                application.getApplicationContext().getString(R.string.consumer_secret),
                application.getApplicationContext().getString(R.string.lang));
        progressLiveStatus.postValue(Constant.STATE_DATA_LOADING);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();

                if (products != null) {
                    progressLiveStatus.postValue(Constant.STATE_DATA_LOADED);
                    callback.onResult(products, params.key + 1);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressLiveStatus.postValue(Constant.STATE_DATA_ERROR);
            }
        });
    }
}

