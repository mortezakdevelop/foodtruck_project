package com.texonapp.foodtruck.repository

import androidx.lifecycle.LiveData
import com.texonapp.foodtruck.api.ApiSuccessResponse
import com.texonapp.foodtruck.api.GenericApiResponse
import com.texonapp.foodtruck.api.MyRetrofitBuilder
import com.texonapp.foodtruck.api.NetworkBoundResource
import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
import com.texonapp.foodtruck.data.responseModel.CartResponse
import com.texonapp.foodtruck.mvi.state.GoodFoodMenuState
import com.texonapp.foodtruck.util.MainApplication
import com.texonapp.foodtruck.util.TOKEN

object GoodFoodMenuRepository {

    fun getUserCart(): LiveData<GoodFoodMenuState> {
        return object : NetworkBoundResource<CartResponse, GoodFoodMenuState>() {

            override fun onReturnError(message: String) {
                result.value =
                    GoodFoodMenuState.GetUserCart(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
                return MyRetrofitBuilder.apiService.getUserCart(
                    MainApplication.sharedPreferenceUtil?.getStringValue(TOKEN)!!
                )
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
                result.value =
                    GoodFoodMenuState.GetUserCart(response.body, null)            }

        }.asLiveData()
    }

    fun increaseFoodQuantity(request: FoodIdRequest): LiveData<GoodFoodMenuState> {
        return object : NetworkBoundResource<CartResponse, GoodFoodMenuState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
                result.value =
                    GoodFoodMenuState.IncreaseFoodQuantity(response.body, null)
            }

            override fun onReturnError(message: String) {
                result.value =
                    GoodFoodMenuState.IncreaseFoodQuantity(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
                return MyRetrofitBuilder.apiService.increaseFoodQuantity(
                    MainApplication.sharedPreferenceUtil?.getStringValue(TOKEN)!!,
                    request
                )
            }

        }.asLiveData()
    }

    fun reduceFoodQuantity(request: ItemIdRequest): LiveData<GoodFoodMenuState> {
        return object : NetworkBoundResource<CartResponse, GoodFoodMenuState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
                result.value =
                    GoodFoodMenuState.ReduceFoodQuantity(response.body, null)
            }

            override fun onReturnError(message: String) {
                result.value =
                    GoodFoodMenuState.ReduceFoodQuantity(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
                return MyRetrofitBuilder.apiService.reduceFoodQuantity(
                    MainApplication.sharedPreferenceUtil?.getStringValue(TOKEN)!!,
                    request
                )
            }

        }.asLiveData()
    }
}