package com.texonapp.foodtruck.repository

import com.texonapp.foodtruck.model.UserAddressDialogModel
import com.texonapp.foodtruck.model.UserAddressModel
import com.texonapp.foodtruck.roomDb.AppDatabase
import com.texonapp.foodtruck.roomDb.dao.UserAddressDialogDao
import com.texonapp.foodtruck.roomDb.entity.UserAddressDialogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.mapbox.geojson.Point
//import com.mapbox.search.*
//import com.mapbox.search.result.SearchResult
//import com.mapbox.search.result.SearchSuggestion
//import com.texonapp.foodtruck.mvi.LocationState

class LocationRepository @Inject constructor(
    appDatabase: AppDatabase
){
    private val roomDao:UserAddressDialogDao = appDatabase.userAddressDialogDao()

    fun insertNote(userAddressModel: UserAddressModel){
        val userAddressDialogEntity = UserAddressDialogEntity(0,userAddressModel)
        roomDao.insert(userAddressDialogEntity)
    }

    fun getAllData(): Flow<List<UserAddressDialogEntity>> {
        return roomDao.getAll()
    }
}

//object LocationRepository {
//
//    private val searchEngine by lazy {
//        MapboxSearchSdk.getSearchEngine()
//    }
//
//    fun getUserAddressByLocation(longitude: Double, latitude: Double): LiveData<LocationState> {
//        val userAddress = MutableLiveData<LocationState>()
//        searchEngine.search(
//            ReverseGeoOptions(
//                center = Point.fromLngLat(
//                    longitude, latitude
//                ), limit = 1
//            ),
//            object : SearchCallback {
//                override fun onError(e: Exception) {
//                    userAddress.value = LocationState.UserAddressByLocation(null, e.message)
//                }
//
//                override fun onResults(
//                    results: List<SearchResult>,
//                    responseInfo: ResponseInfo
//                ) {
//                    userAddress.value =
//                        LocationState.UserAddressByLocation(results[0].address, null)
//                }
//
//            }
//        )
//        return userAddress
//    }
//
//    fun getSuggestLocations(query: String, options: SearchOptions): LiveData<LocationState> {
//        val userAddress = MutableLiveData<LocationState>()
//        searchEngine.search(
//            query,
//            options,
//            object : SearchSelectionCallback {
//                override fun onCategoryResult(
//                    suggestion: SearchSuggestion,
//                    results: List<SearchResult>,
//                    responseInfo: ResponseInfo
//                ) {
//
//                }
//
//                override fun onError(e: Exception) {
//                    userAddress.value = LocationState.SuggestLocations(null, e.message)
//                }
//
//                override fun onResult(
//                    suggestion: SearchSuggestion,
//                    result: SearchResult,
//                    responseInfo: ResponseInfo
//                ) {
//
//                }
//
//                override fun onSuggestions(
//                    suggestions: List<SearchSuggestion>,
//                    responseInfo: ResponseInfo
//                ) {
//                    userAddress.value = LocationState.SuggestLocations(suggestions, null)
//                }
//
//
//            }
//        )
//        return userAddress
//    }
//}