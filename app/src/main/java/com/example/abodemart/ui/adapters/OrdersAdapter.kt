package com.example.abodemart.ui.adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abodemart.R
import com.example.abodemart.database.OrderDatabase
import com.example.abodemart.models.CartData
import com.example.abodemart.utils.MSPTextView
import com.example.abodemart.utils.MSPTextViewBold

class OrdersAdapter(private val context: Context, private var orderDatabase: OrderDatabase) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val allOrderItem =
            LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return ViewHolder(allOrderItem)
    }

    override fun getItemCount(): Int {
        val allStoreOrders = orderDatabase.orderDao().getAllOrders()
        return allStoreOrders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val allStoreOrders = orderDatabase.orderDao().getAllOrders()
        val newOrdersList = allStoreOrders[position]

        holder.orderID.text = "Order# : ${newOrdersList.order_uid}"
        holder.orderCost.text = newOrdersList.orderTotalCost.removePrefix("Total Cost : ")
        holder.orderStatus.text = newOrdersList.orderStatus

        holder.orderDetailsLink.setOnClickListener {
            val customDialog = Dialog(holder.orderStatus.context)
            customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            customDialog.setContentView(R.layout.fragment_order_details)
            val tvOrderNumber = customDialog.findViewById<MSPTextView>(R.id.tv_order_number)
            val tvOrderStatus = customDialog.findViewById<MSPTextView>(R.id.tv_order_status)
            val tvOrderTotalCost = customDialog.findViewById<MSPTextView>(R.id.tv_order_total_cost)
            val tvOrderedDate = customDialog.findViewById<MSPTextView>(R.id.tv_order_ordered_date)
            val tvDeliveredDate = customDialog.findViewById<MSPTextView>(R.id.tv_order_delivered_date)
            val tvOrderItemsCount = customDialog.findViewById<MSPTextView>(R.id.tv_order_items_count)
            val tvOrderItemsOrdered = customDialog.findViewById<MSPTextView>(R.id.tv_order_items_ordered)



            tvOrderNumber.text = newOrdersList.order_uid
            tvOrderStatus.text = newOrdersList.orderStatus
            tvOrderTotalCost.text = newOrdersList.orderTotalCost.removePrefix("Total Cost : ")
            tvOrderedDate.text = newOrdersList.orderOrderedDate
            tvDeliveredDate.text = newOrdersList.orderDeliveredDate
            tvOrderItemsCount.text = newOrdersList.orderItemsCount
            tvOrderItemsOrdered.text = newOrdersList.orderedItems

            customDialog.setCanceledOnTouchOutside(true)
            customDialog.setCancelable(true)
            customDialog.show()
        }


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var orderID: MSPTextViewBold = itemView.findViewById<MSPTextViewBold>(R.id.tv_order_number)
        var orderCost: MSPTextViewBold = itemView.findViewById<MSPTextViewBold>(R.id.tv_order_total_cost)
        var orderStatus: MSPTextViewBold = itemView.findViewById<MSPTextViewBold>(R.id.tv_order_status)
        var orderDetailsLink: MSPTextViewBold = itemView.findViewById<MSPTextViewBold>(R.id.tv_order_details)
    }



}
