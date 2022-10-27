package com.texonapp.foodtruck.view.entry

import android.os.Bundle
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.poovam.pinedittextfield.PinField
import com.poovam.pinedittextfield.SquarePinField
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentVerifyCodeBinding
import com.texonapp.foodtruck.mvi.UserState
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.util.USER_REGISTER_DATA
import com.texonapp.foodtruck.util.USER_REGISTER_DATA_R
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.UserViewModel

class VerifyCodeFragment : BaseFragment() {
    private lateinit var binding: FragmentVerifyCodeBinding
    private lateinit var smsCode:String
    private lateinit var phoneNumber: String
    var pinField: EditText? = null
    private val viewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bun = this.arguments
        phoneNumber = bun?.getString(USER_REGISTER_DATA_R,"")?:""
        binding = FragmentVerifyCodeBinding.inflate(inflater, container, false)
        val view = binding.root
        pinField = binding.digitCode
        initMainToolbar(binding.toolbar)
        initialData()
        return view
    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            if (checkFields())
                viewModel.verify(pinField?.text.toString().toInt(),phoneNumber,"Android")
        }
    }

    private fun checkFields(): Boolean {
        if (binding.digitCode.text.toString() == "" && binding.digitCode.text.length != 6) {
            binding.digitCode.error = getString(R.string.please_fill_this_field)
            return false
        }
        return true
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is UserState.VerifyLogin -> {
                    hideProgress()
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }

                    dataState.response?.let {
                        if (it.data?.isNew!!){
                            viewModel.saveUserToken(it.data?.token!!)
                            viewModel.saveUserId(it.data?.user?.id!!)
                            findNavController().navigate(R.id.action_verifyCodeFragment_to_selectLocationFragment)
                        }else{
                            findNavController().navigate(R.id.action_verifyCodeFragment_to_mainActivity)
                        }

                    }
                }
            }
        }
    }

    private fun initialData() {
        smsCode = binding.digitCode.text.toString()
    }

}