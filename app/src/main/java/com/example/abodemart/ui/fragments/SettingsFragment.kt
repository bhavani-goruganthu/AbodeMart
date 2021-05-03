package com.example.abodemart.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.abodemart.R
import com.example.abodemart.ui.activities.LoginActivity
import com.example.abodemart.ui.activities.UserProfileActivity
import com.example.abodemart.utils.MSPButtonBold
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)

        view.findViewById<TextView>(R.id.tv_edit).setOnClickListener{
            val intent = Intent(this@SettingsFragment.context, UserProfileActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<MSPButtonBold>(R.id.btn_logout).setOnClickListener{
            val builder = AlertDialog.Builder(this@SettingsFragment.context)
            builder.setTitle("Alert")
            builder.setMessage("Are you sure you want to Logout?")
            builder.setIcon(R.drawable.ic_baseline_warning_24)

            builder.setPositiveButton("Yes") { dialogInterface, which ->
                dialogInterface.dismiss()
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@SettingsFragment.context, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            builder.setNegativeButton("No") { dialogInterface, which ->
                dialogInterface.dismiss()
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.setCanceledOnTouchOutside(true)
            alertDialog.show()
        }
        return view
    }

}




