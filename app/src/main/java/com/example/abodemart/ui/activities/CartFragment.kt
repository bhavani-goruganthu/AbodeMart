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
import com.example.abodemart.ui.fragments.MyListAdapter
import com.example.abodemart.utils.MSPTextView
import com.example.abodemart.utils.MSPTextViewBold

class CartFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.cart_dialog, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.rv_cart_items)
        recyclerView.adapter =
            MyListAdapter(activity as FragmentActivity, (activity as HomeActivity).myCartItemsList)
        recyclerView.layoutManager = LinearLayoutManager(activity as FragmentActivity)

        if ((activity as HomeActivity).myCartItemsList.size == 0) {
            rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = "Start Adding Items to the Cart"
        } else if ((activity as HomeActivity).myCartItemsList.size > 0){
            rootView.findViewById<MSPTextViewBold>(R.id.tv_info).text = "Swipe Right to remove items"
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
                val position = viewHolder.adapterPosition
                (activity as HomeActivity).myCartItemsList.removeAt(position)
                recyclerView.removeViewAt(position)
                MyListAdapter(
                    activity as FragmentActivity,
                    (activity as HomeActivity).myCartItemsList
                ).notifyItemRemoved(position)
                MyListAdapter(
                    activity as FragmentActivity,
                    (activity as HomeActivity).myCartItemsList
                ).notifyItemRangeChanged(position, (activity as HomeActivity).myCartItemsList.size)
                MyListAdapter(
                    activity as FragmentActivity,
                    (activity as HomeActivity).myCartItemsList
                ).notifyDataSetChanged()
            }
        }
        val swap = ItemTouchHelper(itemSwipe)
        swap.attachToRecyclerView(recyclerView)

        return rootView
    }
}