package com.texonapp.foodtruck.mvi

import com.texonapp.foodtruck.data.responseModel.BrandDetialResponce
import com.texonapp.foodtruck.data.responseModel.BrandFoodsResponse
import com.texonapp.foodtruck.data.responseModel.BrandResponse

sealed class BrandState {

    data class GetBrands(
        val responce:BrandResponse?,
        val errorMessage:String?
    ):BrandState()

    data class GetBrandDetail(
        val response: BrandDetialResponce?,
        val errorMessage: String?
    ):BrandState()

    data class GetBrandFood(
        val response: BrandFoodsResponse?,
        val errorMessage: String?
    ):BrandState()

    object Idls:BrandState()
}