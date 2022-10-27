package com.texonapp.foodtruck.data.requestModel

import com.google.gson.annotations.SerializedName

data class FoodIdRequest(
    @SerializedName("food_id") val foodId: Int
)

