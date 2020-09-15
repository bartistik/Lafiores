package com.example.lafiores.model.product;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.lafiores.R;
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
    private ArrayList<Product> products = new ArrayList<>();
    private MutableLiveData<String> progressLiveStatus;

    public ProductDataSource(ProductApiService productApiService, Application application) {
        this.productApiService = productApiService;
        this.application = application;
        progressLiveStatus = new MutableLiveData<>();
    }

    public MutableLiveData<String> getProgressLiveStatus() {
        return progressLiveStatus;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Product> callback) {


        productApiService = RetrofitInstance.getService();
        Call<List<Product>> call = productApiService.getAllProductsWithPaging(1,
                application.getApplicationContext().getString(R.string.consumer_key),
                application.getApplicationContext().getString(R.string.consumer_secret),
                "ru");

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();

                if (products != null) {
                    progressLiveStatus.postValue("Loaded");
                    callback.onResult(products, null, 2);
                } else {
                    progressLiveStatus.postValue("Loading");
                    Log.d("LoadData: " + getClass().getName(), "неудача");

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("LoadData: " + getClass().getSimpleName(), t.getLocalizedMessage().toString());
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
                "ru");
        progressLiveStatus.postValue("Loading");
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();

                if (products != null) {
                    progressLiveStatus.postValue("Loaded");
                    callback.onResult(products, params.key + 1);
                } else {

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

}
