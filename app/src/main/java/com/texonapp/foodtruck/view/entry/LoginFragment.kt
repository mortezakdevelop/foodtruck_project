package com.texonapp.foodtruck.view.entry

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hbb20.CountryCodePicker
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentLoginBinding
import com.texonapp.foodtruck.mvi.UserState
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.util.USER_REGISTER_DATA
import com.texonapp.foodtruck.util.USER_REGISTER_DATA_R
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.UserViewModel

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
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
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.edtMobile.doAfterTextChanged {
            if(it.isNullOrEmpty())fullPhoneNumber = it.toString()
        }



//        binding.etInputNumber.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_phoneRegisterFragment)
//            val imgr =
//                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//
//        }

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // when we are in home fragment and back pressed button , finish fragment
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }
    override fun listeners() {

        initData()
        binding.btnLogin.setOnClickListener {
            if (checkFields()) {
                showProgress()
                viewModel.registerPhoneNumber(fullPhoneNumber!!)
            }
        }

//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
//            OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                requireActivity().finish()
//            }
//        })

//        binding.login.setOnClickListener {
//            if (checkLogin())
//                findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
//        }
//
//        binding.forgetPassword.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
//        }
//
//        binding.signUp.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
//        }
//
//        binding.btnGmail.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_continueWithGoogleFragment)
//        }
//
        binding.btnConnectGoogle.setOnClickListener {
            //findNavController().navigate(R.id.action_loginFragment_to_verifyCodeFragment)
        }
//    }
//
//    private fun checkLogin(): Boolean {
//        var flag = true
//        if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.text).matches()) {
//            binding.email.error = getString(R.string.Invalid_email_address)
//            flag = false
//        }
//        if (binding.email.text.toString() == "") {
//            binding.email.error = getString(R.string.please_fill_this_field)
//            flag = false
//        }
//        if (binding.password.text.toString() == "") {
//            binding.password.error = getString(R.string.please_fill_this_field)
//            flag = false
//        }
//        return flag
    }

    private fun initData() {
      //  initMainToolbar(binding.toolbar)
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
                        //ToastUtil.showToast(it)
                    }

                    dataState.response?.let {
                        val bundle = Bundle()
                        println("phone --------------------------------------------------- $fullPhoneNumber" )
                        bundle.putString(USER_REGISTER_DATA_R, fullPhoneNumber.toString())
                        findNavController().navigate(
                            R.id.action_loginFragment_to_verifyCodeFragment,
                            bundle
                        )
                    }
                }
            }
        }
    }
}