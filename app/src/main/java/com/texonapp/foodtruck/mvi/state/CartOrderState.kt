package com.texonapp.foodtruck.mvi.state

import com.texonapp.foodtruck.data.responseModel.*


sealed class CartOrderState {

    data class GetUserCart(
        val response: CartResponse?,
        val errorMessage: String?
    ) : CartOrderState()

    data class IncreaseFoodQuantity(
        val response: CartResponse?,
        val errorMessage: String?
    ) : CartOrderState()

    data class ReduceFoodQuantity(
        val response: CartResponse?,
        val errorMessage: String?
    ) : CartOrderState()

    data class RemoveFoodFromCart(
        val response: CartResponse?,
        val errorMessage: String?
    ) : CartOrderState()

    data class EmptyCart(
        val response: MainResponse?,
        val errorMessage: String?
    ) : CartOrderState()

    data class AddDiscountCode(
        val response: DiscountCodeResponse?,
        val errorMessage: String?
    ) : CartOrderState()

    data class GetDeliveryCharges(
        val response: DeliveryChargeResponse?,
        val errorMessage: String?
    ) : CartOrderState()

    object Idle : CartOrderState()
}