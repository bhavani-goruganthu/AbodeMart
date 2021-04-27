package com.example.abodemart.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abodemart.R
import com.example.abodemart.models.ProductData
import com.example.abodemart.ui.adapters.MyListAdapter
import com.example.abodemart.ui.adapters.ProductsAdapter
import com.example.abodemart.utils.MSPTextViewBold

class StoreActivity : BaseCartActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        setupActionBar()

//        myListAdapter = MyListAdapter(this, myCartItemsList, null)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }

        when {
            intent.hasExtra("Costco") -> {
                val storeName = "Costco"
                findViewById<MSPTextViewBold>(R.id.tv_title_store).text = storeName
                myProductsList.add(ProductData("Cherry Tomato", R.drawable.product_cherry_tomato, "$ 1.98 /lb", storeName))
                myProductsList.add(ProductData("Grape Tomato", R.drawable.product_grape_tomato, "$ 0.99 /lb", storeName))
                myProductsList.add(ProductData("Roma Tomato", R.drawable.product_roma_tomato, "$ 2.18 /lb", storeName))
            }
            intent.hasExtra("Whole Foods") -> {
                val storeName = "Whole Foods"
                findViewById<MSPTextViewBold>(R.id.tv_title_store).text = storeName
                myProductsList.add(ProductData("Cherry Tomato", R.drawable.product_cherry_tomato, "$ 1.98 /lb", storeName))
                myProductsList.add(ProductData("Wine Tomato", R.drawable.product_wine_tomato, "$ 1.50 /lb", storeName))
                myProductsList.add(ProductData("Organic Sweet Tomato", R.drawable.product_sweet_tomato, "$ 2.69 /lb", storeName))
            }
            intent.hasExtra("New India Bazar") -> {
                val storeName = "New India Bazar"
                findViewById<MSPTextViewBold>(R.id.tv_title_store).text = storeName
                myProductsList.add(ProductData("Cherry Tomato", R.drawable.product_cherry_tomato, "$ 1.98 /lb", storeName))
                myProductsList.add(ProductData("Grape Tomato", R.drawable.product_grape_tomato, "$ 0.96 /lb", storeName))
                myProductsList.add(ProductData("Green Tomato", R.drawable.product_green_tomato, "$ 2.99 /lb", storeName))
            }
        }

        findViewById<RecyclerView>(R.id.rv_products_list).apply {
            layoutManager = GridLayoutManager(this@StoreActivity, 2)
            adapter = ProductsAdapter(myProductsList, myCartItemsList)
        }

    }

    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_store_activity))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
            actionBar.title = ""
        }
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_store_activity).setNavigationOnClickListener { onBackPressed() }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.home_cart, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        when (id) {
//            // when settings in clicked on dashboard fragment
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