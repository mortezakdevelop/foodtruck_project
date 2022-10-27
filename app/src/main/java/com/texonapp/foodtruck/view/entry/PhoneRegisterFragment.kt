package com.texonapp.foodtruck.view.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hbb20.CountryCodePicker
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentPhoneRegisterBinding
import com.texonapp.foodtruck.mvi.UserState
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.util.USER_REGISTER_DATA
import com.texonapp.foodtruck.util.USER_REGISTER_DATA_R
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.UserViewModel

class PhoneRegisterFragment : BaseFragment() {
    private lateinit var binding: FragmentPhoneRegisterBinding
    private var fullPhoneNumber: String? = null
    private var countryCodePicker: CountryCodePicker? = null
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneRegisterBinding.inflate(inflater, container, false)
        binding.edtMobile.doAfterTextChanged {
            if(it.isNullOrEmpty())fullPhoneNumber = it.toString()
        }
        return binding.root
    }

    override fun listeners() {
        initData()
        binding.btnNext.setOnClickListener {
            if (checkFields()) {
                showProgress()
                viewModel.registerPhoneNumber(fullPhoneNumber!!)
            }
        }
    }

    private fun initData() {
        initMainToolbar(binding.toolbar)
        countryCodePicker = binding.ccp
        countryCodePicker?.registerCarrierNumberEditText(binding.edtMobile)
    }

    private fun checkFields(): Boolean {
        var flag = true
        if (!countryCodePicker!!.isValidFullNumber) {
            ToastUtil.showToast(R.string.enter_valid_number)
            flag = false
        } else {
            fullPhoneNumber = countryCodePicker!!.fullNumber
        }
        return flag
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is UserState.RegisterPhoneNumber -> {
                    hideProgress()
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }

                    dataState.response?.let {
                        val bundle = Bundle()
                        println("phone $fullPhoneNumber" )
                        bundle.putString(USER_REGISTER_DATA_R, fullPhoneNumber.toString())
                        findNavController().navigate(
                            R.id.action_phoneRegisterFragment_to_verifyCodeFragment,
                            bundle
                        )
                    }
                }
            }
        }
    }
}