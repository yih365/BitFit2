package com.example.bitfit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddActivity: AppCompatActivity() {
    private lateinit var etFood: EditText
    private lateinit var etCal: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_page)
        etFood= findViewById(R.id.etFood)
        etCal = findViewById(R.id.etCal)
        submitBtn = findViewById(R.id.recordBtn)

        submitBtn.setOnClickListener { addItem() }
    }

    private fun addItem() {
        val foodName = etFood.text.toString()
        val cal = etCal.text.toString()

        let {
            lifecycleScope.launch(IO) {
                val list = ArrayList<FoodEntity>()
                list.add(FoodEntity(name = foodName, calories = cal))
                (application as FoodApplication).db.foodDao().insertAll(list)
            }
        }

        finish()
    }
}