package com.texonapp.foodtruck.view.entry

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentSignUpBinding
import com.texonapp.foodtruck.util.USER_REGISTER_DATA
import com.texonapp.foodtruck.view.base.BaseFragment

class SignUpFragment : BaseFragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var phoneNumber:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        initMainToolbar(binding.toolbar)
        return view
    }

    override fun listeners() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            }
        })
//        binding.signup.setOnClickListener {
//            if (checkFields())
//                findNavController().navigate(R.id.action_signUpFragment_to_selectLocationFragment)
//        }

        binding.signIn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun checkFields(): Boolean {
        var flag = true
        if (binding.username.text.toString() == "") {
            binding.username.error = getString(R.string.please_fill_this_field)
            flag = false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.text).matches()) {
            binding.email.error = getString(R.string.Invalid_email_address)
            flag = false
        }
        if (binding.email.text.toString() == "") {
            binding.email.error = getString(R.string.please_fill_this_field)
            flag = false
        }
        if (binding.password.text.toString() == "") {
            binding.password.error = getString(R.string.please_fill_this_field)
            flag = false
        }
        if (binding.idCard.text.toString() == "") {
            binding.idCard.error = getString(R.string.please_fill_this_field)
            flag = false
        }
        return flag
    }

}