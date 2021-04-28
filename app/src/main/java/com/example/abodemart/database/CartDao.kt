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

    @Query("DELETE FROM CartData WHERE cart_uid = :id")
    fun deleteItem(id: Int?)

    @Query("DELETE FROM CartData")
    fun deleteAllItems()
}