package com.example.abodemart.models
//import android.os.Parcelable
//import kotlinx.parcelize.Parcelize

// to parcel the entire user data to another screen
// in a specific format
//@Parcelize
//class Cart (
//    val itemName: String = "Tom",
//    val storeName: String = "",
//    val itemCost: String = "" ) : Parcelable

data class CartData(val itemName:String,
                    val storeName: String, val itemCost: String)