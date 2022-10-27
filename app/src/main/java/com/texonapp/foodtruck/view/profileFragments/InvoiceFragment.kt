package com.texonapp.foodtruck.view.profileFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.adapter.ReOrderFoodAdapter
import com.texonapp.foodtruck.databinding.FragmentInvoiceBinding
import com.texonapp.foodtruck.model.FoodModel
import com.texonapp.foodtruck.util.publicTools.AdapterListener
import com.texonapp.foodtruck.view.base.BaseFragment

class InvoiceFragment : BaseFragment() {

    private lateinit var binding: FragmentInvoiceBinding
    private val adapter: ReOrderFoodAdapter by lazy {
        ReOrderFoodAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInvoiceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun listeners() {
        initMainToolbar(binding.toolbar)
        initialData()
    }

    private fun initialData() {
        initRecycler()
        adapter.addAll(getFoods())
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        adapter.listener = object : AdapterListener {
            override fun onItemClick(position: Int, obj: Any) {

            }
        }
    }

    private fun getFoods(): ArrayList<FoodModel> {
        val data = ArrayList<FoodModel>()
        data.add(FoodModel)
        data.add(FoodModel)
        data.add(FoodModel)
        return data
    }
}