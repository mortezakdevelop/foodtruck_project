package com.texonapp.foodtruck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.texonapp.foodtruck.data.model.DeliveryChargeModel
import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
import com.texonapp.foodtruck.mvi.intent.CartOrderIntent
import com.texonapp.foodtruck.mvi.state.CartOrderState
import com.texonapp.foodtruck.repository.CartOrderRepository

class CartOrderViewModel : MainViewModel() {

    private val _deliveryChargeService: MutableLiveData<DeliveryChargeModel> = MutableLiveData()
    val deliveryChargeService: LiveData<DeliveryChargeModel> get() = _deliveryChargeService

    private val _subTotal: MutableLiveData<Double> = MutableLiveData(0.0)
    val subTotal: LiveData<Double> get() = _subTotal

    private val _deliveryCharge: MutableLiveData<Double> = MutableLiveData(0.0)
    val deliveryCharge: LiveData<Double> get() = _deliveryCharge

    private val _discount: MutableLiveData<Double> = MutableLiveData(0.0)
    val discount: LiveData<Double> get() = _discount

    private val _total: MutableLiveData<Double> = MutableLiveData(0.0)
    val total: LiveData<Double> get() = _total

    private val _idleState = MutableLiveData<CartOrderState>(CartOrderState.Idle)
    private val _stateIntent: MutableLiveData<CartOrderIntent> = MutableLiveData()

    var dataState: LiveData<CartOrderState> = Transformations
        .switchMap(_stateIntent) { stateIntent ->
            stateIntent?.let {
                handleStateEvent(stateIntent)
            }
        }

    private fun handleStateEvent(stateIntent: CartOrderIntent): LiveData<CartOrderState> {
        return when (stateIntent) {
            is CartOrderIntent.GetUserCart -> {
                CartOrderRepository.getUserCart()
            }
            is CartOrderIntent.IncreaseFoodQuantity -> {
                CartOrderRepository.increaseFoodQuantity(stateIntent.request)
            }
            is CartOrderIntent.ReduceFoodQuantity -> {
                CartOrderRepository.reduceFoodQuantity(stateIntent.request)
            }
            is CartOrderIntent.RemoveFoodFromCart -> {
                CartOrderRepository.removeFoodFromCart(stateIntent.request)
            }
            is CartOrderIntent.EmptyCart -> {
                CartOrderRepository.emptyCart()
            }
            is CartOrderIntent.AddDiscountCode -> {
                CartOrderRepository.addDiscountCode(stateIntent.discountCode)
            }
            is CartOrderIntent.GetDeliveryCharges -> {
                CartOrderRepository.getDeliveryCharge()
            }
            is CartOrderIntent.Idle -> {
                _idleState
            }
        }
    }

    private fun setStateEvent(intent: CartOrderIntent) {
        _stateIntent.value = intent
    }

    fun getUserCart() {
        setStateEvent(CartOrderIntent.GetUserCart)
    }

    fun increaseFoodQuantity(foodId: String) {
        setStateEvent(CartOrderIntent.IncreaseFoodQuantity(FoodIdRequest(foodId.toInt())))
    }

    fun reduceFoodQuantity(itemId: String) {
        setStateEvent(CartOrderIntent.ReduceFoodQuantity(ItemIdRequest(itemId.toInt())))
    }

    fun removeFoodFromCart(itemId: String) {
        setStateEvent(CartOrderIntent.RemoveFoodFromCart(ItemIdRequest(itemId.toInt())))
    }

    fun emptyCart() {
        setStateEvent(CartOrderIntent.EmptyCart)
    }

    fun addDiscountCode(discountCode: String) {
        setStateEvent(CartOrderIntent.AddDiscountCode(discountCode))
    }

    fun getDeliveryCharges() {
        setStateEvent(CartOrderIntent.GetDeliveryCharges)
    }

    fun stateOff() {
        setStateEvent(CartOrderIntent.Idle)
    }

    fun setSubTotal(subTotal: Double) {
        _subTotal.value = subTotal
        _total.value = subTotal + deliveryCharge.value!! + discount.value!!
    }

    fun setDeliveryChargeService(deliveryChargeModel: DeliveryChargeModel) {
        _deliveryChargeService.value = deliveryChargeModel
        setDeliveryCharge(deliveryChargeModel.deliveryCharge!!)
    }

    fun setDeliveryCharge(deliveryCharge: Double) {
        _deliveryCharge.value = deliveryCharge
        _total.value = subTotal.value!! + deliveryCharge + discount.value!!
    }

    fun setDiscount(discount: Double) {
        _discount.value = discount
        _total.value = subTotal.value!! + deliveryCharge.value!! + discount
    }
}

//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Transformations
//import com.texonapp.foodtruck.data.model.DeliveryChargeModel
//import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
//import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
//import com.texonapp.foodtruck.mvi.intent.CartOrderIntent
//import com.texonapp.foodtruck.mvi.state.CartOrderState
//import com.texonapp.foodtruck.repository.CartOrderRepository
//
//class CartOrderViewModel : MainViewModel() {
//
//    private val _deliveryChargeService: MutableLiveData<DeliveryChargeModel> = MutableLiveData()
//    val deliveryChargeService: LiveData<DeliveryChargeModel> get() = _deliveryChargeService
//
//    private val _subTotal: MutableLiveData<Double> = MutableLiveData(0.0)
//    val subTotal: LiveData<Double> get() = _subTotal
//
//    private val _deliveryCharge: MutableLiveData<Double> = MutableLiveData(0.0)
//    val deliveryCharge: LiveData<Double> get() = _deliveryCharge
//
//    private val _discount: MutableLiveData<Double> = MutableLiveData(0.0)
//    val discount: LiveData<Double> get() = _discount
//
//    private val _total: MutableLiveData<Double> = MutableLiveData(0.0)
//    val total: LiveData<Double> get() = _total
//
//    private val _idleState = MutableLiveData<CartOrderState>(CartOrderState.Idle)
//    private val _stateIntent: MutableLiveData<CartOrderIntent> = MutableLiveData()
//
//    var dataState: LiveData<CartOrderState> = Transformations
//        .switchMap(_stateIntent) { stateIntent ->
//            stateIntent?.let {
//                handleStateEvent(stateIntent)
//            }
//        }
//
//    private fun handleStateEvent(stateIntent: CartOrderIntent): LiveData<CartOrderState> {
//        return when (stateIntent) {
//            is CartOrderIntent.GetUserCart -> {
//                CartOrderRepository.getUserCart()
//            }
//            is CartOrderIntent.IncreaseFoodQuantity -> {
//                CartOrderRepository.increaseFoodQuantity(stateIntent.request)
//            }
//            is CartOrderIntent.ReduceFoodQuantity -> {
//                CartOrderRepository.reduceFoodQuantity(stateIntent.request)
//            }
//            is CartOrderIntent.RemoveFoodFromCart -> {
//                CartOrderRepository.removeFoodFromCart(stateIntent.request)
//            }
//            is CartOrderIntent.EmptyCart -> {
//                CartOrderRepository.emptyCart()
//            }
//            is CartOrderIntent.AddDiscountCode -> {
//                CartOrderRepository.addDiscountCode(stateIntent.discountCode)
//            }
//            is CartOrderIntent.GetDeliveryCharges -> {
//                CartOrderRepository.getDeliveryCharge()
//            }
//            is CartOrderIntent.Idle -> {
//                _idleState
//            }
//        }
//    }
//
//    private fun setStateEvent(intent: CartOrderIntent) {
//        _stateIntent.value = intent
//    }
//
//    fun getUserCart() {
//        setStateEvent(CartOrderIntent.GetUserCart)
//    }
//
//    fun increaseFoodQuantity(foodId: String) {
//        setStateEvent(CartOrderIntent.IncreaseFoodQuantity(FoodIdRequest(foodId)))
//    }
//
//    fun reduceFoodQuantity(itemId: String) {
//        setStateEvent(CartOrderIntent.ReduceFoodQuantity(ItemIdRequest(itemId)))
//    }
//
//    fun removeFoodFromCart(itemId: String) {
//        setStateEvent(CartOrderIntent.RemoveFoodFromCart(ItemIdRequest(itemId)))
//    }
//
//    fun emptyCart() {
//        setStateEvent(CartOrderIntent.EmptyCart)
//    }
//
//    fun addDiscountCode(discountCode: String) {
//        setStateEvent(CartOrderIntent.AddDiscountCode(discountCode))
//    }
//
//    fun getDeliveryCharges() {
//        setStateEvent(CartOrderIntent.GetDeliveryCharges)
//    }
//
//    fun stateOff() {
//        setStateEvent(CartOrderIntent.Idle)
//    }
//
//    fun setSubTotal(subTotal: Double) {
//        _subTotal.value = subTotal
//        _total.value = subTotal + deliveryCharge.value!! + discount.value!!
//    }
//
//    fun setDeliveryChargeService(deliveryChargeModel: DeliveryChargeModel) {
//        _deliveryChargeService.value = deliveryChargeModel
//        setDeliveryCharge(deliveryChargeModel.deliveryCharge!!)
//    }
//
//    fun setDeliveryCharge(deliveryCharge: Double) {
//        _deliveryCharge.value = deliveryCharge
//        _total.value = subTotal.value!! + deliveryCharge + discount.value!!
//    }
//
//    fun setDiscount(discount: Double) {
//        _discount.value = discount
//        _total.value = subTotal.value!! + deliveryCharge.value!! + discount
//    }
//}