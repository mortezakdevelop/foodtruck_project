package com.texonapp.foodtruck.data.requestModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.FoodOrder

data class ProfileRequest(

    @SerializedName("name")
    val name:String? = null,
    @SerializedName("email")
    val email:String? = null,
    @SerializedName("avatar")
    val avatar:Int? = null,
    @SerializedName("id_card")
    val id_card:String? = null,
    @SerializedName("password")
    val password:String? = null,
    @SerializedName("birth_day")
    val birth_day:String? = null,

)
