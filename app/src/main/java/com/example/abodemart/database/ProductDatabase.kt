package com.example.abodemart.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.abodemart.models.ProductData

// every time dataModel is updated version should be changed
@Database(entities = [ProductData:: class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
}