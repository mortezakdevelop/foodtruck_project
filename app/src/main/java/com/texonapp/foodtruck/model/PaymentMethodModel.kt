package com.texonapp.foodtruck.model

data class PaymentMethodModel(
    val cartNumber: String,
    val img: String,
    var activated: Boolean = false
)
