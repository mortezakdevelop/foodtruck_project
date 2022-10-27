package com.texonapp.foodtruck.mvi.intent

import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ProfileRequest
import com.texonapp.foodtruck.data.responseModel.SubmitOrderResponse
import com.texonapp.foodtruck.data.responseModel.UserProfileResponse
import com.texonapp.foodtruck.mvi.state.OrderState

sealed class ProfileIntent {

    object getProfile : ProfileIntent()

    data class PostProfile(val request: ProfileRequest) : ProfileIntent()

    object  Idle: ProfileIntent()
}