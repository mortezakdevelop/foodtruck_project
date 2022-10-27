package com.texonapp.foodtruck.view.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.CartOrdersAdapter
import com.texonapp.foodtruck.data.model.OrderModel
import com.texonapp.foodtruck.databinding.FragmentCartBinding
import com.texonapp.foodtruck.mvi.state.CartOrderState
import com.texonapp.foodtruck.util.SUB_TOTAL
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.CartOrderViewModel

class CartFragment : BaseFragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartOrderViewModel by viewModels()

    private val adapter: CartOrdersAdapter by lazy {
        CartOrdersAdapter()
    }

    private var itemChangedQuantity: ItemChangedQuantity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObserver()
        viewModel.getUserCart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun listeners() {
        initMainToolbar(binding.toolbar)
        initRecycler()
        binding.placeOrder.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble(SUB_TOTAL, viewModel.subTotal.value ?: 0.0)
            findNavController().navigate(
                R.id.action_cartFragment_to_cartOrderDeliveryFragment,
                bundle
            )
        }
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        adapter.listener = object : CartOrdersAdapter.CartOrderListener {
            override fun increaseQuantity(position: Int, obj: OrderModel, newValue: Int) {
                itemChangedQuantity = ItemChangedQuantity(position, obj, newValue)
                viewModel.increaseFoodQuantity(obj.food!!.id!!)
            }

            override fun reduceQuantity(position: Int, obj: OrderModel, newValue: Int) {
                itemChangedQuantity = ItemChangedQuantity(position, obj, newValue)
                viewModel.reduceFoodQuantity(obj.id!!.toString())
            }

            override fun removeItem(position: Int, obj: OrderModel) {
                itemChangedQuantity = ItemChangedQuantity(position, obj, obj.quantity!!)
                viewModel.removeFoodFromCart(obj.id!!.toString())
            }
        }
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is CartOrderState.GetUserCart -> {
                    viewModel.setMainLoadingState(false)
                    dataState.errorMessage?.let {
                        Log.i("TAG","ERROR")
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let {
                        adapter.addAll(it.data?.orderItems!!)
                    }
                }
                is CartOrderState.IncreaseFoodQuantity -> {
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                        adapter.changeItemQuantity(
                            itemChangedQuantity?.position!!,
                            itemChangedQuantity?.obj?.quantity!!
                        )
                    }
                    dataState.response?.let {
                        adapter.changeItemQuantity(
                            itemChangedQuantity?.position!!,
                            itemChangedQuantity?.newValue!!
                        )
                    }
                }
                is CartOrderState.ReduceFoodQuantity -> {
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                        adapter.changeItemQuantity(
                            itemChangedQuantity?.position!!,
                            itemChangedQuantity?.obj?.quantity!!
                        )
                    }
                    dataState.response?.let {
                        adapter.changeItemQuantity(
                            itemChangedQuantity?.position!!,
                            itemChangedQuantity?.newValue!!
                        )
                    }
                }
                is CartOrderState.RemoveFoodFromCart -> {
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let {
                        adapter.removeItem(itemChangedQuantity?.position!!)
                    }
                }

                is CartOrderState.EmptyCart -> {
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let {

                    }
                }

            }

            calculateFactor(adapter.getItems())
            viewModel.stateOff()
        }
    }

    private fun calculateFactor(cartItems: ArrayList<OrderModel>) {
        var subTotal = 0.0
        for (item in cartItems) {
            subTotal += (item.quantity ?: 0) * (item.food?.price?.toDouble() ?: 0.0)
        }
        viewModel.setSubTotal(subTotal)
    }

    override fun onResume() {
        super.onResume()
        hideBottomNavigation()
    }

    override fun onStop() {
        super.onStop()
        showBottomNavigation()
    }

}

data class ItemChangedQuantity(val position: Int, val obj: OrderModel, val newValue: Int)

//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.texonapp.foodtruck.R
//import com.texonapp.foodtruck.adapter.CartOrdersAdapter
//import com.texonapp.foodtruck.data.model.OrderModel
//import com.texonapp.foodtruck.databinding.FragmentCartBinding
//import com.texonapp.foodtruck.mvi.state.CartOrderState
//import com.texonapp.foodtruck.util.SUB_TOTAL
//import com.texonapp.foodtruck.util.ToastUtil
//import com.texonapp.foodtruck.view.base.BaseFragment
//import com.texonapp.foodtruck.viewmodel.CartOrderViewModel
//
//class CartFragment : BaseFragment() {
//
//    private lateinit var binding: FragmentCartBinding
//    private val viewModel: CartOrderViewModel by viewModels()
//
//    private val adapter: CartOrdersAdapter by lazy {
//        CartOrdersAdapter()
//    }
//
//    private var itemChangedQuantity: ItemChangedQuantity? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        subscribeObserver()
//        viewModel.getUserCart()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentCartBinding.inflate(inflater, container, false)
//        binding.lifecycleOwner = this
//        binding.viewModel = viewModel
//        return binding.root
//    }
//
//    override fun listeners() {
//        initMainToolbar(binding.toolbar)
//        initRecycler()
//        binding.placeOrder.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putDouble(SUB_TOTAL, viewModel.subTotal.value ?: 0.0)
//            findNavController().navigate(
//                R.id.action_cartFragment_to_cartOrderDeliveryFragment,
//                bundle
//            )
//        }
//    }
//
//    private fun initRecycler() {
//        val layoutManager = LinearLayoutManager(context)
//        binding.recyclerView.layoutManager = layoutManager
//        binding.recyclerView.adapter = adapter
//
//        adapter.listener = object : CartOrdersAdapter.CartOrderListener {
//            override fun increaseQuantity(position: Int, obj: OrderModel, newValue: Int) {
//                itemChangedQuantity = ItemChangedQuantity(position, obj, newValue)
//                viewModel.increaseFoodQuantity(obj.food!!.id!!)
//            }
//
//            override fun reduceQuantity(position: Int, obj: OrderModel, newValue: Int) {
//                itemChangedQuantity = ItemChangedQuantity(position, obj, newValue)
//                viewModel.reduceFoodQuantity(obj.id!!)
//            }
//
//            override fun removeItem(position: Int, obj: OrderModel) {
//                itemChangedQuantity = ItemChangedQuantity(position, obj, obj.quantity!!)
//                viewModel.removeFoodFromCart(obj.id!!)
//            }
//        }
//    }
//
//    private fun subscribeObserver() {
//        viewModel.dataState.observe(this) { dataState ->
//            when (dataState) {
//                is CartOrderState.GetUserCart -> {
//                    viewModel.setMainLoadingState(false)
//                    dataState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                    }
//                    dataState.response?.let {
//                        adapter.addAll(it.data?.orderItems!!)
//                    }
//                }
//                is CartOrderState.IncreaseFoodQuantity -> {
//                    dataState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                        adapter.changeItemQuantity(
//                            itemChangedQuantity?.position!!,
//                            itemChangedQuantity?.obj?.quantity!!
//                        )
//                    }
//                    dataState.response?.let {
//                        adapter.changeItemQuantity(
//                            itemChangedQuantity?.position!!,
//                            itemChangedQuantity?.newValue!!
//                        )
//                    }
//                }
//                is CartOrderState.ReduceFoodQuantity -> {
//                    dataState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                        adapter.changeItemQuantity(
//                            itemChangedQuantity?.position!!,
//                            itemChangedQuantity?.obj?.quantity!!
//                        )
//                    }
//                    dataState.response?.let {
//                        adapter.changeItemQuantity(
//                            itemChangedQuantity?.position!!,
//                            itemChangedQuantity?.newValue!!
//                        )
//                    }
//                }
//                is CartOrderState.RemoveFoodFromCart -> {
//                    dataState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                    }
//                    dataState.response?.let {
//                        adapter.removeItem(itemChangedQuantity?.position!!)
//                    }
//                }
//
//                is CartOrderState.EmptyCart -> {
//                    dataState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                    }
//                    dataState.response?.let {
//
//                    }
//                }
//
//            }
//
//            calculateFactor(adapter.getItems())
//            viewModel.stateOff()
//        }
//    }
//
//    private fun calculateFactor(cartItems: ArrayList<OrderModel>) {
//        var subTotal = 0.0
//        for (item in cartItems) {
//            subTotal += (item.quantity ?: 0) * (item.food?.price ?: 0.0)
//        }
//        viewModel.setSubTotal(subTotal)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        hideBottomNavigation()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        showBottomNavigation()
//    }
//
//}

//data class ItemChangedQuantity(val position: Int, val obj: OrderModel, val newValue: Int)