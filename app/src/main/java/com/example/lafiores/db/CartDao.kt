package com.example.lafiores.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafiores.model.cart.Cart

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCart(cart: Cart)

    @Query("DELETE FROM cart_table")
    fun delete()

    @Query("SELECT * FROM cart_table ORDER BY idProduct DESC")
    fun getCartDetail(): LiveData<Cart>

}