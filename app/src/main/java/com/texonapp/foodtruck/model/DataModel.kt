package com.texonapp.foodtruck.model

data class DataModel(
    val `data`: Data,
    val errors: List<Any>,
    val success: Boolean
)