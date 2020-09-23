package com.example.lafiores.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lafiores.R;
import com.example.lafiores.adapter.ListProductAdapter;
import com.example.lafiores.databinding.ActivityMainBinding;
import com.example.lafiores.model.product.Product;
import com.example.lafiores.service.Constant;
import com.example.lafiores.viewmodel.ListProductActivityViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import in.codeshuffle.typewriterview.TypeWriterView;

public class ListProductActivity extends AppCompatActivity {

    private PagedList<Product> productsList;
    private ListProductAdapter adapter;
    private RecyclerView recyclerView;
    private Button errConnectionRestartButtonClicked;
    private ListProductActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private ListProductClickHandlers listProductActivityClickHandlers;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TypeWriterView typeWriterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        listProductActivityClickHandlers = new ListProductClickHandlers();

        //анимация текста при загрузке товаров
        typeWriterView = activityMainBinding.downloadProductTextView;
        typeWriterView.setDelay(1);
        typeWriterView.setWithMusic(false);
        typeWriterView.animateText(getResources().getString(R.string.downloadProducts));

        activityMainBinding.setButtonHandler(listProductActivityClickHandlers);
        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(ListProductActivityViewModel.class);

        //SwipeReresh
        swipeRefreshLayout = activityMainBinding.swipeRefresh;
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.design_default_color_primary_dark));
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getListProducts();
                    }
                }
        );

        getListProducts();
    }

    public void getListProducts() {
        errConnectionRestartButtonClicked = activityMainBinding.errorConnectionRestartButton;
//      проверяем интернет-соединение
        if (!Constant.checkNetworkConnection(this)) {
            Snackbar.make(findViewById(R.id.loadingIndicator), R.string.error_network_connection, Snackbar.LENGTH_LONG)
                    .show();
            errConnectionRestartButtonClicked = activityMainBinding.errorConnectionRestartButton;
            errConnectionRestartButtonClicked.setVisibility(View.VISIBLE);
        } else {
            errConnectionRestartButtonClicked.setVisibility(View.GONE);
            mainActivityViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Product>>() {
                @Override
                public void onChanged(PagedList<Product> productsPageList) {

                    productsList = productsPageList;
                    fillProductsRecyclerView();
                }
            });
        }

    }

    public void fillProductsRecyclerView() {

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
        swipeRefreshLayout.setRefreshing(false);

        //Проверка загрузки данных. Loaded - скрыть progressBar. Loading - показать progressBar
        mainActivityViewModel.getProgressLoadStatus().observe(this, status -> {
            Log.d("ProgressBarSTATUS1", Objects.requireNonNull(status));
            if (status.equalsIgnoreCase(Constant.STATE_DATA_LOADED)) {
                activityMainBinding.loadingIndicator.setVisibility(View.GONE);
                activityMainBinding.downloadProductTextView.setVisibility(View.GONE);
                activityMainBinding.downloadProductsLoadingIndicator.setVisibility(View.GONE);
            } else if (status.equalsIgnoreCase(Constant.STATE_DATA_LOADING)) {
                activityMainBinding.loadingIndicator.setVisibility(View.VISIBLE);
            } else if (status.equalsIgnoreCase(Constant.STATE_DATA_ERROR)) {
                errConnectionRestartButtonClicked.setVisibility(View.VISIBLE);
            }
        });
    }

    public class ListProductClickHandlers extends ListProductActivity {
        public void errConnectionRestartButtonClicked(View view) {
            errConnectionRestartButtonClicked.setVisibility(View.GONE);
            getListProducts();
        }
    }
}

