package com.example.abodemart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.abodemart.R
import com.example.abodemart.ui.activities.LoginActivity
import com.example.abodemart.ui.activities.UserProfileActivity
import com.example.abodemart.utils.MSPButton

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
        view.findViewById<MSPButton>(R.id.btn_logout).setOnClickListener{
            val intent = Intent(this@SettingsFragment.context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        return view
    }

}




