package com.example.lafiores.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lafiores.R
import com.example.lafiores.model.cart.Cart

class CartProductAdapter(private val productInCart: Cart):
        RecyclerView.Adapter<CartProductAdapter.CartViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
     val v: View = LayoutInflater.from(parent.context).inflate(R.layout.cart_product_item, parent, false)
        return CartViewHolder(v)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.name.text = productInCart.idProduct.toString()
    }

    override fun getItemCount(): Int {
        return productInCart.idProduct
    }
    class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameProductTextView)

    }

}