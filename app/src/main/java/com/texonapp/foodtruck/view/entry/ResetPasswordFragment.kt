package com.texonapp.foodtruck.view.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentResetPasswordBinding
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.view.base.BaseFragment

class ResetPasswordFragment : BaseFragment() {

    private lateinit var binding: FragmentResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        initMainToolbar(binding.toolbar)
        return view
    }

    override fun listeners() {
        binding.submit.setOnClickListener {
            if (checkFields())
                findNavController().navigate(R.id.action_resetPasswordFragment_to_mainActivity)
        }
    }

    private fun checkFields(): Boolean {
        var flag = true
        if (binding.confirmPassword.text.toString() == "") {
            binding.confirmPassword.error = getString(R.string.please_fill_this_field)
            flag = false
        }
        if (binding.password.text.toString() == "") {
            binding.password.error = getString(R.string.please_fill_this_field)
            flag = false
        }
        if (binding.password.text.toString() != binding.confirmPassword.text.toString()) {
            ToastUtil.showToast(getString(R.string.passwords_do_not_match))
            flag = false
        }
        return flag
    }

}