package com.texonapp.foodtruck.data.model

import com.google.gson.annotations.SerializedName

class Link {
    @SerializedName("url")
    val url:String? = null

    @SerializedName("label")
    val label:String? = null

    @SerializedName("active")
    val active:Boolean? = null
}