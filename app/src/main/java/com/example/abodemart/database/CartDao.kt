package com.example.abodemart.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.abodemart.models.CartData

@Dao
interface CartDao {
    @Query("SELECT * FROM CartData")
    fun getAllItems() : List<CartData>

    @Insert
    fun insertItem(item: CartData)

}