package com.example.lafiores.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lafiores.R;
import com.example.lafiores.model.product.Attribute;
import com.example.lafiores.view.DetailProductActivity;

import java.util.ArrayList;

public class DetailsProductAdapter extends RecyclerView.Adapter<DetailsProductAdapter.DetailsViewHolder> {

    private final ArrayList<Attribute> detailsList;

    public DetailsProductAdapter(ArrayList<Attribute> detailsList, DetailProductActivity detailProductActivity) {
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.detail_attributes_product,
                parent,
                false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        holder.bind(detailsList.get(position));
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameDetail;
        private final TextView optionDetail;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameDetail = itemView.findViewById(R.id.textView2);
            optionDetail = itemView.findViewById(R.id.textView);
        }

        public void bind(Attribute attribute) {
            nameDetail.setText(attribute.getName());
            optionDetail.setText(TextUtils.join(", ", attribute.getOptions()));
        }
    }
}