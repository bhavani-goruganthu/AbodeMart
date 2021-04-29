package com.example.abodemart.ui.activities

import android.os.Build
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.abodemart.R
import com.example.abodemart.database.ProductDatabase
import com.example.abodemart.models.ProductData
import com.example.abodemart.utils.MSPButtonBold

class HandleProductActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handle_product)
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


        // Click event assigned to Add Product
        findViewById<MSPButtonBold>(R.id.btn_add_product).setOnClickListener(this@HandleProductActivity)

        // Click event assigned to Update Product
        findViewById<MSPButtonBold>(R.id.btn_update_product).setOnClickListener(this@HandleProductActivity)

        // Click event assigned to Delete Product
        findViewById<MSPButtonBold>(R.id.btn_delete_product).setOnClickListener(this@HandleProductActivity)

        // Click event assigned to Delete all Products
        findViewById<MSPButtonBold>(R.id.btn_delete_all_products).setOnClickListener(this@HandleProductActivity)

        // Click event assigned to Delete all Products
        findViewById<MSPButtonBold>(R.id.btn_view_all_products).setOnClickListener(this@HandleProductActivity)

    }

    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_handle_product_activity))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
            actionBar.title = ""
        }
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_handle_product_activity).setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(v: View?) {
        val productDatabase = Room.databaseBuilder(
            applicationContext, ProductDatabase::class.java, "product_database"
        ).allowMainThreadQueries().build()

        val productIDUpdate = findViewById<EditText>(R.id.et_product_id)
        val productTitle = findViewById<EditText>(R.id.et_product_title)
        val productImageUrl = findViewById<EditText>(R.id.et_product_imageUrl)
        val productPrice = findViewById<EditText>(R.id.et_product_price)
        val productStore = findViewById<EditText>(R.id.et_product_store)
        val productID = findViewById<EditText>(R.id.et_delete_product_id)

        if (v != null) {
            when (v.id) {
                R.id.btn_add_product -> {
                    productDatabase.productDao().insertProduct(
                        ProductData(
                            title = productTitle.text.toString(),
                            imageUrl = productImageUrl.text.toString(),
                            price = productPrice.text.toString(),
                            store = productStore.text.toString()
                        )
                    )
                    val allProducts = productDatabase.productDao().getAllProducts()
//                    d("bhavani", "allProducts size? \n $allProducts")
                    productIDUpdate.setText("")
                    productTitle.setText("")
                    productImageUrl.setText("")
                    productPrice.setText("")
                    productStore.setText("")
                    productID.setText("")
                }
                R.id.btn_update_product -> {
                    productDatabase.productDao().updateProduct(
                        ProductData(
                            product_uid = productIDUpdate.text.toString().toInt(),
                            title = productTitle.text.toString(),
                            imageUrl = productImageUrl.text.toString(),
                            price = productPrice.text.toString(),
                            store = productStore.text.toString()
                        )
                    )
                    val allProducts = productDatabase.productDao().getAllProducts()
//                    d("bhavani", "allProducts size? \n $allProducts")
                    productIDUpdate.setText("")
                    productTitle.setText("")
                    productImageUrl.setText("")
                    productPrice.setText("")
                    productStore.setText("")
                    productID.setText("")
                }
                R.id.btn_delete_product -> {
                    productDatabase.productDao().deleteProduct(productID.text.toString().toInt())
                    val allProducts = productDatabase.productDao().getAllProducts()
//                    d("bhavani", "allProducts size? \n $allProducts")
                    productIDUpdate.setText("")
                    productTitle.setText("")
                    productImageUrl.setText("")
                    productPrice.setText("")
                    productStore.setText("")
                    productID.setText("")
                }
                R.id.btn_delete_all_products -> {
                    productDatabase.productDao().deleteAllProducts()
                    val allProducts = productDatabase.productDao().getAllProducts()
//                    d("bhavani", "allProducts ? \n $allProducts")
                    productIDUpdate.setText("")
                    productTitle.setText("")
                    productImageUrl.setText("")
                    productPrice.setText("")
                    productStore.setText("")
                    productID.setText("")
                }
                R.id.btn_view_all_products -> {
                    val allProducts = productDatabase.productDao().getAllProducts()
                    d("bhavani", "allProducts ? \n $allProducts")
                }

            }
        }
    }

}