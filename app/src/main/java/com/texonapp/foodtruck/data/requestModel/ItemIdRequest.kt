package com.texonapp.foodtruck.data.requestModel

import com.google.gson.annotations.SerializedName

data class ItemIdRequest(
    @SerializedName("item_id") val itemId: Int
)

