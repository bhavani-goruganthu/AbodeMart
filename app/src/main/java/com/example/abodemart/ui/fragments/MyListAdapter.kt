package com.example.abodemart.ui.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.abodemart.R
import com.example.abodemart.models.CartData

class MyListAdapter(private val context: Context, private val myCartItemsList: List<CartData>): RecyclerView.Adapter<MyListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val myListItem = LayoutInflater.from(context).inflate(R.layout.cart_item, viewGroup, false)
        return ViewHolder(myListItem)
    }

    override fun getItemCount(): Int {
        return myCartItemsList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val newCartList = myCartItemsList[position]
        viewHolder.cardItemTitle .text = newCartList.itemName
        viewHolder.cardItemStoreName .text = newCartList.storeName
        viewHolder.cartItemCost.text = newCartList.itemCost
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var cardItemTitle : TextView = itemView.findViewById<TextView>(R.id.card_item_title)
        var cardItemStoreName : TextView = itemView.findViewById<TextView>(R.id.card_item_storeName)
        var cartItemCost :  TextView = itemView.findViewById<TextView>(R.id.cart_item_cost)
    }
}