package com.example.lafiores.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.example.lafiores.interfaces.RecyclerViewClickListener;
import com.example.lafiores.model.product.Product;
import com.example.lafiores.service.AsyncTasks;
import com.example.lafiores.service.Constant;
import com.example.lafiores.viewmodel.CartViewModel;
import com.example.lafiores.viewmodel.ListProductActivityViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import in.codeshuffle.typewriterview.TypeWriterView;

public class ListProductActivity extends AppCompatActivity
        implements RecyclerViewClickListener, NavigationView.OnNavigationItemSelectedListener {

    private PagedList<Product> productsList;
    private Button errConnectionRestartButtonClicked;
    private ListProductActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TypeWriterView typeWriterView;
    private CartViewModel cartViewModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ListProductClickHandlers listProductActivityClickHandlers = new ListProductClickHandlers();
        typeWriterView = activityMainBinding.downloadProductTextView;
        activityMainBinding.setButtonHandler(listProductActivityClickHandlers);
        errConnectionRestartButtonClicked = activityMainBinding.errorConnectionRestartButton;
        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(ListProductActivityViewModel.class);

        //SwipeRefresh
        swipeRefreshLayout = activityMainBinding.swipeRefresh;
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.design_default_color_primary_dark));
        swipeRefreshLayout.setOnRefreshListener(
                () -> {
                    try {
                        Thread.sleep(2000);
                        swipeRefreshLayout.setRefreshing(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    checkInternetState();
                }
        );
        checkInternetState();
    }

    //Проверка инета
    private void checkInternetState() {
        new AsyncTasks.InternetCheck(isInternetEnabled -> {
            if (isInternetEnabled) {
                getListProducts();
                errConnectionRestartButtonClicked.setVisibility(View.GONE);
            } else {
                Snackbar.make(findViewById(R.id.loadingIndicator), R.string.error_network_connection, Snackbar.LENGTH_LONG)
                        .show();
                errConnectionRestartButtonClicked.setVisibility(View.VISIBLE);
                typeWriterView.setDelay(1);
                typeWriterView.setWithMusic(false);
                typeWriterView.animateText(getResources().getString(R.string.notInternet));
            }
        });
    }

    //Получаем список товаров и заполняем ресайклер
    private void getListProducts() {
        launchTypeWriter();
        mainActivityViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Product>>() {
            @Override
            public void onChanged(PagedList<Product> productsPageList) {
                productsList = productsPageList;
                fillProductsRecyclerView();
            }
        });
    }

    //Анимация при загрузке товаров
    private void launchTypeWriter() {
        typeWriterView.setDelay(1);
        typeWriterView.setWithMusic(false);
        typeWriterView.animateText(getResources().getString(R.string.downloadProducts));
    }

    //Наполняем ресайклер
    public void fillProductsRecyclerView() {
        RecyclerView recyclerView = activityMainBinding.categoryRecycleView;
        ListProductAdapter adapter = new ListProductAdapter(this, this);
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

    @Override
    public void recyclerViewClickListener(int position) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.insertData(getApplicationContext(), position, 1);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public class ListProductClickHandlers extends ListProductActivity {
        public void errConnectionRestartButtonClicked(View view) {
        }
    }

}