package com.example.lafiores.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.lafiores.R;
import com.example.lafiores.adapter.DetailsProductAdapter;
import com.example.lafiores.model.product.Attribute;
import com.example.lafiores.model.product.Image;
import com.example.lafiores.model.product.Product;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DetailProductActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {

    private TextView titleProductTextView;
    private TextView priceProductTextView;
    private TextView oldPriceTextView;
    private TextView descriptionProductTextView;
    private Button buyButton;
    private SliderLayout productImageSlider;
    private DetailsProductAdapter detailsProductAdapter;
    private RecyclerView recyclerView;
    TextView changeOnDetailsTextView;
    TextView changeOnDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        productImageSlider = findViewById(R.id.sliderLayout);
        titleProductTextView = findViewById(R.id.titleProductTextView);
        priceProductTextView = findViewById(R.id.priceProduct);
        oldPriceTextView = findViewById(R.id.oldPrice);
        descriptionProductTextView = findViewById(R.id.descriptionProductTextView);
        buyButton = findViewById(R.id.buyButton);
        recyclerView = findViewById(R.id.detailProductRecyclerView);
        changeOnDetailsTextView = findViewById(R.id.changeOnDetailsProductTextView);
        changeOnDescriptionTextView = findViewById(R.id.changeOnDescriptionProductTextView);

        Intent intent = getIntent();
        Gson gson = new Gson();
        String strObj = intent.getStringExtra("json");
        Product product = gson.fromJson(strObj, Product.class);

        ArrayList<Image> images = (ArrayList<Image>) product.getImages();
        //Слайдер картинок
        for (int i = 0; i < images.size(); i++) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView
                    .image(images.get(i).getSrc())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .empty(R.drawable.lafiores_logo)
                    .setOnSliderClickListener(this);
            productImageSlider.addSlider(defaultSliderView);
        }
        productImageSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        productImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        productImageSlider.startAutoCycle(4000, 5000, true);

        titleProductTextView.setText(product.getName());

        if (product.getSalePrice() != null) {
            priceProductTextView.setText(product.getSalePrice() + " ₴");
            oldPriceTextView.setPaintFlags(oldPriceTextView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            oldPriceTextView.setText(product.getRegularPrice() + " ₴");
        } else {
            priceProductTextView.setText(product.getRegularPrice() + " ₴");
            oldPriceTextView.setVisibility(View.GONE);
        }

        descriptionProductTextView.setText(Html.fromHtml(product.getDescription()));
        detailsProductAdapter = new DetailsProductAdapter((ArrayList<Attribute>) product.getAttributes(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailProductActivity.this));
        recyclerView.setAdapter(detailsProductAdapter);

        changeOnDetailsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                descriptionProductTextView.setVisibility(View.GONE);
            }
        });
        changeOnDescriptionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                descriptionProductTextView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
