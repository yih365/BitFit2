package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private lateinit var rvFoods: RecyclerView
    private lateinit var addBtn: Button
    private var entries: MutableList<Food> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        rvFoods = view.findViewById(R.id.rvFoods)

        // Create adapter passing in the list
        val adapter = FoodsAdapter(view.context, entries)
        // Attach the adapter to the RecyclerView to populate items
        rvFoods.adapter = adapter
        // Set layout manager to position the items
        rvFoods.layoutManager = LinearLayoutManager(view.context)

        lifecycleScope.launch {
            (requireActivity().application as FoodApplication).db.foodDao()
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
        return view
    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}