package com.texonapp.foodtruck.api

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.util.MainApplication

class ApiError {
    @SerializedName("error")
    var error: HashMap<String, ArrayList<String>>? = null

    fun getError(): String? {
        return if (error != null && error!!.size != 0) {
            var errorMessage = error!!.values.toTypedArray()[0].toString()
            errorMessage = errorMessage.replace("[", "")
            errorMessage = errorMessage.replace("]", "")
            errorMessage
        } else MainApplication.applicationContext().getString(R.string.check_connection)
    }
}