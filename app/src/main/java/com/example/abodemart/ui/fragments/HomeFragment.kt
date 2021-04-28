package com.example.abodemart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.example.abodemart.R
import com.example.abodemart.ui.activities.StoreActivity
import com.example.abodemart.ui.adapters.MyListAdapter
import com.example.abodemart.database.CartDatabase

class HomeFragment : Fragment() {

    private lateinit var myListAdapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // if we want to use the option menu in fragment we need to add it
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // room db
//        val cartDatabase = Room.databaseBuilder(
//            activity as FragmentActivity, CartDatabase::class.java, "cart_database"
//        ).allowMainThreadQueries().build()

        // get all cart Items
//        val allItems = cartDatabase.cartDao().getAllItems()
//        myListAdapter = MyListAdapter(activity as FragmentActivity, allItems , root)

        root.findViewById<CardView>(R.id.cv_costco).setOnClickListener {
            val intent = Intent(this@HomeFragment.context, StoreActivity::class.java)
            intent.putExtra("Costco", true)
            startActivity(intent)
        }
        root.findViewById<CardView>(R.id.cv_whole_foods).setOnClickListener {
            val intent = Intent(this@HomeFragment.context, StoreActivity::class.java)
            intent.putExtra("Whole Foods", true)
            startActivity(intent)
        }
        root.findViewById<CardView>(R.id.cv_nib).setOnClickListener {
            val intent = Intent(this@HomeFragment.context, StoreActivity::class.java)
            intent.putExtra("New India Bazar", true)
            startActivity(intent)
        }
        return root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.home_cart, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        when (id) {
//            // when settings in clicked on dashboard fragment
//            R.id.action_cart -> {
//                val dialog = CartActivity()
//                val fragmentManager = (activity as FragmentActivity).supportFragmentManager
//                (activity as HomeActivity).dialog.show(fragmentManager, "cartDialog")
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}