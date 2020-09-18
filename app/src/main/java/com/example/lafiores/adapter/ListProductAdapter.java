package com.example.lafiores.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lafiores.R;
import com.example.lafiores.databinding.ActivityMainBinding;
import com.example.lafiores.databinding.ListProductItemBinding;
import com.example.lafiores.model.product.Product;
import com.example.lafiores.view.DetailProductActivity;
import com.example.lafiores.view.MainActivity;
import com.example.lafiores.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class ListProductAdapter extends PagedListAdapter<Product, ListProductAdapter.ListProductViewHolder> {

    private Context context;
    private Application application;
    private static final int TYPE_FOOTER = 0;
    private static final int TYPE_ITEM = 1;
    private ArrayList<Product> productArrayList;

    public ListProductAdapter(Context context) {
        super(Product.CALLBACK);
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListProductItemBinding listProductItemBinding;
        Log.d("ProgressBarVIEWTYPE", viewType + "");

                listProductItemBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.list_product_item,
                        parent,
                        false);
                return new ListProductViewHolder(listProductItemBinding);
 }

//        switch (viewType) {
//            case 0:
//                i = R.layout.progress_bar;
//            case 1:
//                i = R.layout.list_product_item;
//            case 2:
//                i = R.layout.progress_bar;
//            default:
//                i = R.layout.list_product_item;
//                Log.d("ProgressBarI", i + "");
//                listProductItemBinding = DataBindingUtil.inflate(
//                        LayoutInflater.from(parent.getContext()),
//                        i,
//                        parent,
//                        false);
//                return new ListProductViewHolder(listProductItemBinding);
//        Log.d("ProgressBarI", i + "");
//        listProductItemBinding = DataBindingUtil.inflate(
//                LayoutInflater.from(parent.getContext()),
//                i,
//                parent,
//                false);
//        return new ListProductViewHolder(listProductItemBinding);

//        switch (viewType) {
//            case 1:
//                listProductItemBinding = DataBindingUtil.inflate(
//                        LayoutInflater.from(parent.getContext()),
//                        R.layout.list_product_item,
//                        parent,
//                        false);
//                return new ListProductViewHolder(listProductItemBinding);
//            case 2:
//                listProductItemBinding = DataBindingUtil.inflate(
//                        LayoutInflater.from(parent.getContext()),
//                        R.layout.progress_bar,
//                        parent,
//                        false);
//                return new ListProductViewHolder(listProductItemBinding);
//            default:
//                listProductItemBinding = DataBindingUtil.inflate(
//                        LayoutInflater.from(parent.getContext()),
//                        R.layout.list_product_item,
//                        parent,
//                        false);
//                return new ListProductViewHolder(listProductItemBinding);
//        }
//        if (viewType == 2) {
//            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_item, parent, false);
//        } else {
//            ListProductItemBinding listProductItemBinding = DataBindingUtil.inflate(
//                    LayoutInflater.from(parent.getContext()),
//                    R.layout.list_product_item,
//                    parent,
//                    false);
//
//            return new ListProductViewHolder(listProductItemBinding);
//        }


//    public ListProductAdapter(Application application) {
//        this.application = application;
//    }


    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {


        Product product = getItem(position);

        holder.listProductItemBinding.setProduct(product);


//        String imagePath = products.get(position).getImages().get(0).getSrc();
//        String imagePath  = product.getImages().get(0).getSrc();
//        Log.d("TEEST", imagePath);
//        product.setImages(imagePath);

    }

//    @Override
//    public int getItemCount() {
//        return products.size();
//    }


    class ListProductViewHolder extends RecyclerView.ViewHolder {

        private ListProductItemBinding listProductItemBinding;


        public ListProductViewHolder(@NonNull ListProductItemBinding listProductItemBinding) {
            super(listProductItemBinding.getRoot());
            this.listProductItemBinding = listProductItemBinding;

            listProductItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        Product product = getItem(position);
                        Intent intent = new Intent(context, DetailProductActivity.class);
                        intent.putExtra("idProduct", product.getId());
                        intent.putExtra("imageProduct", product.getImages().get(0).getSrc());
                        intent.putExtra("titleProduct", product.getName());
                        intent.putExtra("priceProduct", product.getPrice());
                        intent.putExtra("oldPriceProduct", product.getSalePrice());
                        intent.putExtra("descriptionProduct", product.getDescription());
                        itemView.getContext().startActivity(intent);
                    }

                }
            });
        }
    }

}
