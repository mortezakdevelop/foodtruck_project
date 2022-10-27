package com.texonapp.foodtruck.mvi

import com.texonapp.foodtruck.data.responseModel.MainResponse
import com.texonapp.foodtruck.data.responseModel.RegisterResponse
import com.texonapp.foodtruck.data.responseModel.UserProfileResponse
import com.texonapp.foodtruck.data.responseModel.VerifyResponse


sealed class UserState {

    data class RegisterPhoneNumber(
        val response: MainResponse?,
        val errorMessage: String?
    ) : UserState()

    data class RegisterEmail(
        val response: RegisterResponse?,
        val errorMessage: String?
    ) : UserState()

    data class VerifyLogin(
        val response: VerifyResponse?,
        val errorMessage: String?
    ) : UserState()

    data class GetUserProfile(
        val response: UserProfileResponse?,
        val errorMessage: String?
    ) : UserState()

    object Idle : UserState()

}