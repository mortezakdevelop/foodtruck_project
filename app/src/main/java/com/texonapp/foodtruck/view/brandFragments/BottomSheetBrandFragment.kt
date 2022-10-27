package com.texonapp.foodtruck.view.brandFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentBottomSheetBrandBinding
import com.texonapp.foodtruck.util.ToastUtil


class BottomSheetBrandFragment : Fragment() {

    lateinit var fragmentBottomSheetBrandBinding: FragmentBottomSheetBrandBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBottomSheetBrandBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_bottom_sheet_brand,
            container,
            false
        )

        return fragmentBottomSheetBrandBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}