package com.example.lafiores.model.product;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.lafiores.R;
import com.example.lafiores.service.ProductApiService;
import com.example.lafiores.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private ArrayList<Product> products = new ArrayList<>();
    private MutableLiveData<List<Product>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public ProductRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Product>> getMutableLiveData() {
        ProductApiService productApiService = RetrofitInstance.getService();

        Call<List<Product>> call = productApiService.getAllProducts(
                application.getApplicationContext().getString(R.string.consumer_key),
                application.getApplicationContext().getString(R.string.consumer_secret),
                application.getApplicationContext().getString(R.string.lang));

        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();
                mutableLiveData.setValue(products);
                Log.d("TEEST", "загружено ");
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("TEEST", "не загружено");
            }
        });
        return mutableLiveData;
    }
}

