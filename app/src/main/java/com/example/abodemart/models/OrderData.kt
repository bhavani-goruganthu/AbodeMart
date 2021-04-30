package com.example.abodemart.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderData(
    @PrimaryKey(autoGenerate = true) val order_uid: Int = 0,
    val orderStatus: String,
    val orderOrderedDate: String,
    val orderDeliveredDate: String,
    val orderItemsCount: String,
    val orderItemsCost: String,
    val orderShippingCost: String,
    val orderTaxCost: String,
    val orderTotalCost: String,
    val orderedItems: String
)