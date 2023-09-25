package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var rvFoods: RecyclerView
    private lateinit var addBtn: Button
    private var entries: MutableList<Food> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFoods = findViewById(R.id.rvFoods)
        addBtn = findViewById(R.id.addBtn)

//        foodsList = ArrayList()
        // Set as saved
        // Add new

        // Create adapter passing in the list
        val adapter = FoodsAdapter(this, entries)
        // Attach the adapter to the RecyclerView to populate items
        rvFoods.adapter = adapter
        // Set layout manager to position the items
        rvFoods.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao()
                .getAll().collect { databaseList ->
                    databaseList.map { entity ->
                        FoodEntity(
                            entity.id,
                            entity.name,
                            entity.calories,
                        )
                    }.also { mappedList ->
                        val foodEntries = mappedList.map { foodEntity ->
                            Food(
                                foodEntity.name,
                                foodEntity.calories,
                            )
                        }
                        entries.clear()
                        entries.addAll(foodEntries)
                        rvFoods.adapter?.notifyDataSetChanged()
                    }
                }
        }

        addBtn.setOnClickListener { addBtnPress() }
    }

    private fun addBtnPress() {
        val intent = Intent(this, AddActivity::class.java)
        this.startActivity(intent)
    }
}