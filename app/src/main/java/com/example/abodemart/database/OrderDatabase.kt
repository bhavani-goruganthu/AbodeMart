package com.example.abodemart.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.abodemart.models.OrderData

// every time dataModel is updated version should be changed
@Database(entities = [OrderData:: class], version = 1)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun orderDao() : OrderDao
}