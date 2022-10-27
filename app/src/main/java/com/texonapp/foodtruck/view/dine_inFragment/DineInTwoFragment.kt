package com.texonapp.foodtruck.view.dine_inFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.adapter.DineInAtAdapter
import com.texonapp.foodtruck.databinding.FragmentDineInTwoBinding
import com.texonapp.foodtruck.model.DineAtModel
import com.texonapp.foodtruck.model.UserAddressModel
import com.texonapp.foodtruck.util.publicTools.AdapterListener
import com.texonapp.foodtruck.view.base.BaseFragment

class DineInTwoFragment : BaseFragment() {

    lateinit var fragmentDineInTwoBinding: FragmentDineInTwoBinding
    lateinit var sp: SharedPreferences
    lateinit var sp2:SharedPreferences

    var order = arrayOf<String?>(
        "1", "2",
        "3", "4", "5", "6", "7", "8"
    )

    private val adapter: DineInAtAdapter by lazy {
        DineInAtAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentDineInTwoBinding =
            FragmentDineInTwoBinding.inflate(layoutInflater, container, false)
        return fragmentDineInTwoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            order
        ) as SpinnerAdapter
        fragmentDineInTwoBinding.spinner.adapter = arrayAdapter
        fragmentDineInTwoBinding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //save to sharedPreferences
                    sp = requireContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)
                    val prefEditor: SharedPreferences.Editor = sp.edit()
                    prefEditor.putString("typeFood",fragmentDineInTwoBinding.spinner.selectedItem.toString())
                    prefEditor.commit()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    override fun listeners() {
        initialData()
    }

    private fun initialData() {
        initRecycler()
        if (adapter.itemCount == 0)
            adapter.addAll(getUserAddresses())
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        fragmentDineInTwoBinding.rvDineAt.layoutManager = layoutManager
        fragmentDineInTwoBinding.rvDineAt.adapter = adapter

        adapter.listener = object : AdapterListener {
            override fun onItemClick(position: Int, obj: Any) {

            }
        }
    }

    private fun getUserAddresses(): ArrayList<DineAtModel> {
        val data = ArrayList<DineAtModel>()
        data.add(
            DineAtModel(
                "Breakfast",
                "at restaurant until 13:45 PM",
                true
            )
        )
        data.add(
            DineAtModel(
                "Lunch",
                "at restaurant until 16:45 PM",
            )
        )
        data.add(
            DineAtModel(
                "Dinner",
                "at restaurant until 23:45 PM",
            )
        )
        return data
    }
}
