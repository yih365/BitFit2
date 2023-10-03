package com.example.bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFragment : Fragment() {
    private lateinit var etFood: EditText
    private lateinit var etCal: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        etFood= view.findViewById(R.id.etFood)
        etCal = view.findViewById(R.id.etCal)
        submitBtn = view.findViewById(R.id.recordBtn)

        submitBtn.setOnClickListener {
            val foodName = etFood.text.toString()
            val cal = etCal.text.toString()

            let {
                lifecycleScope.launch(Dispatchers.IO) {
                    val list = ArrayList<FoodEntity>()
                    list.add(FoodEntity(name = foodName, calories = cal))
                    (requireActivity().application as FoodApplication).db.foodDao().insertAll(list)
                }
            }
        }

        return view
    }

    companion object {
        fun newInstance(): AddFragment {
            return AddFragment()
        }
    }
}