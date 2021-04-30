package com.example.abodemart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.abodemart.R
import com.example.abodemart.ui.activities.SearchActivity
import com.example.abodemart.ui.activities.StoreActivity
import com.example.abodemart.ui.adapters.MyListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<CardView>(R.id.cv_costco).setOnClickListener {
            val intent = Intent(this@HomeFragment.context, StoreActivity::class.java)
            intent.putExtra("Costco", true)
            startActivity(intent)
        }
        view.findViewById<CardView>(R.id.cv_whole_foods).setOnClickListener {
            val intent = Intent(this@HomeFragment.context, StoreActivity::class.java)
            intent.putExtra("Whole Foods", true)
            startActivity(intent)
        }
        view.findViewById<CardView>(R.id.cv_nib).setOnClickListener {
            val intent = Intent(this@HomeFragment.context, StoreActivity::class.java)
            intent.putExtra("New India Bazar", true)
            startActivity(intent)
        }
        view.findViewById<FloatingActionButton>(R.id.fab_search).setOnClickListener {
            val intent = Intent(this@HomeFragment.context, SearchActivity::class.java)
            startActivity(intent)
        }
    }

}