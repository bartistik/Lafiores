package com.example.lafiores.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private PagedList<Product> productsList;
    private RecyclerView recyclerView;
    private ListProductAdapter adapter;
    private ProgressBar progressBar;
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

        //Проверка загрузки данных. Loaded - скрыть progressBar. Loading - показать progressBar
        mainActivityViewModel.getProgressLoadStatus().observe(this, status -> {
            if(Objects.requireNonNull(status).equalsIgnoreCase("Loaded")) {
                activityMainBinding.loadingIndicator.setVisibility(View.GONE);
                adapter.onCreateViewHolder(recyclerView,1);
                Log.d("ProgressBar", Objects.requireNonNull(status).toString());
            } else if (status.equalsIgnoreCase("Loading")) {
                Log.d("ProgressBar", Objects.requireNonNull(status).toString());
                activityMainBinding.loadingIndicator.setVisibility(View.VISIBLE);
                adapter.onCreateViewHolder(recyclerView,2);
            }
        });
    }
}
