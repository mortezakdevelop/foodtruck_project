package com.texonapp.foodtruck.view.profileFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.texonapp.foodtruck.databinding.FragmentCreditCardBinding
import com.texonapp.foodtruck.view.base.BaseFragment

class CreditCardFragment : BaseFragment() {
    private lateinit var binding: FragmentCreditCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditCardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun listeners() {
        initMainToolbar(binding.toolbar)
    }
}