package com.texonapp.foodtruck.mvi


sealed class HomeIntent {

    object GetHomePage : HomeIntent()

    object Idle : HomeIntent()

}