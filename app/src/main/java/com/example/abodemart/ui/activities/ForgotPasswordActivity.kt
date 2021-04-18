package com.example.abodemart.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.abodemart.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
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

        findViewById<Button>(R.id.btn_submit).setOnClickListener {
            Toast.makeText(
                this@ForgotPasswordActivity,
                resources.getString(R.string.email_sent_success),
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    // for the action bar (left arrow like back button)
    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_forgot_password_activity))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
            actionBar.title = ""
        }
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_forgot_password_activity).setNavigationOnClickListener { onBackPressed() }
    }
}