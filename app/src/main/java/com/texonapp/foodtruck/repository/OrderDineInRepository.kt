package com.texonapp.foodtruck.repository

import androidx.lifecycle.LiveData
import com.texonapp.foodtruck.api.ApiSuccessResponse
import com.texonapp.foodtruck.api.GenericApiResponse
import com.texonapp.foodtruck.api.MyRetrofitBuilder
import com.texonapp.foodtruck.api.NetworkBoundResource
import com.texonapp.foodtruck.data.requestModel.SubmitOrderRequest
import com.texonapp.foodtruck.data.responseModel.SubmitOrderResponse
import com.texonapp.foodtruck.mvi.state.OrderState
import com.texonapp.foodtruck.util.MainApplication
import com.texonapp.foodtruck.util.TOKEN

object OrderDineInRepository {

    fun SubmitOrders(request: SubmitOrderRequest): LiveData<OrderState> {
        return object : NetworkBoundResource<SubmitOrderResponse, OrderState>() {
            override fun onReturnError(message: String) {
                result.value = OrderState.SubmitOrder(null,message)
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<SubmitOrderResponse>) {
                result.value = OrderState.SubmitOrder(response.body,null)
            }

            override fun createCall(): LiveData<GenericApiResponse<SubmitOrderResponse>> {
                return MyRetrofitBuilder.apiService.submitOrder(
                    MainApplication.sharedPreferenceUtil?.getStringValue(TOKEN)!!,
                    request
                )
            }


        }.asLiveData()
    }
}