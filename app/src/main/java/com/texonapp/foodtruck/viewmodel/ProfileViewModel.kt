package com.texonapp.foodtruck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ProfileRequest
import com.texonapp.foodtruck.mvi.intent.CartOrderIntent
import com.texonapp.foodtruck.mvi.intent.ProfileIntent
import com.texonapp.foodtruck.mvi.state.CartOrderState
import com.texonapp.foodtruck.mvi.state.ProfileState
import com.texonapp.foodtruck.repository.CartOrderRepository
import com.texonapp.foodtruck.repository.ProfileRepository

class ProfileViewModel:MainViewModel() {
    private val _idleState = MutableLiveData<ProfileState>(ProfileState.Idle)
    private val _stateIntent: MutableLiveData<ProfileIntent> = MutableLiveData()

    var dataState: LiveData<ProfileState> = Transformations
        .switchMap(_stateIntent) { stateIntent ->
            stateIntent?.let {
                handleStateEvent(stateIntent)
            }
        }

    private fun handleStateEvent(stateIntent: ProfileIntent): LiveData<ProfileState> {
        return when (stateIntent) {
            is ProfileIntent.getProfile -> {
                ProfileRepository.getUserCart()
            }
            is ProfileIntent.PostProfile -> {
                ProfileRepository.postProfile(stateIntent.request)
            }
            is ProfileIntent.Idle -> {
                _idleState
            }
        }
    }

    private fun setStateEvent(intent: ProfileIntent) {
        _stateIntent.value = intent
    }

    fun getUserCart() {
        setStateEvent(ProfileIntent.getProfile)
    }

    fun increaseFoodQuantity(name:String, email:String) {
        setStateEvent(ProfileIntent.PostProfile(ProfileRequest(name,email)))
    }

    fun stateOff() {
        setStateEvent(ProfileIntent.Idle)
    }
}