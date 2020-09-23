package com.example.lafiores.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lafiores.R;
import com.example.lafiores.databinding.ActivityMainBinding;
import com.example.lafiores.databinding.ListProductItemBinding;
import com.example.lafiores.model.product.Product;
import com.example.lafiores.view.DetailProductActivity;

import java.util.ArrayList;

public class ListProductAdapter extends PagedListAdapter<Product, ListProductAdapter.ListProductViewHolder> {

    private Context context;

    public ListProductAdapter(Context context) {
        super(Product.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListProductItemBinding listProductItemBinding;
        Log.d("ProgressBarVIEWTYPE", viewType + "");
        ActivityMainBinding activityMainBinding;

        listProductItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_product_item,
                parent,
                false);
        return new ListProductViewHolder(listProductItemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {


        Product product = getItem(position);

        holder.listProductItemBinding.setProduct(product);
    }

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
                            intent.putParcelableArrayListExtra("imageProduct", (ArrayList<? extends Parcelable>) product.getImages());
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
