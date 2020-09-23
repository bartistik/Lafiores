package com.example.lafiores.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lafiores.model.product.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<Product>> loadProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveProducts(List<Product> productList);

    @Query("SELECT * FROM products WHERE id = :id")
    LiveData<Product> getProduct(int id);

}
