package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.OutputStreamWriter
import android.widget.EditText
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var itemlist = arrayListOf<String>()

        var adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_multiple_choice, itemlist
        )
        add.setOnClickListener {
            itemlist.add(task.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            task.text.clear()

            listView.setOnItemClickListener { adapterView, view, i, l ->
                android.widget.Toast.makeText(
                    this,
                    "You Selected the item --> " + itemlist.get(i),
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }

            delete.setOnClickListener {
                val position: SparseBooleanArray = listView.checkedItemPositions
                val count = listView.count
                var item = count - 1
                while (item >= 0) {
                    if (position.get(item)) {
                        adapter.remove(itemlist.get(item))
                    }
                    item--
                }
                position.clear()
                adapter.notifyDataSetChanged()
            }
        }
    }
}
