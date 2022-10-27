package com.texonapp.foodtruck.mvi.state

import com.texonapp.foodtruck.data.responseModel.SubmitOrderResponse

sealed class OrderState {
    data class SubmitOrder(val response:SubmitOrderResponse? = null,val errorMessage:String? = null):OrderState()

    object  Idle: OrderState()
}