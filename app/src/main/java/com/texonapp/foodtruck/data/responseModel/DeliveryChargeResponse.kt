package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.DeliveryChargeModel

class DeliveryChargeResponse : MainResponse() {
    @SerializedName("data")
    val deliveryChargeItems: ArrayList<DeliveryChargeModel>? = null
}