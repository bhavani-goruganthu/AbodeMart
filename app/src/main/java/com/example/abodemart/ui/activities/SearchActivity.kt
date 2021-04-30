package com.example.abodemart.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
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

class SearchActivity : BaseCartMenuActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
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

        findViewById<RecyclerView>(R.id.rv_products_search_list).apply {
            layoutManager = GridLayoutManager(this@SearchActivity, 2)
            adapter = ProductsAdapter(
                this@SearchActivity,
                productDatabase,
                cartDatabase,
                "%%"
//                "%%"
            )
        }
        // Click event assigned to Search
        findViewById<MSPButtonBold>(R.id.btn_search).setOnClickListener(this@SearchActivity)

    }

    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_search_activity))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
            actionBar.title = ""
        }
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_search_activity).setNavigationOnClickListener { onBackPressed() }
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
                    findViewById<RecyclerView>(R.id.rv_products_search_list).apply {
                        layoutManager = GridLayoutManager(this@SearchActivity, 2)
                        adapter = ProductsAdapter(
                            this@SearchActivity,
                            productDatabase,
                            cartDatabase,
                            "%${searchTerm}%"
//                            "%%"
                        )
                    }

                }
            }
        }
    }
}
