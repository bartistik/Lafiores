package com.example.lafiores.model.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
        @ColumnInfo(name = "idProduct")
        var idProduct: Int,
        @ColumnInfo(name = "quantityItem")
        var quantity: Int
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCart")
    var idCart: Int? = null

}