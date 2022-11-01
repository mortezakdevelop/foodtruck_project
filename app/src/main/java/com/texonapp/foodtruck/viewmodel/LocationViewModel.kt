package com.texonapp.foodtruck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.texonapp.foodtruck.model.UserAddressDialogModel
import com.texonapp.foodtruck.model.UserAddressModel
import com.texonapp.foodtruck.repository.LocationRepository
import com.texonapp.foodtruck.roomDb.entity.UserAddressDialogEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Transformations
//import com.mapbox.search.SearchOptions
//import com.texonapp.foodtruck.mvi.LocationIntent
//import com.texonapp.foodtruck.mvi.LocationState
//import com.texonapp.foodtruck.repository.LocationRepository

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository
): ViewModel() {

    init {
        getAllFromDb()
    }

    private val data: MutableLiveData<List<UserAddressDialogEntity>> = MutableLiveData()
    val liveData: LiveData<List<UserAddressDialogEntity>> = data

    fun insertToNoteModel(userAddressModel: UserAddressModel) {
        viewModelScope.launch (Dispatchers.IO) {
            locationRepository.insertNote(userAddressModel)
        }
    }

    private fun getAllFromDb(){
        viewModelScope.launch (Dispatchers.IO) {
            locationRepository.getAllData().collect(){
                data.postValue(it)
            }
        }
    }

    fun updateModel(userAddressDialogEntity: UserAddressDialogEntity){
        viewModelScope.launch(Dispatchers.IO) {
            locationRepository.updateModel(userAddressDialogEntity)
        }
    }

    fun deleteAddress(userAddressDialogEntity: UserAddressDialogEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            locationRepository.deleteAddress(userAddressDialogEntity)
        }
    }

//    private val _idleState = MutableLiveData<LocationState>(
//        LocationState.Idle
//    )
//
//    private val _stateIntent: MutableLiveData<LocationIntent> = MutableLiveData()
//
//    var dataState: LiveData<LocationState> = Transformations
//        .switchMap(_stateIntent) { stateIntent ->
//            stateIntent?.let {
//                handleStateEvent(stateIntent)
//            }
//        }
//
//    private fun handleStateEvent(stateIntent: LocationIntent): LiveData<LocationState> {
//        return when (stateIntent) {
//            is LocationIntent.UserAddress -> {
//                LocationRepository.getUserAddressByLocation(
//                    stateIntent.longitude,
//                    stateIntent.latitude
//                )
//            }
//
//            is LocationIntent.SuggestLocations -> {
//                LocationRepository.getSuggestLocations(stateIntent.query, stateIntent.options)
//            }
//
//            is LocationIntent.Idle -> {
//                _idleState
//            }
//        }
//    }
//
//    private fun setStateEvent(intent: LocationIntent) {
//        _stateIntent.value = intent
//    }
//
//    fun getUserAddressFromLocation(longitude: Double, latitude: Double) {
//        setStateEvent(LocationIntent.UserAddress(longitude, latitude))
//    }
//
//    fun getSuggestLocations(query: String, options: SearchOptions) {
//        setStateEvent(LocationIntent.SuggestLocations(query, options))
//    }
//
//    fun stateOff() {
//        setStateEvent(LocationIntent.Idle)
//    }
}