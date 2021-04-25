package com.example.abodemart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.abodemart.R
import com.example.abodemart.models.CartData
import com.google.android.material.snackbar.Snackbar


open class BaseActivity : AppCompatActivity() {
//    var myCartItemsList = ArrayList<String>()
//        var myCartItemsList = listOf<Cart>()

   var myCartItemsList = ArrayList<CartData>()

    // to display a message at the bottom of the screen
    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if(errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity, R.color.colorSnackBarError
                )
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity, R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }

}