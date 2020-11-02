package com.example.lafiores.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lafiores.R;
import com.example.lafiores.databinding.ListProductItemBinding;
import com.example.lafiores.model.cart.Cart;
import com.example.lafiores.model.product.Product;
import com.example.lafiores.view.DetailProductActivity;
import com.google.gson.Gson;

import java.io.Serializable;

public class ListProductAdapter extends PagedListAdapter<Product, ListProductAdapter.ListProductViewHolder> implements Serializable {

    private Context context;

    public ListProductAdapter(Context context) {
        super(Product.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListProductItemBinding listProductItemBinding;

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
            int position = getAdapterPosition();
            listProductItemBinding.buyButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Product product = getItem(position);
                    Cart cart = new Cart(product.getId(), 1);
                    FragmentActivity fragmentActivity = new FragmentActivity();
                    FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

                }
            });

            listProductItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        Product product = getItem(position);
                        Intent intent = new Intent(context, DetailProductActivity.class);
                        Gson gson = new Gson();
                        intent.putExtra("json", gson.toJson(product));
                        itemView.getContext().startActivity(intent);
                    }

                }
            });
        }
    }

}
