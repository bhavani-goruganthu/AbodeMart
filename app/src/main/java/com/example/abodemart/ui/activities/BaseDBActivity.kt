package com.example.abodemart.ui.activities

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.abodemart.R
import com.example.abodemart.models.ProductData

open class BaseDBActivity : AppCompatActivity() {

    var myProductsList = ArrayList<ProductData>()

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