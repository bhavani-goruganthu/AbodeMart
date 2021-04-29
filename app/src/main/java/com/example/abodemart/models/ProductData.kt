package com.example.abodemart.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductData(
    @PrimaryKey(autoGenerate = true) val product_uid: Int = 0,
    val title: String,
    val imageUrl: String,
    val price: String,
    val store: String,
)