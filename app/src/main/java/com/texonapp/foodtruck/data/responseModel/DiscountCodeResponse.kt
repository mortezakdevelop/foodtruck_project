package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName

class DiscountCodeResponse : MainResponse() {
    @SerializedName("data")
    val data: Data? = null

    inner class Data {
        @SerializedName("amount")
        val amount: Double? = null
    }
}