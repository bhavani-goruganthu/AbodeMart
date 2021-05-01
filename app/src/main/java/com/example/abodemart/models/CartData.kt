package com.example.abodemart.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartData(
    @PrimaryKey(autoGenerate = true) val cart_uid: Int = 0,
    val itemName: String,
    val storeName: String,
    val itemCost: String,
    val itemCount: String,
    val itemPerCost: String,
//    val orderShippingCost: String,
//    val orderTaxCost: String,
//    val orderTotalCost: String,
//    val orderItemsCount: String,
)