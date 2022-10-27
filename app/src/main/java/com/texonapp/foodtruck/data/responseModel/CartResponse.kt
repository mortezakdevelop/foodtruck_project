package com.texonapp.foodtruck.data.responseModel
import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.*

class CartResponse : MainResponse() {
    @SerializedName("data")
    val data: Data? = null

    inner class Data {
        @SerializedName("count")
        val count: Int? = null

        @SerializedName("items")
        val orderItems: ArrayList<OrderModel>? = null

    }
}

//package com.texonapp.foodtruck.data.responseModel
//
//import com.google.gson.annotations.SerializedName
//import com.texonapp.foodtruck.data.model.*
//
//class CartResponse : MainResponse() {
//    @SerializedName("data")
//    val data: Data? = null
//
//    inner class Data {
//        @SerializedName("count")
//        val count: Int? = null
//
//         @SerializedName("success")
//        val success: Boolean? = null
//
//        @SerializedName("items")
//        val orderItems: ArrayList<OrderModel>? = null
//
//    }
//}