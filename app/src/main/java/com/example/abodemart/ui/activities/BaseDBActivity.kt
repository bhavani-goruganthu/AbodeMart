package com.example.abodemart.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.abodemart.R
import com.example.abodemart.database.CartDatabase
import com.example.abodemart.database.ProductDatabase
import com.example.abodemart.models.CartData
import com.example.abodemart.models.ProductData
import com.example.abodemart.ui.adapters.MyListAdapter

open class BaseDBActivity : AppCompatActivity() {
    //    var myCartItemsList = ArrayList<CartData>()
    var myProductsList = ArrayList<ProductData>()
//    lateinit var myListAdapter: MyListAdapter
//    val dialog = CartActivity()

//
//        // room db
//        var productDatabase = Room.databaseBuilder(
//            this, ProductDatabase::class.java, "product_database"
//        )
////            .build()
//
//        var cartDatabase = Room.databaseBuilder(
//            this, CartDatabase::class.java, "cart_database"
//        )
////            .allowMainThreadQueries()
////            .build()


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_cart, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cart -> {
                val dialog = CartActivity()
                val fragmentManager = supportFragmentManager
                dialog.show(fragmentManager, "cartDialog")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}