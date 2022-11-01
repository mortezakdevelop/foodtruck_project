package com.texonapp.foodtruck.adapter.cardListener

import android.view.View
import androidx.constraintlayout.utils.widget.ImageFilterButton
import com.texonapp.foodtruck.roomDb.entity.UserAddressDialogEntity

interface LocationAddressClickListener {
    fun onItemRVClickListener(userAddressDialogEntity: UserAddressDialogEntity){

    }

    fun onDeleteItemListener(imageFilterButton: View,userAddressDialogEntity: UserAddressDialogEntity){

    }
}