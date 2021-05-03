package com.example.abodemart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.abodemart.R
import com.example.abodemart.database.OrderDatabase
import com.example.abodemart.ui.activities.HomeActivity
import com.example.abodemart.ui.activities.ManageProductActivity
import com.example.abodemart.ui.adapters.OrdersAdapter
import com.example.abodemart.utils.MSPButtonBold

class MyOrdersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_myorders, container, false)

        root.findViewById<MSPButtonBold>(R.id.btn_add_product).setOnClickListener{
            val intent = Intent(this@MyOrdersFragment.context, ManageProductActivity::class.java)
            startActivity(intent)
        }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // room db for orders
        val orderDatabase = Room.databaseBuilder(
            (activity as HomeActivity).applicationContext, OrderDatabase::class.java, "order_database"
        ).allowMainThreadQueries().build()

        view.findViewById<RecyclerView>(R.id.rv_order_list).apply {
            layoutManager = LinearLayoutManager((activity as HomeActivity).applicationContext)
            adapter = OrdersAdapter(
                (activity as HomeActivity).applicationContext,
                orderDatabase,
            )
        }
    }

}