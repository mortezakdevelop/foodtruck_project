package com.texonapp.foodtruck.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentSpinnerBinding
import com.texonapp.foodtruck.util.ToastUtil

class SpinnerFragment : Fragment() {
    lateinit var fragmentSpinnerBinding: FragmentSpinnerBinding
    var country = arrayOf("dine in", "delivery", "tack away")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentSpinnerBinding = FragmentSpinnerBinding.inflate(layoutInflater,container,false)
        return fragmentSpinnerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,country) as SpinnerAdapter
        fragmentSpinnerBinding.spinner.adapter = arrayAdapter
        fragmentSpinnerBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                ToastUtil.showToast("this is test")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                ToastUtil.showToast("this is test")
            }
        }
    }

}