package com.example.abodemart.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.abodemart.R
import com.example.abodemart.models.CartData
import com.example.abodemart.models.ProductData
import com.example.abodemart.utils.MSPButton
import com.example.abodemart.utils.MSPTextView

class ProductsAdapter(
    private val myProductsList: ArrayList<ProductData>,
    private val myCartItemsList: ArrayList<CartData>
//    ,private var myListAdapter: MyListAdapter
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val myProductsListItem =
            LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(myProductsListItem)

    }

    override fun getItemCount(): Int {
        return myProductsList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val newProductsList = myProductsList[position]
        viewHolder.cardProductTitle.text = newProductsList.title
        viewHolder.cardProductStoreName.text = newProductsList.store
        viewHolder.cardProductCost.text = newProductsList.price
        viewHolder.cardProductPhoto.setImageResource(newProductsList.imageUrl)

        viewHolder.btnAddToCart.setOnClickListener {
            myCartItemsList.add(CartData("Tomato", "Whole Foods", "$ 30.00", "1"))
//            myListAdapter.notifyDataSetChanged()
//            myListAdapter.notifyItemInserted(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardProductPhoto: ImageView = itemView.findViewById<ImageView>(R.id.iv_product_photo)
        var cardProductTitle: TextView = itemView.findViewById<TextView>(R.id.card_product_title)
        var cardProductStoreName: MSPTextView =
            itemView.findViewById<MSPTextView>(R.id.card_product_storeName)
        var cardProductCost: TextView = itemView.findViewById<TextView>(R.id.card_product_cost)
        var btnAddToCart: MSPButton = itemView.findViewById<MSPButton>(R.id.btn_add_to_cart)
    }

}