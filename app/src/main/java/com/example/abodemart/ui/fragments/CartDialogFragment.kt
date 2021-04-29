package com.example.abodemart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.abodemart.ui.activities.StoreActivity
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
        val rootView: View = inflater.inflate(R.layout.activity_cart, container, false)
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
            rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = getString(R.string.empty_cart)
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
        var allItems = cartDatabase.cartDao().getAllItems()

        if (v != null) {
            when (v.id) {
                R.id.btn_checkout -> {
                    if(allItems.isNotEmpty()) {
                        val intent = Intent(this.context, CheckoutActivity::class.java)
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