package com.texonapp.foodtruck.data.model

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.model.BrandPopularFoodModel

class BrandDetail {
    @SerializedName("id")
    val id:Int? = null

    @SerializedName("name")
    val name:String? = null

    @SerializedName("owner_id")
    val ownerId:Int? = null

    @SerializedName("address")
    val address:String? = null

    @SerializedName("delivery_types")
    val deliveryTypes:ArrayList<String>? = null

    @SerializedName("country_id")
    val countryId:Int? = null

    @SerializedName("state_id")
    val stateId:Int? = null

    @SerializedName("city_id")
    val cityId:Int? = null

    @SerializedName("phone")
    val phone:String? = null

    @SerializedName("lat")
    val lat:String? = null

    @SerializedName("long")
    val long:String? = null

    @SerializedName("image")
    val image:String? = null

    @SerializedName("logo")
    val logo:String? = null

    @SerializedName("description")
    val description:String? = null

    @SerializedName("status")
    val status:String? = null

    @SerializedName("referral_code")
    val referralCode:Int? = null

    @SerializedName("referrer_id")
    val referralId:Int? = null

    @SerializedName("created_at")
    val createAt:String? = null

    @SerializedName("updated_at")
    val updateAt:String? = null

    @SerializedName("foods")
    val foods: ArrayList<BrandPopularFoodModel> = null!!
    @SerializedName("testimonials")
    val testimonials:Testimations? = null

    @SerializedName("working_hours")
    val workingHours:WorkHours? = null
}