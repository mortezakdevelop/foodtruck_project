package com.texonapp.foodtruck.mvi

import com.texonapp.foodtruck.data.responseModel.*


sealed class HomeState {

    data class GetHomePage(
        val response: HomePageResponse?,
        val errorMessage: String?
    ) : HomeState()

    object Idle : HomeState()

}