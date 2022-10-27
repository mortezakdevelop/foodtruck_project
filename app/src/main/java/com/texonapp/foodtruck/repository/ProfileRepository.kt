package com.texonapp.foodtruck.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.texonapp.foodtruck.api.ApiSuccessResponse
import com.texonapp.foodtruck.api.GenericApiResponse
import com.texonapp.foodtruck.api.MyRetrofitBuilder
import com.texonapp.foodtruck.api.NetworkBoundResource
import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ProfileRequest
import com.texonapp.foodtruck.data.responseModel.CartResponse
import com.texonapp.foodtruck.data.responseModel.ProfileResponse
import com.texonapp.foodtruck.mvi.state.CartOrderState
import com.texonapp.foodtruck.mvi.state.ProfileState
import com.texonapp.foodtruck.util.MainApplication
import com.texonapp.foodtruck.util.TOKEN

object ProfileRepository {

    fun getUserCart(): LiveData<ProfileState> {
        return object : NetworkBoundResource<ProfileResponse, ProfileState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<ProfileResponse>) {
                result.value =
                    ProfileState.GetProfile(response.body, null)
            }

            override fun onReturnError(message: String) {
                result.value =
                    ProfileState.GetProfile(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<ProfileResponse>> {
                return MyRetrofitBuilder.apiService.getProfile(
                    MainApplication.sharedPreferenceUtil?.getStringValue(TOKEN)!!
                )
            }

        }.asLiveData()
    }

    fun postProfile(request: ProfileRequest): LiveData<ProfileState> {
        return object : NetworkBoundResource<ProfileResponse, ProfileState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<ProfileResponse>) {
                result.value =
                    ProfileState.GetProfile(response.body, null)
                Log.i("TAG","Su")
            }

            override fun onReturnError(message: String) {
                result.value =
                    ProfileState.GetProfile(null, message)
                Log.i("TAG","ERROR + ${message}")
            }

            override fun createCall(): LiveData<GenericApiResponse<ProfileResponse>> {
                Log.i("TAG","Req")
                return MyRetrofitBuilder.apiService.postProfile(
                    MainApplication.sharedPreferenceUtil?.getStringValue(TOKEN)!!,
                    request
                )
            }

        }.asLiveData()
    }
}