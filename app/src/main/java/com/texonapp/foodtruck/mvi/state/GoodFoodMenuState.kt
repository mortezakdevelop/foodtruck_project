package com.texonapp.foodtruck.mvi.state

import com.texonapp.foodtruck.data.responseModel.BrandResponse
import com.texonapp.foodtruck.data.responseModel.CartResponse
import com.texonapp.foodtruck.repository.GoodFoodMenuRepository

sealed class GoodFoodMenuState {

    data class GetUserCart(
        val response: CartResponse?,
        val errorMessage: String?
    ) : GoodFoodMenuState()

    data class IncreaseFoodQuantity(
        val response: CartResponse?,
        val errorMessage: String?
    ) : GoodFoodMenuState()

    data class ReduceFoodQuantity(
        val response: CartResponse?,
        val errorMessage: String?
    ) : GoodFoodMenuState()

    object Idle : GoodFoodMenuState()


}