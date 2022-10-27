package com.texonapp.foodtruck.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int? = null,
    val credit:Int? = null,
    val notifications:Int? = null,
)

//open class User {
//    @SerializedName("id")
//    var id: Int? = null
//
//    @SerializedName("role_id")
//    var roleId: Int? = null
//
//    @SerializedName("name")
//    var name: String? = null
//
//    @SerializedName("family")
//    var family: String? = null
//
//    @SerializedName("email")
//    var email: String? = null
//
//    @SerializedName("mobile")
//    var mobile: Int? = null
//
//    @SerializedName("mobile_verification_token")
//    var mobileVerificationToken: Int? = null
//
//    @SerializedName("vendor_id")
//    var vendorId: Int? = null
//
//    @SerializedName("avatar")
//    var avatar: String? = null
//
//    @SerializedName("id_card")
//    var idCard: String? = null
//
//    @SerializedName("fcm_token")
//    var fcmToken: String? = null
//
//     @SerializedName("credit")
//    var credit: String? = null
//
//    @SerializedName("token")
//    var token: String? = null
//
//
//}
