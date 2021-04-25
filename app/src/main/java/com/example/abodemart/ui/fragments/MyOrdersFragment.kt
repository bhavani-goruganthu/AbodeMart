package com.example.abodemart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.abodemart.R

class MyOrdersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_myorders, container, false)
        val textView: TextView = root.findViewById(R.id.text_myOrders)
            textView.text = "This is My Orders Fragment"
        return root
    }
}