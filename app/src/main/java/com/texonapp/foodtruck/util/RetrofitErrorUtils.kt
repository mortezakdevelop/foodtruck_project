package com.texonapp.foodtruck.util

import com.google.gson.Gson
import com.texonapp.foodtruck.api.ApiError

class RetrofitErrorUtils {
    companion object{
        fun parseError(response: String): String? {
            val gson = Gson()
            var apiError = ApiError()
            try {
                apiError =
                    gson.fromJson(response, ApiError::class.java)
            }catch (e:Exception){
                var hashMap= hashMapOf<String , ArrayList<String>>()
                apiError.error = hashMap
            }

            return apiError.getError()
        }

    }
}