package com.example.abodemart.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.abodemart.R
import com.example.abodemart.database.CartDatabase
import com.example.abodemart.models.CartData

class MyListAdapter(
    private val context: Context,
    private var cartDatabase: CartDatabase,
    private val rootView: View?,
) : RecyclerView.Adapter<MyListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val myListItem = LayoutInflater.from(context).inflate(R.layout.cart_item, viewGroup, false)
        return ViewHolder(myListItem)
    }

    override fun getItemCount(): Int {
        // get all items
        val allItems = cartDatabase.cartDao().getAllItems()
        return allItems.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var allItems = cartDatabase.cartDao().getAllItems()
        val newCartList = allItems[position]
        viewHolder.cardItemTitle.text = newCartList.itemName
        viewHolder.cardItemStoreName.text = newCartList.storeName
        viewHolder.cartItemCost.text = newCartList.itemCost
        viewHolder.itemsCount.text = newCartList.itemCount
        viewHolder.addIcon.setOnClickListener {
            val currentItemsCount = (viewHolder.itemsCount.text.toString().toInt())
            if (currentItemsCount > 0) {
                val updatedItemsCount = currentItemsCount + 1
                viewHolder.itemsCount.text = updatedItemsCount.toString()
                cartDatabase.cartDao().updateItem(
                    CartData(
                        cart_uid = newCartList.cart_uid,
                        itemName = viewHolder.cardItemTitle.text.toString(),
                        storeName = viewHolder.cardItemStoreName.text.toString(),
                        itemCost = viewHolder.cartItemCost.text.toString(),
                        itemCount = viewHolder.itemsCount.text.toString()
                    )
                )
                notifyItemChanged(viewHolder.adapterPosition)
                notifyDataSetChanged()

            }
        }
        viewHolder.removeIcon.setOnClickListener {
            val currentItemsCount = (viewHolder.itemsCount.text.toString().toInt())
            if (currentItemsCount > 1) {
                val updatedItemsCount = currentItemsCount - 1
                viewHolder.itemsCount.text = updatedItemsCount.toString()
                cartDatabase.cartDao().updateItem(
                    CartData(
                        cart_uid = newCartList.cart_uid,
                        itemName = viewHolder.cardItemTitle.text.toString(),
                        storeName = viewHolder.cardItemStoreName.text.toString(),
                        itemCost = viewHolder.cartItemCost.text.toString(),
                        itemCount = viewHolder.itemsCount.text.toString()
                    )
                )
                notifyItemChanged(viewHolder.adapterPosition)
                notifyDataSetChanged()
            }
        }
        viewHolder.deleteIcon.setOnClickListener {
            cartDatabase.cartDao().deleteItem(newCartList.cart_uid)
            notifyItemRemoved(viewHolder.adapterPosition)
            notifyDataSetChanged()
            allItems = cartDatabase.cartDao().getAllItems()
            if (allItems.isEmpty()) {
                val rv = rootView?.findViewById<TextView>(R.id.tv_info)
                if (rv != null) {
                    rv.text = context.resources.getString(R.string.empty_cart)
                }
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardItemTitle: TextView = itemView.findViewById<TextView>(R.id.card_item_title)
        var cardItemStoreName: TextView = itemView.findViewById<TextView>(R.id.card_item_storeName)
        var cartItemCost: TextView = itemView.findViewById<TextView>(R.id.cart_item_cost)
        var addIcon: ImageView = itemView.findViewById<ImageView>(R.id.add_icon)
        var removeIcon: ImageView = itemView.findViewById<ImageView>(R.id.remove_icon)
        var deleteIcon: ImageView = itemView.findViewById<ImageView>(R.id.delete_icon)
        var itemsCount: TextView = itemView.findViewById<TextView>(R.id.count_cart_item)
    }
}