package com.texonapp.foodtruck.mvi.state

import com.texonapp.foodtruck.data.responseModel.VouchersResponse
import com.texonapp.foodtruck.mvi.intent.VouchersIntent

sealed class VouchersState{

    data class GetVouchers(
        val response: VouchersResponse?,
        val errorMessage: String?
    ): VouchersState()

    object Idle : VouchersState()
}
