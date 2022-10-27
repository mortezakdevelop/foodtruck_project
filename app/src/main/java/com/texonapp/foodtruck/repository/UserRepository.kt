package com.texonapp.foodtruck.repository

import androidx.lifecycle.LiveData
import com.texonapp.foodtruck.api.ApiSuccessResponse
import com.texonapp.foodtruck.api.GenericApiResponse
import com.texonapp.foodtruck.api.MyRetrofitBuilder
import com.texonapp.foodtruck.api.NetworkBoundResource
import com.texonapp.foodtruck.data.requestModel.RegisterPhoneRequest
import com.texonapp.foodtruck.data.requestModel.VerifyLoginRequest
import com.texonapp.foodtruck.data.responseModel.MainResponse
import com.texonapp.foodtruck.data.responseModel.UserProfileResponse
import com.texonapp.foodtruck.data.responseModel.VerifyResponse
import com.texonapp.foodtruck.mvi.UserState
import com.texonapp.foodtruck.util.MainApplication.Companion.sharedPreferenceUtil
import com.texonapp.foodtruck.util.SharedPreferenceUtil
import com.texonapp.foodtruck.util.TOKEN

object UserRepository {

    fun registerPhoneNumber(request: RegisterPhoneRequest): LiveData<UserState> {
       return object : NetworkBoundResource<MainResponse, UserState>() {

           override fun handleApiSuccessResponse(response: ApiSuccessResponse<MainResponse>) {
               result.value =
                   UserState.RegisterPhoneNumber(response.body, null)

           }

           override fun onReturnError(message: String) {
               result.value =
                   UserState.RegisterPhoneNumber(null, message)
           }

           override fun createCall(): LiveData<GenericApiResponse<MainResponse>> {
               return MyRetrofitBuilder.apiService.registerPhoneNumber(
                   request
               )
           }

       }.asLiveData()
    }

    fun verifyLogin(request: VerifyLoginRequest): LiveData<UserState> {
        return object : NetworkBoundResource<VerifyResponse, UserState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<VerifyResponse>) {
                println("verify sucsess")
                result.value =
                    UserState.VerifyLogin(response.body, null)

            }

            override fun onReturnError(message: String) {
                println("verify error")
                result.value =
                    UserState.VerifyLogin(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<VerifyResponse>> {
                println("verify create call ")
                println(request)
                return MyRetrofitBuilder.apiService.verifyLogin(
                    request
                )
            }


        }.asLiveData()
    }

    fun getUserProfile(): LiveData<UserState> {
        return object : NetworkBoundResource<UserProfileResponse, UserState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<UserProfileResponse>) {
                result.value =
                    UserState.GetUserProfile(response.body, null)
            }

            override fun onReturnError(message: String) {
                result.value =
                    UserState.GetUserProfile(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<UserProfileResponse>> {
                return MyRetrofitBuilder.apiService.getUserProfile(
                    sharedPreferenceUtil?.getStringValue(TOKEN) ?: ""
                )
            }

        }.asLiveData()
    }
}
