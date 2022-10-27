package com.texonapp.foodtruck.view.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentInvitationBinding

class InvitationFragment : Fragment() {

    lateinit var fragmentInvitationBinding: FragmentInvitationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentInvitationBinding = FragmentInvitationBinding.inflate(layoutInflater,container,false)
        return fragmentInvitationBinding.root
    }
}