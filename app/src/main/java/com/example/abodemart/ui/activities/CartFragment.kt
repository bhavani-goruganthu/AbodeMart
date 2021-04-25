package com.example.abodemart.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abodemart.R
import com.example.abodemart.ui.fragments.MyListAdapter

class CartFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.cart_dialog, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.rv_cart_items)
        recyclerView.adapter = MyListAdapter(activity as FragmentActivity, (activity as HomeActivity).myCartItemsList)
        recyclerView.layoutManager = LinearLayoutManager(activity as FragmentActivity)
        return rootView
    }
}