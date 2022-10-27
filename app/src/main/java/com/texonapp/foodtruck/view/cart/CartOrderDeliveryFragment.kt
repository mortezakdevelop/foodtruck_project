package com.texonapp.foodtruck.view.cart

import android.R.string
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.DeliveryChargesAdapter
import com.texonapp.foodtruck.databinding.DialogDeliveryChargeBinding
import com.texonapp.foodtruck.databinding.DialogDiscountBinding
import com.texonapp.foodtruck.databinding.FragmentCartOrderDeliveryBinding
import com.texonapp.foodtruck.mvi.state.CartOrderState
import com.texonapp.foodtruck.util.SUB_TOTAL
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.CartOrderViewModel
import kotlinx.android.synthetic.main.fragment_dine_in_two.*


class CartOrderDeliveryFragment : BaseFragment() {

    private lateinit var binding: FragmentCartOrderDeliveryBinding
    private val viewModel: CartOrderViewModel by viewModels()

    private val addDiscountDialog: AlertDialog by lazy {
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogDiscountBinding.root)
        builder.create()
    }

    private val dialogDiscountBinding: DialogDiscountBinding by lazy {
        DialogDiscountBinding.inflate(layoutInflater, null, false)
    }

    private val deliveryChargeDialog: AlertDialog by lazy {
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogDeliveryChargeBinding.root)
        builder.create()
    }
    private val dialogDeliveryChargeBinding: DialogDeliveryChargeBinding by lazy {
        DialogDeliveryChargeBinding.inflate(layoutInflater, null, false)
    }

    private val deliveryChargesAdapter: DeliveryChargesAdapter by lazy {
        DeliveryChargesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val subTotal = it.getDouble(SUB_TOTAL)
            viewModel.setSubTotal(subTotal)
        }
        subscribeObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartOrderDeliveryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //get value from BrandPageFragment with sharedPreferences
        val sp:SharedPreferences = requireContext().getSharedPreferences("MyUserPrefs",Context.MODE_PRIVATE)
        val spinnerValue = sp.getString("typeFood","")
        binding.placeOrder.setOnClickListener {
            if (spinnerValue == "Dine in"){
                findNavController().navigate(R.id.action_cartOrderDeliveryFragment_to_dineInTwoFragment)
            }
        }
        return binding.root
    }

    override fun listeners() {
        initMainToolbar(binding.toolbar)
        binding.addDiscountCode.setOnClickListener {
            showDiscountCodeDialog()
        }

        binding.editDeliveryCharge.setOnClickListener {
            showDeliveryChargeDialog()
            viewModel.getDeliveryCharges()
        }
        }

    private fun subscribeObserver() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is CartOrderState.AddDiscountCode -> {
                    hideProgress()
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let {
                        viewModel.setDiscount(it.data?.amount!!)
                        if (addDiscountDialog.isShowing)
                            addDiscountDialog.dismiss()
                    }
                }

                is CartOrderState.GetDeliveryCharges -> {
                    hideProgress()
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let { data ->
                        if (deliveryChargeDialog.isShowing){
                            deliveryChargesAdapter.addAll(data.deliveryChargeItems!!)
                            setDeliveryChargeLoading(false)

                        }
                    }
                }
            }

            viewModel.stateOff()
        }
    }

    private fun showDiscountCodeDialog() {
        addDiscountDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addDiscountDialog.show()
        dialogDiscountBinding.close.setOnClickListener {
            addDiscountDialog.dismiss()
        }
        dialogDiscountBinding.check.setOnClickListener {
            if (dialogDiscountBinding.discountCode.text.isNullOrBlank())
                dialogDiscountBinding.discountCode.error =
                    getString(R.string.please_fill_this_field)
            else {
                showProgress()
                viewModel.addDiscountCode(dialogDiscountBinding.discountCode.text.toString())
            }
        }

    }

    private fun showDeliveryChargeDialog() {
        setDeliveryChargeLoading(true)
        initDeliveryChargeRecycler()
        deliveryChargeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        deliveryChargeDialog.show()
        dialogDeliveryChargeBinding.close.setOnClickListener {
            deliveryChargeDialog.dismiss()
        }
        dialogDeliveryChargeBinding.done.setOnClickListener {
            if (deliveryChargesAdapter.itemCount > 0) {
                viewModel.setDeliveryChargeService(deliveryChargesAdapter.getItemSelected())
                deliveryChargeDialog.dismiss()
            } else return@setOnClickListener
        }
    }

    private fun setDeliveryChargeLoading(loadingState: Boolean) {
        if (loadingState) {
            dialogDeliveryChargeBinding.deliveryChargeProgress.visibility = View.VISIBLE
            dialogDeliveryChargeBinding.deliveryChargeRecycler.visibility = View.GONE
        } else {
            dialogDeliveryChargeBinding.deliveryChargeProgress.visibility = View.GONE
            dialogDeliveryChargeBinding.deliveryChargeRecycler.visibility = View.VISIBLE
        }
    }

    private fun initDeliveryChargeRecycler() {
        val layoutManager = LinearLayoutManager(context)
        dialogDeliveryChargeBinding.deliveryChargeRecycler.layoutManager = layoutManager
        dialogDeliveryChargeBinding.deliveryChargeRecycler.adapter = deliveryChargesAdapter
    }
}