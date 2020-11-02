package com.example.lafiores.model.product;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lafiores.R;
import com.example.lafiores.db.ProductDao;
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
    private ProductDao productDao;
    private LiveData<List<Product>> productList;


    public ProductRepository(Application application) {
        this.application = application;
        //Room
//        ProductDatabase database = ProductDatabase.getInstance(application);
//        productDao = database.getProductDao();
    }

    public LiveData<List<Product>> getProductList() {

        return productDao.loadProducts();
    }

//    public void insertProduct(Product products) {
//        new InsertProductAsyncTask(productDao).execute();
//
//        Log.d("ROOM",  productDao + "");
//    }
//
//    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void> {
//
//        private ProductDao productAsyncDao;
//
//        public InsertProductAsyncTask(ProductDao productDao) {
//            this.productAsyncDao = productDao;
//        }
//
//        @Override
//        protected Void doInBackground(Product...products) {
//
//            productAsyncDao.saveProducts(products[0]);
//            return null;
//        }
//    }


    //Retrofit
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
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }
}

