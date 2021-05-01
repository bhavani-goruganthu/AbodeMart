package com.example.abodemart.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.abodemart.R
import com.example.abodemart.database.CartDatabase
import com.example.abodemart.database.OrderDatabase
import com.example.abodemart.models.OrderData
import com.example.abodemart.utils.MSPButtonBold
import java.text.SimpleDateFormat
import java.time.*
import java.time.temporal.TemporalAdjusters
import java.util.*

class CheckoutActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        setupActionBar()
        val ctViewCOD = findViewById<CheckedTextView>(R.id.ctv_COD)
        val ctViewRent = findViewById<CheckedTextView>(R.id.ctv_add_to_rent)
        val btnPlaceOrder = findViewById<MSPButtonBold>(R.id.btn_place_order)

        if (ctViewCOD != null) {
            ctViewCOD.isChecked = true
            ctViewCOD.setCheckMarkDrawable(
                android.R.drawable.checkbox_on_background
            )
            ctViewCOD.setOnClickListener(this)
        }
        if (ctViewRent != null) {
            ctViewRent.isChecked = false
            ctViewRent.setCheckMarkDrawable(
                android.R.drawable.checkbox_off_background
            )
            ctViewRent.setOnClickListener(this)
        }
        btnPlaceOrder.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        val ctViewCOD = findViewById<CheckedTextView>(R.id.ctv_COD)
        val ctViewRent = findViewById<CheckedTextView>(R.id.ctv_add_to_rent)
        // room db
        val cartDatabase = Room.databaseBuilder(
            this, CartDatabase::class.java, "cart_database"
        ).allowMainThreadQueries().build()
        // get all items
        val allItems = cartDatabase.cartDao().getAllItems()

        // order db
        val orderDatabase = Room.databaseBuilder(
            this, OrderDatabase::class.java, "order_database"
        ).allowMainThreadQueries().build()

        if (v != null) {
            when (v.id) {
                R.id.ctv_COD -> {
                    ctViewCOD.isChecked = !ctViewCOD.isChecked
                    ctViewRent.isChecked = !ctViewCOD.isChecked
                    ctViewCOD.setCheckMarkDrawable(
                        if (ctViewCOD.isChecked)
                            android.R.drawable.checkbox_on_background
                        else
                            android.R.drawable.checkbox_off_background
                    )
                    ctViewRent.setCheckMarkDrawable(
                        if (ctViewRent.isChecked)
                            android.R.drawable.checkbox_on_background
                        else
                            android.R.drawable.checkbox_off_background
                    )
                }
                R.id.ctv_add_to_rent -> {
                    ctViewRent.isChecked = !ctViewRent.isChecked
                    ctViewCOD.isChecked = !ctViewRent.isChecked
                    ctViewRent.setCheckMarkDrawable(
                        if (ctViewRent.isChecked)
                            android.R.drawable.checkbox_on_background
                        else
                            android.R.drawable.checkbox_off_background
                    )
                    ctViewCOD.setCheckMarkDrawable(
                        if (ctViewCOD.isChecked)
                            android.R.drawable.checkbox_on_background
                        else
                            android.R.drawable.checkbox_off_background
                    )
                }
                R.id.btn_place_order -> {

                    val simpleDateFormat = SimpleDateFormat.getDateTimeInstance()
                    val currentDateAndTime: String = simpleDateFormat.format(Date())

                    // can be changed based on preferred delivery day
                    val nextWedDate = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY))


                    orderDatabase.orderDao().insertOrder(
                        OrderData(
                            orderStatus = "Processing",
                            orderOrderedDate = currentDateAndTime,
                            orderDeliveredDate = nextWedDate.toString(),
                            orderItemsCount = allItems.size.toString(),
                            orderItemsCost = "",
                            orderShippingCost = "",
                            orderTaxCost = "",
                            orderTotalCost = "",
                            orderedItems = allItems.toString()
                        )
                    )



                    cartDatabase.cartDao().deleteAllItems()

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("")
                    builder.setMessage("Order Successful!!")
                    builder.setIcon(android.R.drawable.ic_dialog_info)
                    builder.setPositiveButton("Ok") { dialogInterface , which ->
                        dialogInterface.dismiss()
                        val intent = Intent(this@CheckoutActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCanceledOnTouchOutside(false)
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
            }
        }
    }

    // for the action bar (left arrow like back button)
    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_checkout))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_home_white_24dp)
            actionBar.title = ""
        }
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_checkout).setNavigationOnClickListener {
            val intent = Intent(this@CheckoutActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}