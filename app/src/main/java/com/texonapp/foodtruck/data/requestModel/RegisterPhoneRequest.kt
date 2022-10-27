package com.texonapp.foodtruck.data.requestModel

import com.google.gson.annotations.SerializedName

data class RegisterPhoneRequest(
    @SerializedName("mobile") val phoneNumber: String
)

