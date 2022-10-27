package com.texonapp.foodtruck.view.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentForgetPasswordBinding
import com.texonapp.foodtruck.view.base.BaseFragment


class ForgetPasswordFragment : BaseFragment() {
    private lateinit var binding: FragmentForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        initMainToolbar(binding.toolbar)
        return view
    }

    override fun listeners() {
        binding.submit.setOnClickListener {
            findNavController().navigate(R.id.action_forgetPasswordFragment_to_verifyCodeFragment)
        }

        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_forgetPasswordFragment_to_signUpFragment)
        }
    }
}