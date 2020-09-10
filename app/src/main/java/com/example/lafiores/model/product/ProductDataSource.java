package com.example.lafiores.model.product;

import android.app.Application;

import androidx.annotation.NonNull;
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

    public ProductDataSource(ProductApiService productApiService, Application application) {
        this.productApiService = productApiService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Product> callback) {

        productApiService = RetrofitInstance.getService();
        Call<List<Product>> call = productApiService.getAllProductsWithPaging(1,
                application.getApplicationContext().getString(R.string.consumer_key),
                application.getApplicationContext().getString(R.string.consumer_secret));

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();

                if (products != null && products.isEmpty()) {
                    callback.onResult(products, null, 1);
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {
        productApiService = RetrofitInstance.getService();
        Call<List<Product>> call = productApiService.getAllProductsWithPaging(1,
                application.getApplicationContext().getString(R.string.consumer_key),
                application.getApplicationContext().getString(R.string.consumer_secret));

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();

                if (products != null && products.isEmpty()) {
                    callback.onResult(products, params.key + 1);
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

}
