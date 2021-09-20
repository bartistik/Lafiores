package com.example.lafiores.model.cart

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.lafiores.db.CartDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CartRepository {

    companion object {
        var cartDatabase: CartDatabase? = null
        var cart: LiveData<Cart>? = null

        fun initializeDb(context: Context): CartDatabase {
            return CartDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context, idProduct: Int, quantityProduct: Int) {
            cartDatabase = initializeDb(context)

            CoroutineScope(IO).launch {
                val cartDetails = Cart(idProduct, quantityProduct)
                cartDatabase!!.cartDao().insertProductCart(cartDetails)
            }
        }

        fun getCartDetails(context: Context): LiveData<Cart>? {
            cartDatabase = initializeDb(context)
            cart = cartDatabase!!.cartDao().getCartDetail()
            return cart
        }
    }

}