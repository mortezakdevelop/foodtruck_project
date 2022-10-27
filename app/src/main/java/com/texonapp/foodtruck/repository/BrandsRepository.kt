package com.texonapp.foodtruck.repository

import androidx.lifecycle.LiveData
import com.texonapp.foodtruck.api.ApiSuccessResponse
import com.texonapp.foodtruck.api.GenericApiResponse
import com.texonapp.foodtruck.api.MyRetrofitBuilder
import com.texonapp.foodtruck.api.NetworkBoundResource
import com.texonapp.foodtruck.data.requestModel.BrandDetailRequest
import com.texonapp.foodtruck.data.responseModel.BrandDetialResponce
import com.texonapp.foodtruck.data.responseModel.BrandFoodsResponse
import com.texonapp.foodtruck.data.responseModel.BrandResponse
import com.texonapp.foodtruck.mvi.BrandState
import com.texonapp.foodtruck.util.MainApplication
import com.texonapp.foodtruck.util.TOKEN

object BrandsRepository {
    fun getBrands():LiveData<BrandState>{
        return object :NetworkBoundResource<BrandResponse,BrandState>() {
            override fun onReturnError(message: String) {
                result.value = BrandState.GetBrands(null,message)
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<BrandResponse>) {
                result.value = BrandState.GetBrands(response.body,null)
            }

            override fun createCall(): LiveData<GenericApiResponse<BrandResponse>> {
                return MyRetrofitBuilder.apiService.getBrands(
                    MainApplication.sharedPreferenceUtil?.getStringValue(
                        TOKEN
                    )!!)
            }

        }.asLiveData()
    }

    fun getBrandDetail(request: BrandDetailRequest):LiveData<BrandState>{
        return object :NetworkBoundResource<BrandDetialResponce,BrandState>() {
            override fun onReturnError(message: String) {
                result.value = BrandState.GetBrandDetail(null,message)
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<BrandDetialResponce>) {
                result.value = BrandState.GetBrandDetail(response.body,null)
            }

            override fun createCall(): LiveData<GenericApiResponse<BrandDetialResponce>> {
                return MyRetrofitBuilder.apiService.getBrandDetail(
                    MainApplication.sharedPreferenceUtil?.getStringValue(
                        TOKEN
                    )!!,request.id)
            }

        }.asLiveData()
    }

    fun getBrandFood(request: BrandDetailRequest):LiveData<BrandState>{
        return object :NetworkBoundResource<BrandFoodsResponse,BrandState>() {
            override fun onReturnError(message: String) {
                result.value = BrandState.GetBrandFood(null,message)
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<BrandFoodsResponse>) {
                result.value = BrandState.GetBrandFood(response.body,null)

            }

            override fun createCall(): LiveData<GenericApiResponse<BrandFoodsResponse>> {
                return MyRetrofitBuilder.apiService.getBrandFood(
                    MainApplication.sharedPreferenceUtil?.getStringValue(
                        TOKEN
                    )!!,request.id)
            }

        }.asLiveData()
    }
}