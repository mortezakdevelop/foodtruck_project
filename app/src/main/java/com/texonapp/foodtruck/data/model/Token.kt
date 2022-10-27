package com.texonapp.foodtruck.data.model

import com.google.gson.annotations.SerializedName

data class Token(@SerializedName("tokenType") val tokenType: String,
            @SerializedName("token") val token: String)