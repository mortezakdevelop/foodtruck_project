package com.texonapp.foodtruck.view.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentReferralCodeBinding
import com.texonapp.foodtruck.view.base.BaseFragment

class ReferralCodeFragment : BaseFragment() {
    private lateinit var binding: FragmentReferralCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReferralCodeBinding.inflate(inflater, container, false)
        val view = binding.root
        initMainToolbar(binding.toolbar)
        return view
    }

    override fun listeners() {
        binding.submit.setOnClickListener {
            if (checkFields())
                findNavController().navigate(R.id.action_referralCodeFragment_to_mainActivity)
        }

        binding.skip.setOnClickListener {
            findNavController().navigate(R.id.action_referralCodeFragment_to_mainActivity)
        }
    }

    private fun checkFields(): Boolean {
        if (binding.digitCode.text.toString() == "" && binding.digitCode.text.length != 6) {
            binding.digitCode.error = getString(R.string.please_fill_this_field)
            return false
        }
        return true
    }
}