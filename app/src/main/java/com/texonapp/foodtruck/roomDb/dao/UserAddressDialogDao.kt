package com.texonapp.foodtruck.roomDb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.texonapp.foodtruck.roomDb.entity.UserAddressDialogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAddressDialogDao {

    @Query("SELECT * FROM address_table")
    fun getAll(): Flow<List<UserAddressDialogEntity>>


    @Insert
    fun insert(userAddressDialogEntity: UserAddressDialogEntity)
}