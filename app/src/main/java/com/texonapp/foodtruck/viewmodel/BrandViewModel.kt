package com.texonapp.foodtruck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.texonapp.foodtruck.data.requestModel.BrandDetailRequest
import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
import com.texonapp.foodtruck.mvi.BrandIntent
import com.texonapp.foodtruck.mvi.BrandState
import com.texonapp.foodtruck.mvi.intent.GoodFoodMenuIntent
import com.texonapp.foodtruck.mvi.state.GoodFoodMenuState
import com.texonapp.foodtruck.repository.BrandsRepository
import com.texonapp.foodtruck.repository.GoodFoodMenuRepository

class BrandViewModel : MainViewModel() {
    private val _idleState = MutableLiveData<BrandState>(BrandState.Idls)
    private val _stateIntent: MutableLiveData<BrandIntent> = MutableLiveData()

    private val _idleState2 = MutableLiveData<GoodFoodMenuState>(GoodFoodMenuState.Idle)
    private val _stateIntent2: MutableLiveData<GoodFoodMenuIntent> = MutableLiveData()

    var dataState2: LiveData<GoodFoodMenuState> = Transformations
        .switchMap(_stateIntent2) { stateIntent2 ->
            stateIntent2?.let {
                handleStateEvent2(stateIntent2)
            }
        }

    var dataState: LiveData<BrandState> = Transformations
        .switchMap(_stateIntent) { stateIntent ->
            stateIntent?.let {
                handleStateEvent(stateIntent)
            }
        }

    //for reduce and increase logic
    private fun handleStateEvent2(stateIntent: GoodFoodMenuIntent): LiveData<GoodFoodMenuState> {
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

            else -> {_idleState2}
        }
    }

    private fun handleStateEvent(stateIntent: BrandIntent): LiveData<BrandState> {
        return when (stateIntent) {
            is BrandIntent.GetBrands -> {
                BrandsRepository.getBrands()
            }

            is BrandIntent.GetBrandDetail -> {
                BrandsRepository.getBrandDetail(stateIntent.request)
            }

            is BrandIntent.GetBrandFood -> {
                BrandsRepository.getBrandFood(stateIntent.request)
            }

            is BrandIntent.Idle -> {
                _idleState
            }
        }
    }

    private fun setStateEvent(intent: GoodFoodMenuIntent) {
        _stateIntent2.value = intent
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

    private fun setStateEvent(intent: BrandIntent) {
        _stateIntent.value = intent
    }

    fun getBrand() {
        setStateEvent(BrandIntent.GetBrands)
    }

    fun getBrandFood(id: Int) {
        setStateEvent(
            BrandIntent.GetBrandFood(
                BrandDetailRequest(
                    id
                )
            )
        )
    }

    fun getBrandDetail(id: Int) {
        setStateEvent(
            BrandIntent.GetBrandDetail(
                BrandDetailRequest(
                    id
                )
            )
        )
    }
    fun stateOff() {
        setStateEvent(BrandIntent.Idle)
    }
    init {
        //getViewPagerData()
    }
}



//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Transformations
//import com.texonapp.foodtruck.data.requestModel.BrandDetailRequest
//import com.texonapp.foodtruck.data.requestModel.FoodIdRequest
//import com.texonapp.foodtruck.data.requestModel.ItemIdRequest
//import com.texonapp.foodtruck.mvi.*
//import com.texonapp.foodtruck.mvi.intent.GoodFoodMenuIntent
//import com.texonapp.foodtruck.mvi.state.GoodFoodMenuState
//import com.texonapp.foodtruck.repository.BrandsRepository
//import com.texonapp.foodtruck.repository.GoodFoodMenuRepository
//
//class BrandViewModel : MainViewModel() {
//    private val _idleState = MutableLiveData<BrandState>(BrandState.Idls)
//    private val _stateIntent: MutableLiveData<BrandIntent> = MutableLiveData()
//
//    private val _idleState2 = MutableLiveData<GoodFoodMenuState>(GoodFoodMenuState.Idle)
//    private val _stateIntent2: MutableLiveData<GoodFoodMenuIntent> = MutableLiveData()
//
//    var dataState2: LiveData<GoodFoodMenuState> = Transformations
//        .switchMap(_stateIntent2) { stateIntent2 ->
//            stateIntent2?.let {
//                handleStateEvent2(stateIntent2)
//            }
//        }
//
//    var dataState: LiveData<BrandState> = Transformations
//        .switchMap(_stateIntent) { stateIntent ->
//            stateIntent?.let {
//                handleStateEvent(stateIntent)
//            }
//        }
//
//    //for reduce and increase logic
//    private fun handleStateEvent2(stateIntent: GoodFoodMenuIntent): LiveData<GoodFoodMenuState> {
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
//            else -> {_idleState2}
//        }
//    }
//
//    private fun handleStateEvent(stateIntent: BrandIntent): LiveData<BrandState> {
//        return when (stateIntent) {
//            is BrandIntent.GetBrands -> {
//                BrandsRepository.getBrands()
//            }
//
//            is BrandIntent.GetBrandDetail -> {
//                BrandsRepository.getBrandDetail(stateIntent.request)
//            }
//
//            is BrandIntent.GetBrandFood -> {
//                BrandsRepository.getBrandFood(stateIntent.request)
//            }
//
//            is BrandIntent.Idle -> {
//                _idleState
//            }
//        }
//    }
//
//    private fun setStateEvent(intent: GoodFoodMenuIntent) {
//        _stateIntent2.value = intent
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
//
//    private fun setStateEvent(intent: BrandIntent) {
//        _stateIntent.value = intent
//    }
//
//    fun getBrand() {
//        setStateEvent(BrandIntent.GetBrands)
//    }
//
//    fun getBrandFood(id: Int) {
//        setStateEvent(
//            BrandIntent.GetBrandFood(
//                BrandDetailRequest(
//                    id
//                )
//            )
//        )
//    }
//
//    fun getBrandDetail(id: Int) {
//        setStateEvent(
//            BrandIntent.GetBrandDetail(
//                BrandDetailRequest(
//                    id
//                )
//            )
//        )
//    }
//    fun stateOff() {
//        setStateEvent(BrandIntent.Idle)
//    }
//    init {
//        //getViewPagerData()
//    }
//}
//
//
