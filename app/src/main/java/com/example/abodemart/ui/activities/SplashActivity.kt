package com.example.abodemart.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import com.example.abodemart.R
import com.google.firebase.auth.FirebaseAuth


class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }

        val time : Long = 1500
        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                showProgressDialog("Please Wait..")
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    if(user.uid == "8C2viLsrQPe4f8nNuaKok9TKW773") {
                        hideProgressDialog()
                        showErrorSnackBar("Hello Admin!!", false)
                        val intent = Intent(this@SplashActivity, AdminManageProductActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // User is signed in
                        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                        hideProgressDialog()
                        startActivity(intent)
                    }
                } else {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    hideProgressDialog()
                    startActivity(intent)
                    finish()
                }
            }, time
        )


    }
}