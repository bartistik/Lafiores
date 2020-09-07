package com.example.lafiores.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.lafiores.model.product.Product;
import com.example.lafiores.model.product.ProductRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    ProductRepository productRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
    }

    public MutableLiveData<List<Product>> getAllProductData() {

        return productRepository.getMutableLiveData();
    }
}
