package com.example.abodemart.ui.adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.abodemart.R
import com.example.abodemart.database.CartDatabase
import com.example.abodemart.database.ProductDatabase
import com.example.abodemart.models.CartData
import com.example.abodemart.utils.MSPButton
import com.example.abodemart.utils.MSPButtonBold
import com.example.abodemart.utils.MSPTextView
import com.example.abodemart.utils.MSPTextViewBold

class ProductsAdapter(
    private val context: Context,
    private var productDatabase: ProductDatabase,
    private var cartDatabase: CartDatabase,
    private var storeName: String
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val allProductsItem =
            LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(allProductsItem)
    }

    override fun getItemCount(): Int {
        // get all products
        val allStoreProducts = productDatabase.productDao().getStoreProducts(storeName)
        return allStoreProducts.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // get all products
        val allStoreProducts = productDatabase.productDao().getStoreProducts(storeName)
        val newProductsList = allStoreProducts[position]

        viewHolder.cardProductTitle.text = newProductsList.title
        viewHolder.cardProductStoreName.text = newProductsList.store
        viewHolder.cardProductCost.text = newProductsList.price

        // for Image
        val uri = newProductsList.imageUrl
        val contextObj =  viewHolder.cardProductPhoto.context
        val imageResource = contextObj.resources.getIdentifier(uri, "drawable", contextObj.packageName)
        viewHolder.cardProductPhoto.setImageResource(imageResource)

        viewHolder.btnAddToCart.setOnClickListener {
            val customDialog = Dialog(viewHolder.btnAddToCart.context)
            customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            customDialog.setContentView(R.layout.fragment_add_to_cart)
            val tvItemCount = customDialog.findViewById<MSPTextViewBold>(R.id.count_cart_item)

            customDialog.findViewById<ImageView>(R.id.remove_icon).setOnClickListener(View.OnClickListener {
                val currentItemsCount = (tvItemCount.text.toString().toInt())
                if (currentItemsCount > 1) {
                    val updatedItemsCount = currentItemsCount - 1
                    tvItemCount.text = updatedItemsCount.toString()
                }
            })
            customDialog.findViewById<ImageView>(R.id.add_icon).setOnClickListener(View.OnClickListener {
                val currentItemsCount = (tvItemCount.text.toString().toInt())
                if (currentItemsCount > 0) {
                    val updatedItemsCount = currentItemsCount + 1
                    tvItemCount.text = updatedItemsCount.toString()
                }
            })
            customDialog.findViewById<MSPButtonBold>(R.id.btn_add_to_cart).setOnClickListener(View.OnClickListener {
                val updatedCost = (newProductsList.price.removeSurrounding("$", " /lb").toFloat())*(tvItemCount.text.toString().toFloat())
                val itemCost = "\$ ${updatedCost.toString()}"
                cartDatabase.cartDao().insertItem(
                    CartData(
                        itemName = newProductsList.title,
                        storeName = newProductsList.store,
                        itemCost = itemCost,
                        itemCount = tvItemCount.text.toString(),
                        itemPerCost = newProductsList.price
                    )
                )
                customDialog.dismiss()
                Toast.makeText(
                    context,
                    "Added ${newProductsList.title} to the Cart!! ",
                    Toast.LENGTH_LONG
                ).show()
            })
            customDialog.findViewById<MSPButtonBold>(R.id.btn_cancel).setOnClickListener(View.OnClickListener {
                customDialog.dismiss()
            })
            customDialog.show()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardProductPhoto: ImageView = itemView.findViewById<ImageView>(R.id.iv_product_photo)
        var cardProductTitle: TextView = itemView.findViewById<TextView>(R.id.card_product_title)
        var cardProductStoreName: MSPTextView =
            itemView.findViewById<MSPTextView>(R.id.card_product_storeName)
        var cardProductCost: TextView = itemView.findViewById<TextView>(R.id.card_product_cost)
        var btnAddToCart: MSPButton = itemView.findViewById<MSPButton>(R.id.btn_add_to_cart_edit)
    }

}