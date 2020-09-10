package com.example.lafiores.view;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lafiores.R;
import com.example.lafiores.adapter.ListProductAdapter;
import com.example.lafiores.databinding.ActivityMainBinding;
import com.example.lafiores.model.product.Product;
import com.example.lafiores.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private PagedList<Product> productsList;
    private RecyclerView recyclerView;
    private ListProductAdapter adapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);
        getListProducts();
    }

    private void getListProducts() {
//        mainActivityViewModel.getAllProductData().observe(this, new Observer<List<Product>>() {
//            @Override
//            public void onChanged(List<Product> products) {
//                productsList = (ArrayList<Product>) products;
//                fillProductsRecyclerView();
//            }
//        });
        mainActivityViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Product>>() {
            @Override
            public void onChanged(PagedList<Product> productsPageList) {

                productsList = productsPageList;
                fillProductsRecyclerView();

            }
        });
    }

    private void fillProductsRecyclerView() {
        recyclerView = activityMainBinding.categoryRecycleView;
        adapter = new ListProductAdapter(this);
        adapter.submitList(productsList);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(
                    new GridLayoutManager(this, 2));

        } else {

            recyclerView.setLayoutManager(
                    new GridLayoutManager(this, 4));

        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

//    private void loadProducts() {
//        ProductApiService productApiService = RetrofitInstance.getService();
//
//        Call<List<Product>> call = productApiService.getAllProducts(
//                "ck_085bf7135759feb1c3b22a57531e537369dc1ecb",
//                "cs_d129ae26ab7c1f29ed5416efbdacc2459a5c60fd",
//                "ru"
//        );
//
//        call.enqueue(new Callback<List<Product>>() {
//
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                if (response.isSuccessful()) {
//
//                    ArrayList<Product> productApiResponse =
//                            (ArrayList<Product>) response.body();
//                    Log.d("TEEST", "загружено " );
//                    fillRecyclerView(productApiResponse);
//                } else {
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Log.d("TEEST", "не загружено");
//            }
//        });
//    }
//
//    private void fillRecyclerView(ArrayList<Product> productApiResponse) {
//        adapter = new ListProductAdapter(getApplicationContext(), productApiResponse);
//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
//        recyclerView.setAdapter(adapter);
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
////        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
////        recyclerView.setAdapter(adapter);
////        adapter.notifyDataSetChanged();
//    }
}
