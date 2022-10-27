package com.texonapp.foodtruck.view.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.adapter.VouchersAdapter
import com.texonapp.foodtruck.databinding.FragmentVouchersBinding
import com.texonapp.foodtruck.mvi.state.VouchersState
import com.texonapp.foodtruck.util.MainApplication
import com.texonapp.foodtruck.util.TOKEN
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.VouchersViewModel

class VouchersFragment : BaseFragment() {

    lateinit var fragmentVouchersBinding: FragmentVouchersBinding
    private val viewModel: VouchersViewModel by viewModels()

    private val vouchersAdapter by lazy {
        VouchersAdapter()
    }

    override fun listeners() {
        initVouchersAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.sharedPreferenceUtil?.saveValue(TOKEN, "Bearer 4|ZZYgCq90KBdO7s6aFvE9UyFd24sr8zbHwdR8eNf0")
        subscribeObserver()
        viewModel.getVouchers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentVouchersBinding = FragmentVouchersBinding.inflate(layoutInflater,container,false)
        return fragmentVouchersBinding.root
    }

    private fun initVouchersAdapter() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragmentVouchersBinding.rvVouchers.layoutManager = layoutManager
        fragmentVouchersBinding.rvVouchers.adapter = vouchersAdapter
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is VouchersState.GetVouchers -> {
                    viewModel.setMainLoadingState(false)
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let {
                        println("vouchers response ----------------------------------------------------------------- ${it.data!!.promotions}")
                        vouchersAdapter.addAll(it.data!!.promotions!!)
                    }
                }
            }
            //viewModel.stateOff()
        }
    }
}
