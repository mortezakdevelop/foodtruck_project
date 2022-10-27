package com.texonapp.foodtruck.mvi.intent

import com.texonapp.foodtruck.data.responseModel.VouchersResponse

sealed class VouchersIntent{
    object GetVouchers : VouchersIntent()

    object Idle : VouchersIntent()
}
