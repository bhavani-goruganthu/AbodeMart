package com.example.abodemart.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import com.example.abodemart.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }

        // Click event assigned to Forgot Password Text
        findViewById<TextView>(R.id.tv_forgot_password).setOnClickListener(this)
        // Click event assigned to Login Button
        findViewById<TextView>(R.id.btn_login).setOnClickListener(this)
        // Click event assigned to Register Text
        findViewById<TextView>(R.id.tv_register).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v != null) {
            when (v.id) {
                R.id.tv_forgot_password -> {
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_login -> {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.tv_register -> {
                    //launch the register screen when the user clicks on the text
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                    // finish() // use finish() to stop from overlapping the layers.
                    // if no finish() user can click on back button and go back to the previous loaded page.
                }
            }
        }
    }
}