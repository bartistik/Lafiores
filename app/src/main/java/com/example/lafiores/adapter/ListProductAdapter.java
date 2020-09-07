package com.example.lafiores.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lafiores.R;
import com.example.lafiores.databinding.ListProductItemBinding;
import com.example.lafiores.model.product.Product;
import com.example.lafiores.view.DetailProductActivity;

import java.util.ArrayList;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder> {

    private Context context;
    private ArrayList<Product> products = new ArrayList<Product>();
    private Application application;

    public ListProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListProductItemBinding listProductItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_product_item,
                parent,
                false);

        return new ListProductViewHolder(listProductItemBinding);
    }

    public ListProductAdapter(Application application) {
        this.application = application;
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.listProductItemBinding.setProduct(product);

//        String imagePath = products.get(position).getImages().get(0).getSrc();
//        String imagePath  = product.getImages().get(0).getSrc();
//        Log.d("TEEST", imagePath);
//        product.setImages(imagePath);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class ListProductViewHolder extends RecyclerView.ViewHolder {

        private ListProductItemBinding listProductItemBinding;


        public ListProductViewHolder(@NonNull ListProductItemBinding listProductItemBinding) {
            super(listProductItemBinding.getRoot());
            this.listProductItemBinding = listProductItemBinding;

            listProductItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    intent.putExtra("idProduct", products.get(getAdapterPosition()).getId());
                    intent.putExtra("imageProduct", products.get(getAdapterPosition()).getImages().get(0).getSrc());
                    intent.putExtra("titleProduct", products.get(getAdapterPosition()).getName());
                    intent.putExtra("priceProduct", products.get(getAdapterPosition()).getPrice());
                    intent.putExtra("oldPriceProduct", products.get(getAdapterPosition()).getSalePrice());
                    intent.putExtra("descriptionProduct", products.get(getAdapterPosition()).getDescription());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
