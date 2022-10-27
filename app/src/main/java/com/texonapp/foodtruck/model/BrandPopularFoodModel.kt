package com.texonapp.foodtruck.model

data class BrandPopularFoodModel(
    val category_id: Int,
    val created_at: String,
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val price: String,
    val restaurant_id: Int,
    val status: String,
    val updated_at: String
)
