package com.example.lafiores.service;

import android.util.Log;

import com.example.lafiores.model.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApiService {

    //Грузим все товары
    @GET("wp-json/wc/v3/products")
    Call<List<Product>> getAllProducts(
            @Query("consumer_key") String consumerKey,
            @Query("consumer_secret") String consumerSecret,
            @Query("lang") String lang);

    //Грузим карточку товара
    @GET("wp-json/wc/v3/products/{id}")
    Call<Product> getDetailProduct(
            @Path("id") int idProduct,
            @Query("consumer_key") String consumerKey,
            @Query("consumer_secret") String consumerSecret);

    @GET("wp-json/wc/v3/products")
    Call<List<Product>> getAllProductsWithPaging(
            @Query("page") int page,
            @Query("consumer_key") String consumerKey,
            @Query("consumer_secret") String consumerSecret,
            @Query("lang") String lang);
}

