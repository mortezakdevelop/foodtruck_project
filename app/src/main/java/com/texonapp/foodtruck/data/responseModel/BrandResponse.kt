package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.RestaurantModel
import com.texonapp.foodtruck.model.BrandRestaurantModel
import com.texonapp.foodtruck.model.DataBrandModel
import com.texonapp.foodtruck.model.Link
import com.texonapp.foodtruck.model.RestaurantBrandModel

class BrandResponse{

    @SerializedName("data")
    val data:ArrayList<BrandRestaurantModel>? = null

    @SerializedName("links")
    val links:Links? = null

    @SerializedName("meta")
    val meta: Meta? = null

    inner class Links{
        @SerializedName("first")
        val first:String? = null

        @SerializedName("last")
        val last:String? = null

        @SerializedName("prev")
        val prev:String? = null

        @SerializedName("next")
        val next:String? = null
    }

    inner class Meta{
        @SerializedName("current_page")
        val current_page:Int? = null

        @SerializedName("from")
        val from:Int? = null

        @SerializedName("last_page")
        val last_page:String? = null

        @SerializedName("links")
        val links:ArrayList<Link>? = null
    }


//    inner class Resturants{
//        @SerializedName("current_page")
//        val currentPage:Int? = null
//
//        @SerializedName("data")
//        val data:ArrayList<BrandRestaurantModel>? = null
//
//    }
//    inner class Status{
//        @SerializedName("message")
//        val message:String? = null
//        @SerializedName("code")
//        val code:Int? = null
//    }

}
