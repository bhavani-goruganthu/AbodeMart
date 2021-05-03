package com.example.abodemart.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderData(
    @PrimaryKey(autoGenerate = false) val order_uid: String,
    val orderStatus: String,
    val orderOrderedDate: String,
    val orderDeliveredDate: String,
    val orderItemsCount: String,
    val orderShippingCost: String,
    val orderTaxCost: String,
    val orderTotalCost: String,
    val orderedItems: String
)