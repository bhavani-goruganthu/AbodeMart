package com.example.abodemart.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.abodemart.R
import com.example.abodemart.models.CartData
import com.example.abodemart.ui.activities.CartFragment
import com.example.abodemart.ui.activities.HomeActivity
import com.example.abodemart.utils.MSPButton

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

        val textView: TextView = root.findViewById(R.id.text_home)
           textView.text = "This is Home Fragment"

        //set list
        (activity as HomeActivity).myCartItemsList = ArrayList()
        myListAdapter = MyListAdapter(activity as FragmentActivity,(activity as HomeActivity).myCartItemsList )

        // set find id
        root.findViewById<MSPButton>(R.id.btn_add_to_cart).setOnClickListener {
            (activity as HomeActivity).myCartItemsList.add(CartData("Tomato", "Whole Foods", "$ 30.00", "1"))
            myListAdapter.notifyDataSetChanged()
        }
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_cart, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            // when settings in clicked on dashboard fragment
            R.id.action_cart -> {
                val dialog = CartFragment()
                val fragmentManager = (activity as FragmentActivity).supportFragmentManager
                dialog.show(fragmentManager, "cartDialog")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}