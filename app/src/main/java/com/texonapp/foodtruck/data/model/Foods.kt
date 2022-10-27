package com.texonapp.foodtruck.data.model

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.Food
import com.texonapp.foodtruck.data.responseModel.BrandFoodsResponse

class Foods {
    @SerializedName("current_page")
    val current_page:Int? = null

    @SerializedName("data")
    val food:ArrayList<Food>? = null

    @SerializedName("links")
    val links: ArrayList<Links>? = null

    inner class Links {
        @SerializedName("url")
        val url: String? = null
        @SerializedName("label")
        val label: String? = null
        @SerializedName("active")
        val active: Boolean? = null
    }

    inner class Food{

        @SerializedName("id")
        val id:Int? = null

        @SerializedName("name")
        val name:String? = null

        @SerializedName("image")
        val image:String? = null

        @SerializedName("price")
        val price:String? = null

        @SerializedName("description")
        val description:String? = null
    }

}