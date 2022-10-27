package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName

class ProfileResponse:MainResponse() {

    @SerializedName("data")
    val data: Data? = null

    inner class Data(
        @SerializedName("user")
        val user:User? = null
    )

    inner class User(
        @SerializedName("id")
        val id:Int? = null,
        @SerializedName("name")
        val name:String? = null,
        @SerializedName("email")
        val email:String? = null,
        @SerializedName("mobile")
        val mobile:String? = null,
        @SerializedName("restaurant_id")
        val restaurant_id:String? = null,
        @SerializedName("avatar")
        val avatar:String? = null,
        @SerializedName("first_login")
        val first_login:Boolean = false,
        @SerializedName("email_verify_at")
        val email_verify_at:String? = null,
        @SerializedName("mobile_verification_token")
        val mobile_verification_token:String? = null,
        @SerializedName("birth_day")
        val birth_day:String? = null,
        @SerializedName("id_card")
        val id_card:String? = null,
        @SerializedName("credit")
        val credit:String? = null,
        @SerializedName("referral_code")
        val referral_code:String? = null,
        @SerializedName("referrer")
        val referrer:String? = null,
        @SerializedName("default_address_id")
        val default_address_id:String? = null,
        @SerializedName("created_at")
        val created_at:String? = null,
        @SerializedName("updated_at")
        val updated_at:String? = null,
        @SerializedName("join_date")
        val join_date:String? = null
    )
}