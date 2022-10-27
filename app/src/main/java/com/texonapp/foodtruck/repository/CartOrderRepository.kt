package com.texonapp.foodtruck.repository
import android.util.Log
import androidx.lifecycle.LiveData
import com.texonapp.foodtruck.api.ApiSuccessResponse
import com.texonapp.foodtruck.api.GenericApiResponse
import com.texonapp.foodtruck.api.MyRetrofitBuilder
import com.texonapp.foodtruck.api.NetworkBoundResource
import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
import com.texonapp.foodtruck.data.responseModel.CartResponse
import com.texonapp.foodtruck.data.responseModel.DeliveryChargeResponse
import com.texonapp.foodtruck.data.responseModel.DiscountCodeResponse
import com.texonapp.foodtruck.data.responseModel.MainResponse
import com.texonapp.foodtruck.mvi.state.CartOrderState
import com.texonapp.foodtruck.util.MainApplication.Companion.sharedPreferenceUtil
import com.texonapp.foodtruck.util.TOKEN

object CartOrderRepository {

    fun getUserCart(): LiveData<CartOrderState> {
        return object : NetworkBoundResource<CartResponse, CartOrderState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
                result.value =
                    CartOrderState.GetUserCart(response.body, null)
            }

            override fun onReturnError(message: String) {
                result.value =
                    CartOrderState.GetUserCart(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
                return MyRetrofitBuilder.apiService.getUserCart(
                    sharedPreferenceUtil?.getStringValue(TOKEN)!!
                )
            }

        }.asLiveData()
    }

    fun increaseFoodQuantity(request: FoodIdRequest): LiveData<CartOrderState> {
        return object : NetworkBoundResource<CartResponse, CartOrderState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
                result.value =
                    CartOrderState.IncreaseFoodQuantity(response.body, null)
                Log.i("TAG","Su")
            }

            override fun onReturnError(message: String) {
                result.value =
                    CartOrderState.IncreaseFoodQuantity(null, message)
                Log.i("TAG","ERROR + ${message}")
            }

            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
                Log.i("TAG","Req")
                return MyRetrofitBuilder.apiService.increaseFoodQuantity(
                    sharedPreferenceUtil?.getStringValue(TOKEN)!!,
                    request
                )
            }

        }.asLiveData()
    }

    fun reduceFoodQuantity(request: ItemIdRequest): LiveData<CartOrderState> {
        return object : NetworkBoundResource<CartResponse, CartOrderState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
                result.value =
                    CartOrderState.ReduceFoodQuantity(response.body, null)

            }

            override fun onReturnError(message: String) {
                result.value =
                    CartOrderState.ReduceFoodQuantity(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
                return MyRetrofitBuilder.apiService.reduceFoodQuantity(
                    sharedPreferenceUtil?.getStringValue(TOKEN)!!,
                    request
                )
            }

        }.asLiveData()
    }

    fun removeFoodFromCart(request: ItemIdRequest): LiveData<CartOrderState> {
        return object : NetworkBoundResource<CartResponse, CartOrderState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
                result.value =
                    CartOrderState.RemoveFoodFromCart(response.body, null)

            }

            override fun onReturnError(message: String) {
                result.value =
                    CartOrderState.RemoveFoodFromCart(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
                return MyRetrofitBuilder.apiService.removeFoodFromCart(
                    sharedPreferenceUtil?.getStringValue(TOKEN)!!,
                    request
                )
            }

        }.asLiveData()
    }

    fun emptyCart(): LiveData<CartOrderState> {
        return object : NetworkBoundResource<MainResponse, CartOrderState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<MainResponse>) {
                result.value =
                    CartOrderState.EmptyCart(response.body, null)

            }

            override fun onReturnError(message: String) {
                result.value =
                    CartOrderState.EmptyCart(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<MainResponse>> {
                return MyRetrofitBuilder.apiService.emptyCart(
                    sharedPreferenceUtil?.getStringValue(TOKEN)!!
                )
            }

        }.asLiveData()
    }

    fun addDiscountCode(discountCode: String): LiveData<CartOrderState> {
        return object : NetworkBoundResource<DiscountCodeResponse, CartOrderState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<DiscountCodeResponse>) {
                result.value =
                    CartOrderState.AddDiscountCode(response.body, null)

            }

            override fun onReturnError(message: String) {
                result.value =
                    CartOrderState.AddDiscountCode(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<DiscountCodeResponse>> {
                return MyRetrofitBuilder.apiService.addDiscountCode(
                    sharedPreferenceUtil?.getStringValue(TOKEN)!!,
                    discountCode
                )
            }

        }.asLiveData()
    }

    fun getDeliveryCharge(): LiveData<CartOrderState> {
        return object : NetworkBoundResource<DeliveryChargeResponse, CartOrderState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<DeliveryChargeResponse>) {
                result.value =
                    CartOrderState.GetDeliveryCharges(response.body, null)

            }

            override fun onReturnError(message: String) {
                result.value =
                    CartOrderState.GetDeliveryCharges(null, message)
            }

            override fun createCall(): LiveData<GenericApiResponse<DeliveryChargeResponse>> {
                return MyRetrofitBuilder.apiService.getDeliveryCharges(
                    sharedPreferenceUtil?.getStringValue(TOKEN)!!
                )
            }

        }.asLiveData()
    }

}

//package com.texonapp.foodtruck.repository
//
//import androidx.lifecycle.LiveData
//import com.texonapp.foodtruck.api.ApiSuccessResponse
//import com.texonapp.foodtruck.api.GenericApiResponse
//import com.texonapp.foodtruck.api.MyRetrofitBuilder
//import com.texonapp.foodtruck.api.NetworkBoundResource
//import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
//import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
//import com.texonapp.foodtruck.data.responseModel.CartResponse
//import com.texonapp.foodtruck.data.responseModel.DeliveryChargeResponse
//import com.texonapp.foodtruck.data.responseModel.DiscountCodeResponse
//import com.texonapp.foodtruck.data.responseModel.MainResponse
//import com.texonapp.foodtruck.mvi.state.CartOrderState
//import com.texonapp.foodtruck.util.MainApplication.Companion.sharedPreferenceUtil
//import com.texonapp.foodtruck.util.TOKEN
//
//object CartOrderRepository {
//
//    fun getUserCart(): LiveData<CartOrderState> {
//        return object : NetworkBoundResource<CartResponse, CartOrderState>() {
//
//            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
//                result.value =
//                    CartOrderState.GetUserCart(response.body, null)
//            }
//
//            override fun onReturnError(message: String) {
//                result.value =
//                    CartOrderState.GetUserCart(null, message)
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
//                return MyRetrofitBuilder.apiService.getUserCart(
//                    sharedPreferenceUtil?.getStringValue(TOKEN)!!
//                )
//            }
//
//        }.asLiveData()
//    }
//
//    fun increaseFoodQuantity(request: FoodIdRequest): LiveData<CartOrderState> {
//        return object : NetworkBoundResource<CartResponse, CartOrderState>() {
//
//            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
//                result.value =
//                    CartOrderState.IncreaseFoodQuantity(response.body, null)
//
//            }
//
//            override fun onReturnError(message: String) {
//                result.value =
//                    CartOrderState.IncreaseFoodQuantity(null, message)
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
//                return MyRetrofitBuilder.apiService.increaseFoodQuantity(
//                    sharedPreferenceUtil?.getStringValue(TOKEN)!!,
//                    request
//                )
//            }
//
//        }.asLiveData()
//    }
//
//    fun reduceFoodQuantity(request: ItemIdRequest): LiveData<CartOrderState> {
//        return object : NetworkBoundResource<CartResponse, CartOrderState>() {
//
//            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
//                result.value =
//                    CartOrderState.ReduceFoodQuantity(response.body, null)
//
//            }
//
//            override fun onReturnError(message: String) {
//                result.value =
//                    CartOrderState.ReduceFoodQuantity(null, message)
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
//                return MyRetrofitBuilder.apiService.reduceFoodQuantity(
//                    sharedPreferenceUtil?.getStringValue(TOKEN)!!,
//                    request
//                )
//            }
//
//        }.asLiveData()
//    }
//
//    fun removeFoodFromCart(request: ItemIdRequest): LiveData<CartOrderState> {
//        return object : NetworkBoundResource<CartResponse, CartOrderState>() {
//
//            override fun handleApiSuccessResponse(response: ApiSuccessResponse<CartResponse>) {
//                result.value =
//                    CartOrderState.RemoveFoodFromCart(response.body, null)
//
//            }
//
//            override fun onReturnError(message: String) {
//                result.value =
//                    CartOrderState.RemoveFoodFromCart(null, message)
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<CartResponse>> {
//                return MyRetrofitBuilder.apiService.removeFoodFromCart(
//                    sharedPreferenceUtil?.getStringValue(TOKEN)!!,
//                    request
//                )
//            }
//
//        }.asLiveData()
//    }
//
//    fun emptyCart(): LiveData<CartOrderState> {
//        return object : NetworkBoundResource<MainResponse, CartOrderState>() {
//
//            override fun handleApiSuccessResponse(response: ApiSuccessResponse<MainResponse>) {
//                result.value =
//                    CartOrderState.EmptyCart(response.body, null)
//
//            }
//
//            override fun onReturnError(message: String) {
//                result.value =
//                    CartOrderState.EmptyCart(null, message)
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<MainResponse>> {
//                return MyRetrofitBuilder.apiService.emptyCart(
//                    sharedPreferenceUtil?.getStringValue(TOKEN)!!
//                )
//            }
//
//        }.asLiveData()
//    }
//
//    fun addDiscountCode(discountCode: String): LiveData<CartOrderState> {
//        return object : NetworkBoundResource<DiscountCodeResponse, CartOrderState>() {
//
//            override fun handleApiSuccessResponse(response: ApiSuccessResponse<DiscountCodeResponse>) {
//                result.value =
//                    CartOrderState.AddDiscountCode(response.body, null)
//
//            }
//
//            override fun onReturnError(message: String) {
//                result.value =
//                    CartOrderState.AddDiscountCode(null, message)
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<DiscountCodeResponse>> {
//                return MyRetrofitBuilder.apiService.addDiscountCode(
//                    sharedPreferenceUtil?.getStringValue(TOKEN)!!,
//                    discountCode
//                )
//            }
//
//        }.asLiveData()
//    }
//
//    fun getDeliveryCharge(): LiveData<CartOrderState> {
//        return object : NetworkBoundResource<DeliveryChargeResponse, CartOrderState>() {
//
//            override fun handleApiSuccessResponse(response: ApiSuccessResponse<DeliveryChargeResponse>) {
//                result.value =
//                    CartOrderState.GetDeliveryCharges(response.body, null)
//
//            }
//
//            override fun onReturnError(message: String) {
//                result.value =
//                    CartOrderState.GetDeliveryCharges(null, message)
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<DeliveryChargeResponse>> {
//                return MyRetrofitBuilder.apiService.getDeliveryCharges(
//                    sharedPreferenceUtil?.getStringValue(TOKEN)!!
//                )
//            }
//
//        }.asLiveData()
//    }
//
//}
