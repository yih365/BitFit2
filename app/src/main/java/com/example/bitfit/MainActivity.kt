package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var foodsList: MutableList<Food>
    private lateinit var rvFoods: RecyclerView
    private lateinit var addBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFoods = findViewById(R.id.rvFoods)
        addBtn = findViewById(R.id.addBtn)

        foodsList = ArrayList()
        // Set as saved
        // Add new

        // Create adapter passing in the list
        val adapter = FoodsAdapter(this, foodsList)
        // Attach the adapter to the RecyclerView to populate items
        rvFoods.adapter = adapter
        // Set layout manager to position the items
        rvFoods.layoutManager = LinearLayoutManager(this)

        addBtn.setOnClickListener { addBtnPress() }
    }

    private fun addBtnPress() {
        val intent = Intent(this, AddActivity::class.java)
        this.startActivity(intent)
    }
}