package com.texonapp.foodtruck.viewmodel



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
import com.texonapp.foodtruck.mvi.intent.GoodFoodMenuIntent
import com.texonapp.foodtruck.mvi.state.GoodFoodMenuState
import com.texonapp.foodtruck.repository.GoodFoodMenuRepository

class GoodFoodMenuViewModel:MainViewModel() {

    private val _idleState = MutableLiveData<GoodFoodMenuState>(GoodFoodMenuState.Idle)
    private val _stateIntent: MutableLiveData<GoodFoodMenuIntent> = MutableLiveData()

    var dataState: LiveData<GoodFoodMenuState> = Transformations
        .switchMap(_stateIntent) { stateIntent ->
            stateIntent?.let {
                handleStateEvent(stateIntent)
            }
        }

    private fun handleStateEvent(stateIntent: GoodFoodMenuIntent): LiveData<GoodFoodMenuState> {
        return when (stateIntent) {
            is GoodFoodMenuIntent.GetUserCart -> {
                GoodFoodMenuRepository.getUserCart()
            }
            is GoodFoodMenuIntent.IncreaseFoodQuantity -> {
                GoodFoodMenuRepository.increaseFoodQuantity(stateIntent.request)
            }
            is GoodFoodMenuIntent.ReduceFoodQuantity -> {
                GoodFoodMenuRepository.reduceFoodQuantity(stateIntent.request)
            }

            else -> {_idleState}
        }
    }

    private fun setStateEvent(intent: GoodFoodMenuIntent) {
        _stateIntent.value = intent
    }

    fun getUserCart() {
        setStateEvent(GoodFoodMenuIntent.GetUserCart)
    }

    fun increaseFoodQuantity(foodId: String) {
        setStateEvent(GoodFoodMenuIntent.IncreaseFoodQuantity(FoodIdRequest(foodId.toInt())))
    }

    fun reduceFoodQuantity(itemId: String) {
        setStateEvent(GoodFoodMenuIntent.ReduceFoodQuantity(ItemIdRequest(itemId.toInt())))
    }
}


//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Transformations
//import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
//import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
//import com.texonapp.foodtruck.mvi.intent.GoodFoodMenuIntent
//import com.texonapp.foodtruck.mvi.state.GoodFoodMenuState
//import com.texonapp.foodtruck.repository.GoodFoodMenuRepository
//
//class GoodFoodMenuViewModel:MainViewModel() {
//
//    private val _idleState = MutableLiveData<GoodFoodMenuState>(GoodFoodMenuState.Idle)
//    private val _stateIntent: MutableLiveData<GoodFoodMenuIntent> = MutableLiveData()
//
//    var dataState: LiveData<GoodFoodMenuState> = Transformations
//        .switchMap(_stateIntent) { stateIntent ->
//            stateIntent?.let {
//                handleStateEvent(stateIntent)
//            }
//        }
//
//    private fun handleStateEvent(stateIntent: GoodFoodMenuIntent): LiveData<GoodFoodMenuState> {
//        return when (stateIntent) {
//            is GoodFoodMenuIntent.GetUserCart -> {
//                GoodFoodMenuRepository.getUserCart()
//            }
//            is GoodFoodMenuIntent.IncreaseFoodQuantity -> {
//                GoodFoodMenuRepository.increaseFoodQuantity(stateIntent.request)
//            }
//            is GoodFoodMenuIntent.ReduceFoodQuantity -> {
//                GoodFoodMenuRepository.reduceFoodQuantity(stateIntent.request)
//            }
//
//            else -> {_idleState}
//        }
//    }
//
//    private fun setStateEvent(intent: GoodFoodMenuIntent) {
//        _stateIntent.value = intent
//    }
//
//    fun getUserCart() {
//        setStateEvent(GoodFoodMenuIntent.GetUserCart)
//    }
//
//    fun increaseFoodQuantity(foodId: String) {
//        setStateEvent(GoodFoodMenuIntent.IncreaseFoodQuantity(FoodIdRequest(foodId)))
//    }
//
//    fun reduceFoodQuantity(itemId: String) {
//        setStateEvent(GoodFoodMenuIntent.ReduceFoodQuantity(ItemIdRequest(itemId)))
//    }
//}