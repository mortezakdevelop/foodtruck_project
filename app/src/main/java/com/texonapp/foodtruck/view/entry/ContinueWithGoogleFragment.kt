package com.texonapp.foodtruck.view.entry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.databinding.FragmentContinueWithGoogleBinding

class ContinueWithGoogleFragment : Fragment() {

    lateinit var fragmentContinueWithGoogleBinding: FragmentContinueWithGoogleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentContinueWithGoogleBinding = FragmentContinueWithGoogleBinding.inflate(layoutInflater,container,false)
        fragmentContinueWithGoogleBinding.toolbar.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return fragmentContinueWithGoogleBinding.root
    }
}