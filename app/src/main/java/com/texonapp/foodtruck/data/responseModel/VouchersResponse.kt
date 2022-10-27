package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.Discounts
import com.texonapp.foodtruck.data.model.PromotionModel

class VouchersResponse:MainResponse() {

    @SerializedName("data")
    val data:VouchersData? = null

    inner class VouchersData{
        @SerializedName("promotions")
        val promotions:ArrayList<PromotionModel>? = null

        @SerializedName("discounts")
        val discounts:ArrayList<Discounts>? = null

    }
}