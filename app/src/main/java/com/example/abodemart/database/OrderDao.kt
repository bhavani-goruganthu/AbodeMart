package com.example.abodemart.database

import androidx.room.*
import com.example.abodemart.models.CartData
import com.example.abodemart.models.OrderData

@Dao
interface OrderDao {
    @Query("SELECT * FROM OrderData")
    fun getAllOrders() : List<OrderData>

    @Insert
    fun insertOrder(order: OrderData)

//    @Update
//    fun updateOrder(order: OrderData)
//
//    @Query("DELETE FROM OrderData WHERE order_uid = :id")
//    fun deleteOrder(id: Int?)
//
//    @Query("DELETE FROM OrderData")
//    fun deleteAllOrders()
}