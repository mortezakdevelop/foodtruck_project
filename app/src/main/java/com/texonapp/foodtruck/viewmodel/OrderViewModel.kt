package com.texonapp.foodtruck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.texonapp.foodtruck.data.requestModel.SubmitOrderRequest
import com.texonapp.foodtruck.mvi.intent.OrderIntent
import com.texonapp.foodtruck.mvi.state.OrderState
import com.texonapp.foodtruck.repository.OrderDineInRepository

class OrderViewModel:MainViewModel() {
    private val _idleState = MutableLiveData<OrderState>(OrderState.Idle)

    private val _stateIntent: MutableLiveData<OrderIntent> = MutableLiveData()

    var dataState: LiveData<OrderState> = Transformations
        .switchMap(_stateIntent) { stateIntent ->
            stateIntent?.let {
                handleStateEvent(stateIntent)
            }
        }

    private fun handleStateEvent(stateIntent: OrderIntent): LiveData<OrderState> {
        return when (stateIntent) {
            is OrderIntent.SubmitOrder -> {
                OrderDineInRepository.SubmitOrders(stateIntent.request)
            }
            is OrderIntent.Idle ->{
                _idleState
            }
        }
    }

    private fun setStateEvent(intent: OrderIntent) {
        _stateIntent.value = intent
    }

    fun SubmitOrder(request: SubmitOrderRequest){
        setStateEvent(OrderIntent.SubmitOrder(request))
    }

    fun stateOff() {
        setStateEvent(OrderIntent.Idle)
    }
}