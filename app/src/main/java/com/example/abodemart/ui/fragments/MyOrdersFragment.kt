package com.example.abodemart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.abodemart.R
import com.example.abodemart.ui.activities.ManageProductActivity
import com.example.abodemart.utils.MSPButtonBold

class MyOrdersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_myorders, container, false)

        root.findViewById<MSPButtonBold>(R.id.btn_handle_product).setOnClickListener{
            val intent = Intent(this@MyOrdersFragment.context, ManageProductActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}