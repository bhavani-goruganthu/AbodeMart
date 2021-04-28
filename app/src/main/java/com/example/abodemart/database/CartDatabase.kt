package com.example.abodemart.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.abodemart.models.CartData

// every time dataModel is updated version should be changed
@Database(entities = [CartData:: class], version = 1)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao() : CartDao
}