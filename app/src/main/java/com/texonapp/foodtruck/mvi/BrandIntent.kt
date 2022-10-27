package com.texonapp.foodtruck.mvi

import com.texonapp.foodtruck.data.requestModel.BrandDetailRequest

sealed class BrandIntent{

    object GetBrands:BrandIntent()


    data class GetBrandDetail(val request: BrandDetailRequest) : BrandIntent()

    data class GetBrandFood(val request: BrandDetailRequest) : BrandIntent()

    object Idle:BrandIntent()
}
