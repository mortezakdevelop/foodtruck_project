package com.texonapp.foodtruck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.texonapp.foodtruck.mvi.HomeIntent
import com.texonapp.foodtruck.mvi.intent.GoodFoodMenuIntent
import com.texonapp.foodtruck.mvi.intent.VouchersIntent
import com.texonapp.foodtruck.mvi.state.GoodFoodMenuState
import com.texonapp.foodtruck.mvi.state.VouchersState
import com.texonapp.foodtruck.repository.GoodFoodMenuRepository
import com.texonapp.foodtruck.repository.VouchersRepository

class VouchersViewModel:MainViewModel() {

    private val _idleState = MutableLiveData<VouchersState>(VouchersState.Idle)
    private val _stateIntent: MutableLiveData<VouchersIntent> = MutableLiveData()

    var dataState: LiveData<VouchersState> = Transformations
        .switchMap(_stateIntent) { stateIntent ->
            stateIntent?.let {
                handleStateEvent(stateIntent)
            }
        }

    private fun handleStateEvent(stateIntent: VouchersIntent): LiveData<VouchersState> {
        return when (stateIntent) {
            is VouchersIntent.GetVouchers -> {
                VouchersRepository.getVouchers()
            }

            else -> {_idleState}
        }
    }

    private fun setStateEvent(intent: VouchersIntent) {
        _stateIntent.value = intent
    }

    fun getVouchers(){
        setStateEvent(VouchersIntent.GetVouchers)
    }

    fun stateOff() {
        setStateEvent(VouchersIntent.Idle)
    }
}