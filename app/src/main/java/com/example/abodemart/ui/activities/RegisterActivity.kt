package com.example.abodemart.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.abodemart.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
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
        val spinner: Spinner = findViewById(R.id.days_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.days_array,
            android.R.layout.simple_list_item_checked

        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        findViewById<TextView>(R.id.tv_login).setOnClickListener {
            onBackPressed()
        }
    }

    // for the action bar (left arrow like back button)
    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_register_activity))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_green_color_back_24dp)
            actionBar.title = ""
        }
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_register_activity).setNavigationOnClickListener { onBackPressed() }
    }
}