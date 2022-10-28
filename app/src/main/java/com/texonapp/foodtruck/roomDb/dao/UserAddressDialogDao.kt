package com.texonapp.foodtruck.roomDb.dao

import androidx.room.*
import com.texonapp.foodtruck.roomDb.entity.UserAddressDialogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAddressDialogDao {

    @Query("SELECT * FROM address_table")
    fun getAll(): Flow<List<UserAddressDialogEntity>>


    @Insert
    fun insert(userAddressDialogEntity: UserAddressDialogEntity)


    @Delete
    fun delete(userAddressDialogEntity: UserAddressDialogEntity)


    @Update
    fun update(userAddressDialogEntity: UserAddressDialogEntity)
}