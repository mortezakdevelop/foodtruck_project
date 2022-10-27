package com.texonapp.foodtruck.view.brandFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.texonapp.foodtruck.databinding.FragmentCommentBinding

class CommentFragment : Fragment() {

    lateinit var fragmentCommnetBinding:FragmentCommentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentCommnetBinding = FragmentCommentBinding.inflate(layoutInflater,container,false)
        return fragmentCommnetBinding.root
    }
}