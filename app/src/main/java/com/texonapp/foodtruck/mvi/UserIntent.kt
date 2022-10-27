package com.texonapp.foodtruck.mvi

import com.texonapp.foodtruck.data.requestModel.RegisterPhoneRequest
import com.texonapp.foodtruck.data.requestModel.VerifyLoginRequest

sealed class UserIntent {

    data class RegisterPhoneNumber(val request: RegisterPhoneRequest) : UserIntent()

    data class VerifyLogin(val request: VerifyLoginRequest) : UserIntent()

    object UserProfile : UserIntent()

    object Idle : UserIntent()

}