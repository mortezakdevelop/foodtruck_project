package com.texonapp.foodtruck.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.texonapp.foodtruck.roomDb.dao.UserAddressDialogDao
import com.texonapp.foodtruck.roomDb.entity.UserAddressDialogEntity

@TypeConverters(NoteTypeConverter::class)
@Database(entities = [UserAddressDialogEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userAddressDialogDao():UserAddressDialogDao
}