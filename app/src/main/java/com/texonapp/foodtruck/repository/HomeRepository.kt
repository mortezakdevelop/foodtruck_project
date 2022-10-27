package com.texonapp.foodtruck.repository

import androidx.lifecycle.LiveData
import com.texonapp.foodtruck.api.ApiSuccessResponse
import com.texonapp.foodtruck.api.GenericApiResponse
import com.texonapp.foodtruck.api.MyRetrofitBuilder
import com.texonapp.foodtruck.api.NetworkBoundResource
import com.texonapp.foodtruck.data.responseModel.HomePageResponse
import com.texonapp.foodtruck.mvi.HomeState
import com.texonapp.foodtruck.util.MainApplication.Companion.sharedPreferenceUtil
import com.texonapp.foodtruck.util.TOKEN

object HomeRepository {

    fun getHomePage(): LiveData<HomeState> {
        return object : NetworkBoundResource<HomePageResponse, HomeState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<HomePageResponse>) {
                result.value =
                    HomeState.GetHomePage(response.body, null)

            }

            override fun onReturnError(message: String) {
                result.value =
                    HomeState.GetHomePage(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<HomePageResponse>> {
                return MyRetrofitBuilder.apiService.getHomePage(
                    sharedPreferenceUtil?.getStringValue(TOKEN)!!
                )
            }

        }.asLiveData()
    }
}
