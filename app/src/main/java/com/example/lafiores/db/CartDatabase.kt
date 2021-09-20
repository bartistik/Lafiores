package com.example.lafiores.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lafiores.model.cart.Cart

@Database(entities = arrayOf(Cart::class), version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getDatabaseClient(context: Context): CartDatabase {
            if (INSTANCE != null) {
                return INSTANCE!!
            }
            synchronized(this) {
                INSTANCE = Room
                        .databaseBuilder(context, CartDatabase::class.java, "cart")
                        .fallbackToDestructiveMigration()
                        .build()
                return INSTANCE!!
            }
        }
    }
}