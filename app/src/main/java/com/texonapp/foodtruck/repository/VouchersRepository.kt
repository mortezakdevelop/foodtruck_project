package com.texonapp.foodtruck.repository

import androidx.lifecycle.LiveData
import com.texonapp.foodtruck.api.ApiSuccessResponse
import com.texonapp.foodtruck.api.GenericApiResponse
import com.texonapp.foodtruck.api.MyRetrofitBuilder
import com.texonapp.foodtruck.api.NetworkBoundResource
import com.texonapp.foodtruck.data.responseModel.CartResponse
import com.texonapp.foodtruck.data.responseModel.VouchersResponse
import com.texonapp.foodtruck.mvi.state.VouchersState
import com.texonapp.foodtruck.util.MainApplication
import com.texonapp.foodtruck.util.TOKEN

object VouchersRepository {
    fun getVouchers(): LiveData<VouchersState> {
        return object : NetworkBoundResource<VouchersResponse, VouchersState>() {

            override fun onReturnError(message: String) {
                result.value =
                    VouchersState.GetVouchers(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<VouchersResponse>> {
                return MyRetrofitBuilder.apiService.getVouchers(
                    MainApplication.sharedPreferenceUtil?.getStringValue(TOKEN)!!
                )
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<VouchersResponse>) {
                result.value =
                    VouchersState.GetVouchers(response.body, null)
            }

        }.asLiveData()
    }
}