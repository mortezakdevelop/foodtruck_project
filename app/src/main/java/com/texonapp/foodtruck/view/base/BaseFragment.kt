package com.texonapp.foodtruck.view.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.MainActivity
import com.texonapp.foodtruck.databinding.DialogProgressBinding
import com.texonapp.foodtruck.databinding.ToolbarMainBinding
import dagger.hilt.android.AndroidEntryPoint


abstract class BaseFragment : Fragment() {

    private var progressDialog: Dialog? = null
    var radius = 20f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()
    }

    fun showProgress() {
        context?.let {
            progressDialog = Dialog(it)
            val dialogBinding = DialogProgressBinding.inflate(layoutInflater, null, false)
            progressDialog!!.setContentView(dialogBinding.root)
            progressDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressDialog!!.setCanceledOnTouchOutside(false)
            progressDialog!!.show()
        }
    }

    fun hideProgress() {
        progressDialog?.dismiss()
    }

    fun initMainToolbar(toolbarBinding: ToolbarMainBinding) {
        toolbarBinding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun showBottomNavigation() {
        (activity as MainActivity).showBottomNavigation()
    }

    fun hideBottomNavigation() {
        (activity as MainActivity).hideBottomNavigation()
    }

    abstract fun listeners()
}