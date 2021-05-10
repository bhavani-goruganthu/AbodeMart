package com.example.abodemart.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.example.abodemart.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : BaseActivity(), View.OnClickListener {
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

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(findViewById<EditText>(R.id.et_email).text.toString().trim {it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(findViewById<EditText>(R.id.et_password).text.toString().trim {it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                // not needed for now.. if successful , the message to be displayed is in loginRegisteredUser()
//                showErrorSnackBar("Details are Valid", false)
                true
            }
        }
    }

    private fun loginRegisteredUser() {
        if(validateLoginDetails()) {
            //show the progress dialog
            showProgressDialog(resources.getString(R.string.please_wait))

            //Get the text from editText and trim the space
            val email: String = findViewById<EditText>(R.id.et_email).text.toString().trim {it <= ' '}
            val password: String = findViewById<EditText>(R.id.et_password).text.toString().trim {it <= ' '}

            //login using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    hideProgressDialog()

                    // if the login is successful
                    if(task.isSuccessful) {
                        if(email == "admin@abodemart.com") {
                            showErrorSnackBar("Hello Admin!!", false)
                            val intent = Intent(this@LoginActivity, AdminManageProductActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            showErrorSnackBar("You are Logged in Successfully", false)
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
//                        FirestoreClass().getUserDetails(this@LoginActivity)
                    } else {
                        // if the login is not successful then show error message
//                        hideProgressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }

        }

    }

    override fun onClick(v: View?) {
        if(v != null) {
            when (v.id) {
                R.id.tv_forgot_password -> {
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_login -> {
                    loginRegisteredUser()
//                    if(validateLoginDetails()) {
//                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    }
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