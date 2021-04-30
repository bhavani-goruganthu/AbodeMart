package com.example.abodemart.ui.activities

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.abodemart.R
import com.example.abodemart.database.CartDatabase
import com.example.abodemart.database.ProductDatabase
import com.example.abodemart.ui.adapters.ProductsAdapter
import com.example.abodemart.utils.MSPButtonBold
import com.example.abodemart.utils.MSPEditText
import com.example.abodemart.utils.MSPTextViewBold

class StoreActivity : BaseCartMenuActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        setupActionBar()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }

        // room db for products
        val productDatabase = Room.databaseBuilder(
            applicationContext, ProductDatabase::class.java, "product_database"
        ).allowMainThreadQueries().build()

        // room db
        val cartDatabase = Room.databaseBuilder(
            this, CartDatabase::class.java, "cart_database"
        ).allowMainThreadQueries().build()

        when {
            intent.hasExtra("Costco") -> {
                val storeName = "Costco"
                findViewById<MSPTextViewBold>(R.id.tv_title_store).text = storeName
//                myProductsList.add(ProductData(0,"Cherry Tomato", R.drawable.product_cherry_tomato, "$ 1.98 /lb", storeName))
//                myProductsList.add(ProductData(1, "Grape Tomato", R.drawable.product_grape_tomato, "$ 0.99 /lb", storeName))
//                myProductsList.add(ProductData(2, "Organic Sweet Tomato", R.drawable.product_sweet_tomato, "$ 2.69 /lb", storeName))
                findViewById<RecyclerView>(R.id.rv_products_list).apply {
                    layoutManager = GridLayoutManager(this@StoreActivity, 2)
                    adapter = ProductsAdapter(
                        this@StoreActivity,
                        productDatabase,
                        cartDatabase,
                        "%%",
                        storeName
                    )
                }
            }
            intent.hasExtra("Whole Foods") -> {
                val storeName = "Whole Foods"
                findViewById<MSPTextViewBold>(R.id.tv_title_store).text = storeName
//                myProductsList.add(ProductData(0, "Cherry Tomato", R.drawable.product_cherry_tomato, "$ 1.98 /lb", storeName))
//                myProductsList.add(ProductData(1, "Wine Tomato", R.drawable.product_wine_tomato, "$ 1.50 /lb", storeName))
//                myProductsList.add(ProductData(2, "Roma Tomato", R.drawable.product_roma_tomato, "$ 2.18 /lb", storeName))
                findViewById<RecyclerView>(R.id.rv_products_list).apply {
                    layoutManager = GridLayoutManager(this@StoreActivity, 2)
                    adapter = ProductsAdapter(
                        this@StoreActivity,
                        productDatabase,
                        cartDatabase,
                        "%%",
                        storeName
                    )
                }
            }
            intent.hasExtra("New India Bazar") -> {
                val storeName = "New India Bazar"
                findViewById<MSPTextViewBold>(R.id.tv_title_store).text = storeName
//                myProductsList.add(ProductData(0, "Cherry Tomato", R.drawable.product_cherry_tomato, "$ 1.98 /lb", storeName))
//                myProductsList.add(ProductData(1, "Grape Tomato", R.drawable.product_grape_tomato, "$ 0.96 /lb", storeName))
//                myProductsList.add(ProductData(2, "Green Tomato", R.drawable.product_green_tomato, "$ 2.99 /lb", storeName))
                findViewById<RecyclerView>(R.id.rv_products_list).apply {
                    layoutManager = GridLayoutManager(this@StoreActivity, 2)
                    adapter = ProductsAdapter(
                        this@StoreActivity,
                        productDatabase,
                        cartDatabase,
                        "%%",
                        storeName
                    )
                }
            }
        }

        // Click event assigned to Search
        findViewById<MSPButtonBold>(R.id.btn_search).setOnClickListener(this@StoreActivity)

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

    override fun onClick(v: View?) {
        // room db for products
        val productDatabase = Room.databaseBuilder(
            applicationContext, ProductDatabase::class.java, "product_database"
        ).allowMainThreadQueries().build()
        // room db
        val cartDatabase = Room.databaseBuilder(
            this, CartDatabase::class.java, "cart_database"
        ).allowMainThreadQueries().build()
        val searchTerm = findViewById<MSPEditText>(R.id.et_search).text
        if (v != null) {
            when (v.id) {
                R.id.btn_search -> {
                    when {
                        intent.hasExtra("Costco") -> {
                            val storeName = "Costco"
                            findViewById<MSPTextViewBold>(R.id.tv_title_store).text = storeName
                            findViewById<RecyclerView>(R.id.rv_products_list).apply {
                                layoutManager = GridLayoutManager(this@StoreActivity, 2)
                                adapter = ProductsAdapter(
                                    this@StoreActivity,
                                    productDatabase,
                                    cartDatabase,
                                    "%${searchTerm}%",
                                    storeName
                                )
                            }
                        }
                        intent.hasExtra("Whole Foods") -> {
                            val storeName = "Whole Foods"
                            findViewById<MSPTextViewBold>(R.id.tv_title_store).text = storeName
                            findViewById<RecyclerView>(R.id.rv_products_list).apply {
                                layoutManager = GridLayoutManager(this@StoreActivity, 2)
                                adapter = ProductsAdapter(
                                    this@StoreActivity,
                                    productDatabase,
                                    cartDatabase,
                                    "%${searchTerm}%",
                                    storeName
                                )
                            }
                        }
                        intent.hasExtra("New India Bazar") -> {
                            val storeName = "New India Bazar"
                            findViewById<MSPTextViewBold>(R.id.tv_title_store).text = storeName
                            findViewById<RecyclerView>(R.id.rv_products_list).apply {
                                layoutManager = GridLayoutManager(this@StoreActivity, 2)
                                adapter = ProductsAdapter(
                                    this@StoreActivity,
                                    productDatabase,
                                    cartDatabase,
                                    "%${searchTerm}%",
                                    storeName
                                )
                            }
                        }
                    }
                }
            }
        }
    }


}