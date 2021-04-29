package com.example.abodemart.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.abodemart.models.ProductData

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductData")
    fun getAllProducts() : List<ProductData>

    @Query("SELECT * FROM ProductData WHERE store=:storeName")
    fun getStoreProducts(storeName: String?) : List<ProductData>

    @Insert
    fun insertProduct(product: ProductData)

    @Update
    fun updateProduct(product: ProductData)

    @Query("DELETE FROM ProductData WHERE product_uid = :id")
    fun deleteProduct(id: Int?)

    @Query("DELETE FROM ProductData")
    fun deleteAllProducts()
}