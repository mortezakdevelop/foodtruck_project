package com.texonapp.foodtruck.data.model

import com.google.gson.annotations.SerializedName

class OrderModel {

    @SerializedName("id")
    val id: String? = null

    @SerializedName("quantity")
    var quantity: Int? = null

    @SerializedName("food")
    val food: FoodModel? = null

}