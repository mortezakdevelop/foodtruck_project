package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName


open class MainResponse {

    @SerializedName("success")
    val success: Boolean? = null

    @SerializedName("status")
    val status: Status? = null

    inner class Status {
        @SerializedName("code")
        val code: Int? = null

        @SerializedName("message")
        val message: String? = null

    }
}