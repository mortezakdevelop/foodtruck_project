package com.texonapp.foodtruck.mvi.state

import com.texonapp.foodtruck.data.responseModel.CartResponse
import com.texonapp.foodtruck.data.responseModel.ProfileResponse

sealed class ProfileState{
    data class GetProfile(
        val response: ProfileResponse?,
        val errorMessage: String?
    ):ProfileState()

     data class PostProfile(
        val response: ProfileResponse?,
        val errorMessage: String?
    ):ProfileState()

    object Idle : ProfileState()
}
