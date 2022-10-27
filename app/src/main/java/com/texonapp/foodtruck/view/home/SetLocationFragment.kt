package com.texonapp.foodtruck.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentSetLocationBinding

class SetLocationFragment : Fragment() {

    lateinit var fragmentSetLocationBinding: FragmentSetLocationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentSetLocationBinding = FragmentSetLocationBinding.inflate(layoutInflater,container,false)
        return fragmentSetLocationBinding.root
    }
}