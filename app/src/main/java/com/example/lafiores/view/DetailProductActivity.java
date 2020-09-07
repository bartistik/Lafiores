package com.example.lafiores.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.lafiores.R;

public class DetailProductActivity extends AppCompatActivity {

    ImageView mainImageproductImageView;
    TextView titleProductTextView;
    TextView priceProcuctTextView;
    TextView oldPriceTextView;
    TextView descriptionProductTextView;
    Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        mainImageproductImageView = findViewById(R.id.imageProduct);
        titleProductTextView = findViewById(R.id.titleProductTextView);
        priceProcuctTextView = findViewById(R.id.priceProduct);
        oldPriceTextView = findViewById(R.id.oldPrice);
        descriptionProductTextView = findViewById(R.id.descriptionProductTextView);
        buyButton = findViewById(R.id.buyButton);

        Intent intent = getIntent();
        int id = intent.getIntExtra("idProduct", 0);
        Glide.with(this).load(intent.getStringExtra("imageProduct")).into(mainImageproductImageView);
        titleProductTextView.setText(intent.getStringExtra("titleProduct"));
        priceProcuctTextView.setText(intent.getStringExtra("priceProduct"));
        if(intent.getStringExtra("oldPriceProduct") != null) {
                        oldPriceTextView.setText(intent.getStringExtra("oldPriceProduct"));
                    } else {
                        oldPriceTextView.setVisibility(View.GONE);
                    }
        oldPriceTextView.setText(intent.getStringExtra("oldPriceProduct"));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            descriptionProductTextView.setText(Html.fromHtml(intent.getStringExtra("descriptionProduct"), Html.FROM_HTML_MODE_LEGACY));
        } else  {
            descriptionProductTextView.setText(Html.fromHtml(intent.getStringExtra("descriptionProduct")));

        }
//        ProductApiService productApiService = RetrofitInstance.getService();
//        Call<Product> call = productApiService.getDetailProduct(
//                id,
//                "ck_085bf7135759feb1c3b22a57531e537369dc1ecb",
//                "cs_d129ae26ab7c1f29ed5416efbdacc2459a5c60fd"
//        );
//        call.enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//                if (response.isSuccessful()) {
//
//                    Product productApiResponse =
//                             response.body();
//                    Log.d("TEEST2", "загружено " );
//                    titleProductTextView.setText(productApiResponse.getName());
//                    priceProcuctTextView.setText(productApiResponse.getPrice());
//                    if(productApiResponse.getSalePrice() != null) {
//                        oldPriceTextView.setText(productApiResponse.getSalePrice());
//                    } else {
//                        oldPriceTextView.setVisibility(View.GONE);
//                    }
////                    descriptionProductTextView.setText(productApiResponse.getDescription());
//                    Glide
//                            .with(getApplicationContext())
//                            .load(productApiResponse.getImages().get(0).getSrc())
//                            .into(mainImageproductImageView);
//                } else {
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//
//            }
//        });
    }
}
