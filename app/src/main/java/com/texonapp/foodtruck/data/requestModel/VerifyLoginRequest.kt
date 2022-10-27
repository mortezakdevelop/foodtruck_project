package com.texonapp.foodtruck.data.requestModel

import com.google.gson.annotations.SerializedName

data class VerifyLoginRequest (
    @SerializedName("verification_code") val verification_code: Int,
    @SerializedName("mobile") val mobile: String?,
    @SerializedName("device_name") val device_name:String?
)