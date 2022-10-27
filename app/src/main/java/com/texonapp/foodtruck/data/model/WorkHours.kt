package com.texonapp.foodtruck.data.model

import com.google.gson.annotations.SerializedName

class WorkHours {
    @SerializedName("id")
    val id:Int? = null

    @SerializedName("weekday")
    val weekday:String? = null

    @SerializedName("start_time")
    val start_time:String? = null

    @SerializedName("end_time")
    val end_time:String? = null

    @SerializedName("restaurant_id")
    val restaurant_id:Int? = null

    @SerializedName("created_at")
    val created_at:String? = null

    @SerializedName("updated_at")
    val updated_at:String? = null
}