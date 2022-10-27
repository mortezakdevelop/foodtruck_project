package com.texonapp.foodtruck.mvi.intent

import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ItemIdRequest


sealed class CartOrderIntent {

    object GetUserCart : CartOrderIntent()

    data class IncreaseFoodQuantity(val request: FoodIdRequest) : CartOrderIntent()

    data class ReduceFoodQuantity(val request: ItemIdRequest) : CartOrderIntent()

    data class RemoveFoodFromCart(val request: ItemIdRequest) : CartOrderIntent()

    object EmptyCart : CartOrderIntent()

    data class AddDiscountCode(val discountCode: String) : CartOrderIntent()

    object GetDeliveryCharges : CartOrderIntent()

    object Idle : CartOrderIntent()

}