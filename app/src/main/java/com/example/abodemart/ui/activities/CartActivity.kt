package com.example.abodemart.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abodemart.R
import com.example.abodemart.ui.adapters.MyListAdapter
import com.example.abodemart.utils.MSPTextViewBold

class CartActivity : DialogFragment() {
    override fun getTheme() = R.style.RoundedCornersDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.activity_cart, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.rv_cart_items)
        recyclerView.adapter =
            MyListAdapter(activity as FragmentActivity, (activity as BaseCartActivity).myCartItemsList, rootView as View)
        recyclerView.layoutManager = LinearLayoutManager(activity as FragmentActivity)

        if ((activity as BaseCartActivity).myCartItemsList.size == 0) {
            rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = getString(R.string.empty_cart)
        } else if ((activity as BaseCartActivity).myCartItemsList.size > 0){
            rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = getString(R.string.not_empty_cart)
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
                (activity as BaseCartActivity).myCartItemsList.removeAt(position)
                (recyclerView.adapter)?.notifyItemRemoved(position)
                if ((activity as BaseCartActivity).myCartItemsList.size == 0) {
                    rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = getString(R.string.empty_cart)
                }
            }
        }
        val swipe = ItemTouchHelper(itemSwipe)
        swipe.attachToRecyclerView(recyclerView)
        return rootView
    }
}