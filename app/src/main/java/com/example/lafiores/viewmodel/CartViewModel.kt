package com.example.lafiores.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.lafiores.model.cart.Cart
import com.example.lafiores.model.cart.CartRepository

class CartViewModel : ViewModel() {

    var liveDataCart: LiveData<Cart>? = null

    fun insertData(context: Context, idProduct: Int, quantity: Int) {
        CartRepository.insertData(context, idProduct, quantity)
    }

    fun getDetailsCart(context: Context): LiveData<Cart>? {
        liveDataCart = CartRepository.getCartDetails(context)
        return liveDataCart
    }

}