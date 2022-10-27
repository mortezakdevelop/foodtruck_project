package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.*

class HomePageResponse : MainResponse() {
    @SerializedName("data")
    val data: Data? = null

    inner class Data {
        @SerializedName("user")
        val user: User? = null

        @SerializedName("restaurants")
        val restaurants: ArrayList<RestaurantModel>? = null

        @SerializedName("promotions")
        val promotions: PromotionModel? = null

        @SerializedName("activities")
        val activities: ArrayList<ActivityModel>? = null
    }

    inner class PromotionModel {

        @SerializedName("internal")
        val internal:ArrayList<InternalModel>? = null

        @SerializedName("external")
        val external:ArrayList<ExternalModel>? = null

    }

}