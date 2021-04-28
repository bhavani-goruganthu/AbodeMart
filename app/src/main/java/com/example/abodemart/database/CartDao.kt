package com.example.abodemart.database

import androidx.room.*
import com.example.abodemart.models.CartData

@Dao
interface CartDao {
    @Query("SELECT * FROM CartData")
    fun getAllItems() : List<CartData>

    @Insert
    fun insertItem(item: CartData)

    @Update
    fun updateItem(item: CartData)

    @Delete
    fun deleteItem(item: CartData)
}