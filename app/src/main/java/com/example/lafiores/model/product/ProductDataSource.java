package com.example.lafiores.model.product;

import android.app.Application;
import android.util.Log;

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

    int page = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Product> callback) {


        productApiService = RetrofitInstance.getService();
        Call<List<Product>> call = productApiService.getAllProductsWithPaging(1,
                application.getApplicationContext().getString(R.string.consumer_key),
                application.getApplicationContext().getString(R.string.consumer_secret),
                "ru");
        // Log.d(LoadData:)

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();

                if (products != null) {
                    callback.onResult(products, 1, 2);
                } else {
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

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Product> callback) {
        productApiService = RetrofitInstance.getService();
        Call<List<Product>> call = productApiService.getAllProductsWithPaging(params.key,
                application.getApplicationContext().getString(R.string.consumer_key),
                application.getApplicationContext().getString(R.string.consumer_secret),
                "ru");

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();

                if (products != null) {
                    callback.onResult(products, params.key + 1);
                    Log.d("LoadData:", "" + params.key);
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

}
