package com.example.abodemart.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.abodemart.R
import com.example.abodemart.ui.fragments.SettingsFragment

class UserProfileActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setupActionBar()

        // to hide top status bar
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }

        // disabling few fields
        findViewById<EditText>(R.id.et_first_name).isEnabled = false
        findViewById<EditText>(R.id.et_last_name).isEnabled = false
        findViewById<EditText>(R.id.et_email).isEnabled = false

        // default values
        findViewById<EditText>(R.id.et_first_name).setText(R.string.john)
        findViewById<EditText>(R.id.et_last_name).setText(R.string.wick)
        findViewById<EditText>(R.id.et_email).setText(R.string.john_email)
        findViewById<EditText>(R.id.et_contact_number).setText(R.string._415_778_9034)
        findViewById<EditText>(R.id.et_buildingNo).setText(R.string.building_no)
        findViewById<EditText>(R.id.et_aptNo).setText(R.string.apt_no)

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
            spinner.setSelection(3)
        }

        // Click event assigned to Save
        findViewById<TextView>(R.id.btn_save_profile).setOnClickListener(this@UserProfileActivity)
    }
    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_user_profile_activity))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
            actionBar.title = ""
        }
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_user_profile_activity).setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(v: View?) {
        if(v!=null) {
            when(v.id) {
                R.id.btn_save_profile -> {
                    val intent = Intent(this@UserProfileActivity, SettingsFragment::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}