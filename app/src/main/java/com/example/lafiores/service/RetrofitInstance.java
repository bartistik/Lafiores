package com.example.lafiores.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    public static String BASE_URL = "https://lafiores.com.ua/";

    public static ProductApiService getService() {
        if(retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory
                            .create())
                    .build();
        }
        return retrofit.create(ProductApiService.class);
    }
}
