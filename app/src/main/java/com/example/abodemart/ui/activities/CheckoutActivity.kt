package com.example.abodemart.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abodemart.R

class CheckoutActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val ctViewCOD = findViewById<CheckedTextView>(R.id.ctv_COD)
        val ctViewRent = findViewById<CheckedTextView>(R.id.ctv_add_to_rent)
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
    }

    override fun onClick(v: View?) {
        val ctViewCOD = findViewById<CheckedTextView>(R.id.ctv_COD)
        val ctViewRent = findViewById<CheckedTextView>(R.id.ctv_add_to_rent)
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
            }
        }
    }
}