package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.Token

class UserProfileResponse {

    @SerializedName("data")
    val data: Data? = null

    inner class Data {
        @SerializedName("token")
        val token: Token? = null
    }
}