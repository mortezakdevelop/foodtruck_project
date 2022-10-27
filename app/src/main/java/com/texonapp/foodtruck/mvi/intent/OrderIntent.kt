package com.texonapp.foodtruck.mvi.intent

import com.texonapp.foodtruck.data.requestModel.SubmitOrderRequest

sealed class OrderIntent {

    data class SubmitOrder(val request: SubmitOrderRequest):OrderIntent()
    object Idle : OrderIntent()
}
