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
}
