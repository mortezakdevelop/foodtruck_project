package com.texonapp.foodtruck.roomDb.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.texonapp.foodtruck.model.UserAddressDialogModel
import com.texonapp.foodtruck.model.UserAddressModel

@Entity(tableName = "address_table")
data class UserAddressDialogEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var userAddressModel: UserAddressModel
):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        TODO("userAddressDialogModel")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserAddressDialogEntity> {
        override fun createFromParcel(parcel: Parcel): UserAddressDialogEntity {
            return UserAddressDialogEntity(parcel)
        }

        override fun newArray(size: Int): Array<UserAddressDialogEntity?> {
            return arrayOfNulls(size)
        }
    }
}
