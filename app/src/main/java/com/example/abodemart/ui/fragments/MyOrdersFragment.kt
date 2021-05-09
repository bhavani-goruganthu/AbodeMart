package com.example.abodemart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.abodemart.R
import com.example.abodemart.database.OrderDatabase
import com.example.abodemart.ui.activities.HomeActivity
import com.example.abodemart.ui.activities.AdminManageProductActivity
import com.example.abodemart.ui.adapters.OrdersAdapter
import com.example.abodemart.utils.MSPButtonBold
import com.example.abodemart.utils.MSPTextViewBold

class MyOrdersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_myorders, container, false)
//
//        root.findViewById<MSPButtonBold>(R.id.btn_add_product).setOnClickListener{
//            val intent = Intent(this@MyOrdersFragment.context, AdminManageProductActivity::class.java)
//            startActivity(intent)
//        }
//        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // room db for orders
        val orderDatabase = Room.databaseBuilder(
            (activity as HomeActivity).applicationContext, OrderDatabase::class.java, "order_database"
        ).allowMainThreadQueries().build()

        val allStoreOrders = orderDatabase.orderDao().getAllOrders()

        if(allStoreOrders.isEmpty()) {
            view.findViewById<MSPTextViewBold>(R.id.tv_info).text = "No Orders Yet ..!! Start ordering groceries"
        } else {
            view.findViewById<MSPTextViewBold>(R.id.tv_info).text = "View your Orders"
        }


        view.findViewById<RecyclerView>(R.id.rv_order_list).apply {
            layoutManager = LinearLayoutManager((activity as HomeActivity).applicationContext)
            adapter = OrdersAdapter(
                (activity as HomeActivity).applicationContext,
                orderDatabase,
            )
        }
    }

}