package com.example.lafiores.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lafiores.R;
import com.example.lafiores.databinding.ListProductItemBinding;
import com.example.lafiores.model.product.Product;
import com.example.lafiores.view.DetailProductActivity;

public class ListProductAdapter extends PagedListAdapter<Product, ListProductAdapter.ListProductViewHolder> {

    private Context context;
//    private ArrayList<Product> products = new ArrayList<Product>();
    private Application application;

    public ListProductAdapter(Context context) {
        super(Product.CALLBACK);
        this.context = context;
//        this.products = products;
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

                    if(position != RecyclerView.NO_POSITION) {
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
