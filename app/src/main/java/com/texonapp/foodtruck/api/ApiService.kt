package com.texonapp.foodtruck.api

import androidx.lifecycle.LiveData
import com.texonapp.foodtruck.data.requestModel.*
import com.texonapp.foodtruck.data.responseModel.*
import retrofit2.http.*

interface ApiService {

    //user
    @POST("auth/perform")
    fun registerPhoneNumber(
        @Body requestBody: RegisterPhoneRequest
    ): LiveData<GenericApiResponse<MainResponse>>

    @POST("auth/verify-user")
    fun verifyLogin(
        @Body requestBody: VerifyLoginRequest
    ): LiveData<GenericApiResponse<VerifyResponse>>

    @GET("notifications")
    fun getUserProfile(
        @Header("Authorization") token: String
    ): LiveData<GenericApiResponse<UserProfileResponse>>

    @GET("brands")
    fun getBrands(@Header("Authorization") token: String):
            LiveData<GenericApiResponse<BrandResponse>>

    @GET("brand/{id}")
    fun getBrandDetail(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): LiveData<GenericApiResponse<BrandDetialResponce>>

    @GET("brand/{id}/foods")
    fun getBrandFood(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): LiveData<GenericApiResponse<BrandFoodsResponse>>

    @GET("pages/home")
    fun getHomePage(
        @Header("Authorization") token: String
    ): LiveData<GenericApiResponse<HomePageResponse>>

    @GET("user/cart")
    fun getUserCart(
        @Header("Authorization") token: String
    ): LiveData<GenericApiResponse<CartResponse>>

    @POST("user/cart/add_item")
    fun increaseFoodQuantity(
        @Header("Authorization") token: String,
        @Body requestBody: FoodIdRequest
    ): LiveData<GenericApiResponse<CartResponse>>

    @POST("user/cart/reduce_item")
    fun reduceFoodQuantity(
        @Header("Authorization") token: String,
        @Body requestBody: ItemIdRequest
    ): LiveData<GenericApiResponse<CartResponse>>


    @POST("user/cart/remove_item")
    fun removeFoodFromCart(
        @Header("Authorization") token: String,
        @Body requestBody: ItemIdRequest
    ): LiveData<GenericApiResponse<CartResponse>>

    @POST("user/cart/empty")
    fun emptyCart(
        @Header("Authorization") token: String
    ): LiveData<GenericApiResponse<MainResponse>>
    //order
    @GET("orders/check_discount")
    fun addDiscountCode(
        @Header("Authorization") token: String,
        @Query("code") discountCode: String
    ): LiveData<GenericApiResponse<DiscountCodeResponse>>

    @GET("orders/delivery_services")
    fun getDeliveryCharges(
        @Header("Authorization") token: String
    ): LiveData<GenericApiResponse<DeliveryChargeResponse>>

    @POST("orders/submit")
    fun submitOrder(
        @Header("Authorization")token: String,
        @Body request : SubmitOrderRequest
    ): LiveData<GenericApiResponse<SubmitOrderResponse>>

    @GET("vouchers")
    fun getVouchers(
        @Header("Authorization") token: String
    ):LiveData<GenericApiResponse<VouchersResponse>>

    @POST("user/profile")
    fun postProfile(
    @Header("Authorization")token:String,
    @Body requestBody: ProfileRequest
    ): LiveData<GenericApiResponse<ProfileResponse>>

    @GET("user/profile")
    fun getProfile(
        @Header("Authorization") token: String
    ): LiveData<GenericApiResponse<ProfileResponse>>
}
