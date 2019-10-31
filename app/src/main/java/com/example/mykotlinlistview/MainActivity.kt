package com.example.mykotlinlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1 Read from file
        val dataList = MyData.getDataFromFile("recipes.json", this)

        // 2 Get list of items to show
        val listItems = arrayOfNulls<String>(dataList.size)

        // 3 Show title of each individual items
        for (i in 0 until dataList.size) {
            val data  = dataList[i]
            listItems[i] = data.title
        }

        // 4 Bind data to listView
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        my_list_view.adapter = adapter

        // on item selected
        my_list_view.setOnItemClickListener { _, _, position, _ ->

            val selectedItem = listItems[position]

            Log.d(TAG, "$selectedItem selected ")

        }
    }
}
