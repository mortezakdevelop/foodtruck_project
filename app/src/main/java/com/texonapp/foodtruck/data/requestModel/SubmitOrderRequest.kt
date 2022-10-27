package com.texonapp.foodtruck.data.requestModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.FoodOrder

data class SubmitOrderRequest (
    @SerializedName("order_type")
    val order_type:String? = null,
    @SerializedName("discount_code")
    val discount_code:String? = null,
    @SerializedName("payment_method")
    val payment_method:Int? = null,
    @SerializedName("timeset_time")
    val timeset_time:String? = null,
    @SerializedName("items")
    val items:ArrayList<FoodOrder>? = null
)
