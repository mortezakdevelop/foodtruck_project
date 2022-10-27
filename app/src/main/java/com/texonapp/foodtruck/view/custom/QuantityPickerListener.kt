package com.texonapp.foodtruck.view.custom

interface QuantityPickerListener {

    fun increaseQuantity(oldValue: Int, newValue: Int)

    fun reduceFoodQuantity(oldValue: Int, newValue: Int)
}