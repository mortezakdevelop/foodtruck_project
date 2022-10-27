package com.texonapp.foodtruck.adapter.cardListener

import com.texonapp.foodtruck.data.model.RestaurantModel
import com.texonapp.foodtruck.model.DeliveryTypeModel

interface OrderTypeClickListener {
    fun onItemOrderTypeClickListener(deliveryTypeModel: Int, orderModel: DeliveryTypeModel){

    }
}