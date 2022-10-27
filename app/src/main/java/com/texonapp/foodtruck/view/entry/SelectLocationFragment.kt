package com.texonapp.foodtruck.view.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentSelectLocationBinding
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.view.base.BaseFragment

class SelectLocationFragment : BaseFragment() {

    private lateinit var binding: FragmentSelectLocationBinding
    private var zone: String? = null
    private var area: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectLocationBinding.inflate(inflater, container, false)
        val view = binding.root
        initMainToolbar(binding.toolbar)
        return view
    }

    override fun listeners() {
        binding.submit.setOnClickListener {
            if (checkFields())
                findNavController().navigate(R.id.action_selectLocationFragment_to_referralCodeFragment)
        }
    }

    private fun checkFields(): Boolean {
        var flag = true
        if (zone == null) {
            ToastUtil.showToast("Please select your zone")
            flag = false
        }
        if (area == null) {
            ToastUtil.showToast("Please select your area")
            flag = false
        }
        return flag
    }

}