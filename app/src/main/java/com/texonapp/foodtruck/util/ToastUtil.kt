package com.texonapp.foodtruck.util

import android.widget.Toast
import com.texonapp.foodtruck.util.MainApplication.Companion.applicationContext

object ToastUtil {

    private var mToast: Toast? = null

    fun showToast(message: String?) {
        if (mToast == null) {
            mToast = Toast.makeText(applicationContext(), message, Toast.LENGTH_SHORT)
        } else {
            mToast!!.cancel()
            mToast = Toast.makeText(applicationContext(), message, Toast.LENGTH_SHORT)
            mToast!!.duration = Toast.LENGTH_SHORT
        }
        mToast!!.show()

    }

    fun showLongToast(message: String?) {
        if (mToast == null) {
            mToast = Toast.makeText(applicationContext(), message, Toast.LENGTH_LONG)
        } else {
            mToast!!.cancel()
            mToast = Toast.makeText(applicationContext(), message, Toast.LENGTH_LONG)
            mToast!!.duration = Toast.LENGTH_LONG
        }
        mToast!!.show()

    }

    fun showToast(message: Int?) {
        var errorMessage = applicationContext().getString(message!!);
        if (mToast == null) {
            mToast = Toast.makeText(applicationContext(), errorMessage, Toast.LENGTH_SHORT)
        } else {
            mToast!!.cancel()
            mToast = Toast.makeText(applicationContext(), errorMessage, Toast.LENGTH_SHORT)
            mToast!!.duration = Toast.LENGTH_SHORT
        }
        mToast!!.show()

    }
}