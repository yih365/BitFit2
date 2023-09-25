package com.example.bitfit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "FoodsAdapter"
class FoodsAdapter(private val context: Context, private val foods: List<Food>):
    RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val movie = foods[position]
        holder.bind(movie)
    }

    override fun getItemCount() = foods.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.name)
        private val tvCal = itemView.findViewById<TextView>(R.id.calories)

        fun bind(food: Food) {
            tvName.text = food.name
            tvCal.text = food.calorie
        }
    }
}
