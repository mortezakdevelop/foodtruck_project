package com.texonapp.foodtruck.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.texonapp.foodtruck.adapter.IncreaseInventoryAdapter
import com.texonapp.foodtruck.adapter.ItemClickListener
import com.texonapp.foodtruck.databinding.FragmentInventoryBinding
import com.texonapp.foodtruck.model.IncreaseInventoryModel


class InventoryFragment : Fragment(), ItemClickListener {

    lateinit var fragmentInventoryBinding: FragmentInventoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentInventoryBinding =
            FragmentInventoryBinding.inflate(layoutInflater, container, false)
        setupIncreaseInventoryAdapter()
        return fragmentInventoryBinding.root
    }

    private fun setupIncreaseInventoryAdapter() {
        val data: ArrayList<IncreaseInventoryModel> = ArrayList()
        data.add(IncreaseInventoryModel("$1000"))
        data.add(IncreaseInventoryModel("$2000"))
        data.add(IncreaseInventoryModel("$3000"))
        data.add(IncreaseInventoryModel("$4000"))

        fragmentInventoryBinding.rvIncreaseInventory.layoutManager =
            GridLayoutManager(requireContext(), 2)
        fragmentInventoryBinding.rvIncreaseInventory.adapter = IncreaseInventoryAdapter(data,this)
    }
}