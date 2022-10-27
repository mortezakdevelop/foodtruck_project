package com.texonapp.foodtruck.data.model

import com.google.gson.annotations.SerializedName

data class FoodOrder(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("food_id")
    val food_id: Int? = null,
    @SerializedName("quantity")
    val quantity: Int? = null
)
