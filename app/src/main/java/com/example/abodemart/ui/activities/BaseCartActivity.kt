package com.example.abodemart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.abodemart.R
import com.example.abodemart.models.CartData
import com.example.abodemart.models.ProductData
import com.example.abodemart.ui.adapters.MyListAdapter

open class BaseCartActivity : AppCompatActivity() {

    var myCartItemsList = ArrayList<CartData>()
    var myProductsList = ArrayList<ProductData>()
    lateinit var myListAdapter: MyListAdapter

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.home_cart, menu)
//        myCartItemsList.add(CartData("Tomato", "Whole Foods", "$ 30.00", "1"))
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.action_cart -> {
//                val dialog = CartActivity()
//                val fragmentManager = supportFragmentManager
//                dialog.show(fragmentManager, "cartDialog")
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}