package com.texonapp.foodtruck.mvi.intent

import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ItemIdRequest

sealed class GoodFoodMenuIntent {

    object GetUserCart : GoodFoodMenuIntent()

    data class IncreaseFoodQuantity(val request: FoodIdRequest) : GoodFoodMenuIntent()

    data class ReduceFoodQuantity(val request: ItemIdRequest) : GoodFoodMenuIntent()

    object Idle : GoodFoodMenuIntent()

    }


//package com.texonapp.foodtruck.mvi.intent
//
//import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
//import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
//
//sealed class GoodFoodMenuIntent {
//
//    object GetUserCart : GoodFoodMenuIntent()
//
//    data class IncreaseFoodQuantity(val request: FoodIdRequest) : GoodFoodMenuIntent()
//
//    data class ReduceFoodQuantity(val request: ItemIdRequest) : GoodFoodMenuIntent()
//
//    object Idle : GoodFoodMenuIntent()
//
//}