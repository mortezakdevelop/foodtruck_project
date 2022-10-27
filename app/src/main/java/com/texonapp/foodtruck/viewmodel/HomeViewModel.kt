package com.texonapp.foodtruck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.mvi.HomeIntent
import com.texonapp.foodtruck.mvi.HomeState
import com.texonapp.foodtruck.repository.HomeRepository

class HomeViewModel : MainViewModel() {
    private val _idleState = MutableLiveData<HomeState>(HomeState.Idle)

    private val _stateIntent: MutableLiveData<HomeIntent> = MutableLiveData()

    var dataState: LiveData<HomeState> = Transformations
        .switchMap(_stateIntent) { stateIntent ->
            stateIntent?.let {
                handleStateEvent(stateIntent)
            }
        }

    private fun handleStateEvent(stateIntent: HomeIntent): LiveData<HomeState> {
        return when (stateIntent) {
            is HomeIntent.GetHomePage -> {
                HomeRepository.getHomePage()
            }

            is HomeIntent.Idle -> {
                _idleState
            }
        }
    }

    private fun setStateEvent(intent: HomeIntent) {
        _stateIntent.value = intent
    }

    fun getHomePage(){
        setStateEvent(HomeIntent.GetHomePage)
    }

    fun stateOff() {
        setStateEvent(HomeIntent.Idle)
    }

}