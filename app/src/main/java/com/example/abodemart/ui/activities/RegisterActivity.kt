package com.example.abodemart.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.*
import com.example.abodemart.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : BaseActivity(), View.OnClickListener {
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
        // Click event assigned to Register Button
        findViewById<TextView>(R.id.btn_register).setOnClickListener(this)
    }

    // for the action bar (left arrow like back button)
    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_register_activity))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
            actionBar.title = ""
        }
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_register_activity).setNavigationOnClickListener { onBackPressed() }
    }

    //to validate the entries of the user
    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(
                findViewById<EditText>(R.id.et_first_name).text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }
            // TextUtils.isEmpty(findViewById<EditText>(R.id.et_last_name).text.toString().trim {it <= ' '}) || et_last_name.length() <= 3 -> {
            TextUtils.isEmpty(
                findViewById<EditText>(R.id.et_last_name).text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }
            TextUtils.isEmpty(
                findViewById<EditText>(R.id.et_email).text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(
                findViewById<EditText>(R.id.et_contact_number).text.toString()
                    .trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_contact_no), true)
                false
            }
            TextUtils.isEmpty(
                findViewById<EditText>(R.id.et_buildingNo).text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_building_no), true)
                false
            }
            TextUtils.isEmpty(
                findViewById<EditText>(R.id.et_aptNo).text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_apartment_no), true)
                false
            }
            findViewById<Spinner>(R.id.days_spinner).selectedItem.toString() == "Select Preferred Weekday" -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_preferred_weekday),
                    true
                )
                false
            }
            TextUtils.isEmpty(
                findViewById<EditText>(R.id.et_password).text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            TextUtils.isEmpty(
                findViewById<EditText>(R.id.et_confirm_password).text.toString()
                    .trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_confirm_password),
                    true
                )
                false
            }
            findViewById<EditText>(R.id.et_password).text.toString().trim { it <= ' ' } !=
                    findViewById<EditText>(R.id.et_confirm_password).text.toString()
                        .trim { it <= ' ' } -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_password_and_confirm_password_mismatch),
                    true
                )
                false
            }
            !findViewById<CheckBox>(R.id.cb_terms_and_conditions).isChecked -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_agree_terms_and_conditions),
                    true
                )
                false
            }
            else -> {
                // not needed for now.. if successful , the message to be displayed is in registerUser()
//                showErrorSnackBar(resources.getString(R.string.register_successful), false)
                true
            }
        }
    }

    private fun registerUser() {
        if (validateRegisterDetails()) {
            // progress bar is shown when clicked on register
            // and the registration process continues
            showProgressDialog("Please Wait ...")

            val email: String =
                findViewById<EditText>(R.id.et_email).text.toString().trim { it <= ' ' }
            val password: String =
                findViewById<EditText>(R.id.et_password).text.toString().trim { it <= ' ' }

            // FirebaseAuth is displaying because dependencies are added in build.gradle(app)
            // create user with email and pwd is a function of firebase auth
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                // add what to do after user is added in firebase
                .addOnCompleteListener(
                    // after waiting for authentication result which is "task"
                    OnCompleteListener<AuthResult> { task ->
                        // if the registration is successful
                        if (task.isSuccessful) {
                            // Firebase registered user as object
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            hideProgressDialog()
                            showErrorSnackBar(resources.getString(R.string.register_successful), false)
                             // signOut from register page and show login page to sign in again
                            FirebaseAuth.getInstance().signOut()
                             // this closes register screen and as login is the previous screen,
                             // login is displayed
                            finish()

//                            // create a new user object to store in Cloud FireStore
//                            val user = User(
//                                firebaseUser.uid,
//                                findViewById<EditText>(R.id.et_first_name).text.toString()
//                                    .trim { it <= ' ' },
//                                findViewById<EditText>(R.id.et_last_name).text.toString()
//                                    .trim { it <= ' ' },
//                                findViewById<EditText>(R.id.et_email).text.toString()
//                                    .trim { it <= ' ' }
//                            )

                            // pass the user details to Firestore class to create the collection
//                            FirestoreClass().registerUser(this@RegisterActivity, user)

                            // signOut from register page and show login page to sign in again
//                            FirebaseAuth.getInstance().signOut()
                            // this closes register screen and as login is the previous screen,
                            // login is displayed
//                            finish()
                        } else {
                            hideProgressDialog()
                            // if the registration is not successful then show error message
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
                )
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_register -> {
                    registerUser()
//                    if (validateRegisterDetails()) {
//                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    }
                }
            }
        }

    }
}