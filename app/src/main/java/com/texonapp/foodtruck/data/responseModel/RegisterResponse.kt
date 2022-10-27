package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.User

class RegisterResponse:MainResponse() {
    @SerializedName("data")
    val data: ArrayList<Any>? = null
}