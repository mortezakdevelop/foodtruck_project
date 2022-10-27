package com.texonapp.foodtruck.view.profileFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.adapter.OrdersAdapter
import com.texonapp.foodtruck.databinding.FragmentOrdersBinding
import com.texonapp.foodtruck.model.OrderModel
import com.texonapp.foodtruck.view.base.BaseFragment


class OrdersFragment : BaseFragment() {

    private lateinit var binding: FragmentOrdersBinding
    private val adapter: OrdersAdapter by lazy {
        OrdersAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun listeners() {
        initMainToolbar(binding.toolbar)
        initialData()
    }

    private fun initialData() {
        initRecycler()
        adapter.addAll(getOrders())
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        adapter.listener = object : OrdersAdapter.OrderListener {
            override fun buyAgain(position: Int, obj: OrderModel) {

            }
            override fun orderTracking(position: Int, obj: OrderModel) {

            }
        }
    }

    private fun getOrders(): ArrayList<OrderModel> {
        val data = ArrayList<OrderModel>()
        data.add(OrderModel)
        data.add(OrderModel)
        data.add(OrderModel)
        return data
    }
}