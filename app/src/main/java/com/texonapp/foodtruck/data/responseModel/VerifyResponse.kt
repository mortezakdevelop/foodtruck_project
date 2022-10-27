package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.Token
import com.texonapp.foodtruck.data.model.User

class VerifyResponse {
    @SerializedName("data")
    val data: Data? = null

    inner class Data {
        @SerializedName("token")
        val token: String? = null
        val user: User?=null
        val isNew:Boolean = true
    }
}