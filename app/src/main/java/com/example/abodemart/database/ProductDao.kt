package com.example.abodemart.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.abodemart.models.ProductData

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductData")
    fun getAllProducts() : List<ProductData>

    @Insert
    fun insertProduct(item: ProductData)

}