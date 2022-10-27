package com.texonapp.foodtruck.model

data class DineAtModel(
    val foodType: String,
    val foodTime:String,
    var default: Boolean = false
)
