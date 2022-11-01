package com.texonapp.foodtruck.roomDb

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.texonapp.foodtruck.model.UserAddressDialogModel
import com.texonapp.foodtruck.model.UserAddressModel
import java.lang.reflect.Type

class NoteTypeConverter {
    @TypeConverter
    fun toJson(userAddressModel: UserAddressModel):String?{

        if (userAddressModel == null){
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<UserAddressModel?>(){}.type
        return gson.toJson(userAddressModel,type)

    }

    @TypeConverter
    fun toDataClass(address:String?):UserAddressModel?{
        if(address == null){
            return null
        }

        val gson = Gson()
        val type:Type = object :TypeToken<UserAddressModel?>(){}.type
        return gson.fromJson(address,type)
    }
}