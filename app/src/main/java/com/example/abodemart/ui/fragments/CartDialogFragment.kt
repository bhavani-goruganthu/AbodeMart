package com.example.abodemart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.abodemart.R
import com.example.abodemart.database.CartDatabase
import com.example.abodemart.ui.activities.CheckoutActivity
import com.example.abodemart.ui.adapters.MyListAdapter
import com.example.abodemart.utils.MSPButtonBold
import com.example.abodemart.utils.MSPTextViewBold

class CartDialogFragment() : DialogFragment(), View.OnClickListener {

    override fun getTheme() = R.style.RoundedCornersDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_cart, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.rv_cart_items)
        val clearCartButton = rootView.findViewById<MSPButtonBold>(R.id.btn_clear_cart)
        val checkoutButton = rootView.findViewById<MSPButtonBold>(R.id.btn_checkout)

        // room db
        val cartDatabase = Room.databaseBuilder(
            activity as FragmentActivity, CartDatabase::class.java, "cart_database"
        ).allowMainThreadQueries().build()

        // get all items
        var allItems = cartDatabase.cartDao().getAllItems()

        recyclerView.adapter =
            MyListAdapter(activity as FragmentActivity, cartDatabase, rootView as View)
        recyclerView.layoutManager = LinearLayoutManager(activity as FragmentActivity)

        if (allItems.isEmpty()) {
            rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = getString(R.string.empty_cart)
            clearCartButton.visibility = View.GONE
        } else if (allItems.isNotEmpty()){
            rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = getString(R.string.not_empty_cart)
            clearCartButton.visibility = View.VISIBLE
        }

        val itemSwipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.layoutPosition
                allItems = cartDatabase.cartDao().getAllItems()
                cartDatabase.cartDao().deleteItem(allItems[position].cart_uid)
                (recyclerView.adapter)!!.notifyItemRemoved(position)
                (recyclerView.adapter)!!.notifyDataSetChanged()
                allItems = cartDatabase.cartDao().getAllItems()
                if (allItems.isEmpty()) {
                    rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = getString(R.string.empty_cart)
                    clearCartButton.visibility = View.GONE
                    val tvCartItemsCount = rootView.findViewById<TextView>(R.id.tv_cart_items_count)
                    val tvCartTotal = rootView.findViewById<TextView>(R.id.tv_cart_total)
                    val tvOrderTaxCost = rootView.findViewById<TextView>(R.id.tv_order_Tax_cost)
                    val tvOrderTotalCost = rootView.findViewById<TextView>(R.id.tv_order_Total_cost)
                    // cart count
                    var cartItemsCount = 0
                    for(i in 0 until (allItems.size)){
                        cartItemsCount += allItems[i].itemCount.toInt()
                    }
                    if (tvCartItemsCount != null) {
                        tvCartItemsCount.text = "Count: ${cartItemsCount.toString()}"
                    }
                    // cart total
                    var cartTotal = 0.0
                    for(i in 0 until (allItems.size)){
                        cartTotal += allItems[i].itemCost.removeSurrounding("$", "").toFloat()
                    }
                    val updatedCartTotal = String.format("%.2f", cartTotal).toFloat()
                    if (tvCartTotal != null) {
                        tvCartTotal.text = "Cart Total : $${updatedCartTotal.toString()}"
                    }
                    // tax cost
                    val orderTaxCost = cartTotal * 0.1
                    val updatedOrderTaxCost = String.format("%.2f", orderTaxCost).toFloat()
                    if (tvOrderTaxCost != null) {
                        tvOrderTaxCost.text = "Tax : $${updatedOrderTaxCost.toString()}"
                    }
                    // update total and add tax
                    val orderTotalCost =  cartTotal + orderTaxCost + 10.0
                    val updatedOrderTotalCost = String.format("%.2f", orderTotalCost).toFloat()
                    if (tvOrderTotalCost != null) {
                        tvOrderTotalCost.text = "Total Cost : $${updatedOrderTotalCost.toString()}"
                    }
                }
            }
        }
        val swipe = ItemTouchHelper(itemSwipe)
        swipe.attachToRecyclerView(recyclerView)

        clearCartButton.setOnClickListener {
            val allItemsSize = cartDatabase.cartDao().getAllItems().size
            cartDatabase.cartDao().deleteAllItems()
            (recyclerView.adapter)!!.notifyItemRangeRemoved(0, allItemsSize)
            (recyclerView.adapter)!!.notifyDataSetChanged()
            clearCartButton.visibility = View.GONE

            val cartDatabase = Room.databaseBuilder(
                activity as FragmentActivity, CartDatabase::class.java, "cart_database"
            ).allowMainThreadQueries().build()
            // get all items
            val allItems = cartDatabase.cartDao().getAllItems()

            if (allItems.isEmpty()) {
                rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = getString(R.string.empty_cart)
                val tvCartItemsCount = rootView.findViewById<TextView>(R.id.tv_cart_items_count)
                val tvCartTotal = rootView.findViewById<TextView>(R.id.tv_cart_total)
                val tvOrderTaxCost = rootView.findViewById<TextView>(R.id.tv_order_Tax_cost)
                val tvOrderTotalCost = rootView.findViewById<TextView>(R.id.tv_order_Total_cost)

                // cart count
                var cartItemsCount = 0
                for(i in 0 until (allItems.size)){
                    cartItemsCount += allItems[i].itemCount.toInt()
                }
                if (tvCartItemsCount != null) {
                    tvCartItemsCount.text = "Count: ${cartItemsCount.toString()}"
                }
                // cart total
                var cartTotal = 0.0
                for(i in 0 until (allItems.size)){
                    cartTotal += allItems[i].itemCost.removeSurrounding("$", "").toFloat()
                }
                val updatedCartTotal = String.format("%.2f", cartTotal).toFloat()
                if (tvCartTotal != null) {
                    tvCartTotal.text = "Cart Total : $${updatedCartTotal.toString()}"
                }
                // tax cost
                val orderTaxCost = cartTotal * 0.1
                val updatedOrderTaxCost = String.format("%.2f", orderTaxCost).toFloat()
                if (tvOrderTaxCost != null) {
                    tvOrderTaxCost.text = "Tax : $${updatedOrderTaxCost.toString()}"
                }
                // update total and add tax
                val orderTotalCost =  cartTotal + orderTaxCost + 10.0
                val updatedOrderTotalCost = String.format("%.2f", orderTotalCost).toFloat()
                if (tvOrderTotalCost != null) {
                    tvOrderTotalCost.text = "Total Cost : $${updatedOrderTotalCost.toString()}"
                }
            }
        }
        checkoutButton.setOnClickListener(this)
        return rootView
    }

    override fun onClick(v: View?) {

        // room db
        val cartDatabase = Room.databaseBuilder(
            activity as FragmentActivity, CartDatabase::class.java, "cart_database"
        ).allowMainThreadQueries().build()

        // get all items
        val allItems = cartDatabase.cartDao().getAllItems()

        if (v != null) {
            when (v.id) {
                R.id.btn_checkout -> {
                    if(allItems.isNotEmpty()) {
                        val intent = Intent(this.context, CheckoutActivity::class.java)
                        val cartItemsCount = view?.findViewById<TextView>(R.id.tv_cart_items_count)?.text.toString()
                        val cartTotal = view?.findViewById<TextView>(R.id.tv_cart_total)?.text.toString()
                        val orderTaxCost = view?.findViewById<TextView>(R.id.tv_order_Tax_cost)?.text.toString()
                        val orderTotalCost = view?.findViewById<TextView>(R.id.tv_order_Total_cost)?.text.toString()
                        val bundle = Bundle()
                        bundle.putString("cartItemsCount", cartItemsCount)
                        bundle.putString("cartTotal", cartTotal)
                        bundle.putString("orderTaxCost", orderTaxCost)
                        bundle.putString("orderTotalCost", orderTotalCost)
//                        intent.putExtra("cartItemsCount", cartItemsCount)
//                        intent.putExtra("cartTotal", cartTotal)
//                        intent.putExtra("orderTaxCost", orderTaxCost)
//                        intent.putExtra("orderTotalCost", orderTotalCost)
                        intent.putExtras(bundle)
                        startActivity(intent)
                        this.dismiss()
                    } else {
                        Toast.makeText(
                            context,
                            "Add Items to the Cart to proceed..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}