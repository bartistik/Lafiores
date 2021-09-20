package com.example.lafiores.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lafiores.R;
import com.example.lafiores.databinding.ListProductItemBinding;
import com.example.lafiores.interfaces.RecyclerViewClickListener;
import com.example.lafiores.model.product.Product;
import com.example.lafiores.view.DetailProductActivity;
import com.google.gson.Gson;
import java.io.Serializable;

public class ListProductAdapter extends PagedListAdapter<Product, ListProductAdapter.ListProductViewHolder> implements Serializable {

    private final Context context;
    private static RecyclerViewClickListener itemListener;

    public ListProductAdapter(
            Context context, RecyclerViewClickListener recyclerViewClickListener
    ) {
        super(Product.CALLBACK);
        this.context = context;
        itemListener = recyclerViewClickListener;
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

        private final ListProductItemBinding listProductItemBinding;

        public ListProductViewHolder(@NonNull ListProductItemBinding listProductItemBinding) {
            super(listProductItemBinding.getRoot());
            this.listProductItemBinding = listProductItemBinding;
            listProductItemBinding.buyButton.setOnClickListener(v -> {
                Product product = getItem(getAdapterPosition());
                itemListener.recyclerViewClickListener(product.getId());
            });

            listProductItemBinding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Product product = getItem(position);
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    Gson gson = new Gson();
                    intent.putExtra("json", gson.toJson(product));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}