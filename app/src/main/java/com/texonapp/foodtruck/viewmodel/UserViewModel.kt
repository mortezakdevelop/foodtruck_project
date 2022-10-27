package com.texonapp.foodtruck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.texonapp.foodtruck.data.requestModel.RegisterPhoneRequest
import com.texonapp.foodtruck.data.requestModel.VerifyLoginRequest
import com.texonapp.foodtruck.mvi.UserIntent
import com.texonapp.foodtruck.mvi.UserState
import com.texonapp.foodtruck.repository.UserRepository
import com.texonapp.foodtruck.util.MainApplication.Companion.sharedPreferenceUtil
import com.texonapp.foodtruck.util.TOKEN
import com.texonapp.foodtruck.util.USER_ID

class UserViewModel: ViewModel() {

    private val _idleState = MutableLiveData<UserState>(UserState.Idle)

    private val _stateIntent: MutableLiveData<UserIntent> = MutableLiveData()

    var dataState: LiveData<UserState> = Transformations
        .switchMap(_stateIntent) { stateIntent ->
            stateIntent?.let {
                handleStateEvent(stateIntent)
            }
        }

    private fun handleStateEvent(stateIntent: UserIntent): LiveData<UserState> {
        return when (stateIntent) {
            is UserIntent.RegisterPhoneNumber -> {
                UserRepository.registerPhoneNumber(stateIntent.request)
            }

            is UserIntent.VerifyLogin -> {
                UserRepository.verifyLogin(stateIntent.request)
            }

            UserIntent.UserProfile -> {
                UserRepository.getUserProfile()
            }

            is UserIntent.Idle -> {
                _idleState
            }
        }
    }

    private fun setStateEvent(intent: UserIntent) {
        _stateIntent.value = intent
    }

    fun registerPhoneNumber(phone: String) {
        setStateEvent(
            UserIntent.RegisterPhoneNumber(
                RegisterPhoneRequest(
                    phone
                )
            )
        )
    }

    fun saveUserToken(token: String) {
        sharedPreferenceUtil?.saveValue(TOKEN, token)
    }

    fun saveUserId(userId: Int) {
        sharedPreferenceUtil?.saveValue(USER_ID, userId)
    }
    fun deleteUserToken(){
        sharedPreferenceUtil?.deleteValue()
    }


    fun verify(verificationCode: Int, mobile: String,deviceName:String) {
        println("mobile is:" + mobile)
        setStateEvent(
            UserIntent.VerifyLogin(
                VerifyLoginRequest(
                    verificationCode, mobile, deviceName
                )
            )
        )
    }

    fun getUserProfile() {
        setStateEvent(UserIntent.UserProfile)
    }

    fun stateOff() {
        setStateEvent(UserIntent.Idle)
    }
}