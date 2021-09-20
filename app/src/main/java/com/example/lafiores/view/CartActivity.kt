package com.example.lafiores.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lafiores.R
import com.example.lafiores.adapter.CartProductAdapter
import com.example.lafiores.viewmodel.CartViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var productsInCart: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_cart)

        productsInCart = ViewModelProviders.of(this).get(CartViewModel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        productsInCart.getDetailsCart(applicationContext)?.observe(this, Observer {
            products ->
            recyclerView.adapter = CartProductAdapter(products)
        })
    }

}