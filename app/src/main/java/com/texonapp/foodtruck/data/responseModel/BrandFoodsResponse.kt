
package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.Foods

class BrandFoodsResponse:MainResponse() {
    @SerializedName("data")
    val data: DataFood? = null

    inner class DataFood {
        @SerializedName("cart_count")
        val cart_count: Int? = null

        @SerializedName("foods")
        val foods: ArrayList<Foods>? = null
    }
}

//package com.texonapp.foodtruck.data.responseModel
//
//import com.google.gson.annotations.SerializedName
//import com.texonapp.foodtruck.data.model.Food
//import com.texonapp.foodtruck.data.model.Foods
//
//class BrandFoodsResponse:MainResponse() {
//    @SerializedName("data")
//    val dataFood: DataFood? = null
//
//    inner class DataFood {
//        @SerializedName("links")
//        val links: Links? = null
//
//        @SerializedName("cart_count")
//        val cart_count: Int? = null
//
//        @SerializedName("foods")
//        val foods: ArrayList<Foods.Food>? = null
//
//        inner class Links {
//            val first: String? = null
//            val last: String? = null
//            val prev: String? = null
//            val next: String? = null
//        }
//    }
//}