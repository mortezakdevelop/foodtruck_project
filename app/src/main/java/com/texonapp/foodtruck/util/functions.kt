package com.texonapp.foodtruck.util

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.Html
import java.lang.ref.WeakReference
import java.util.*

fun statusBarHeight(context: Context?) : Int {
    WeakReference<Context>(context).get()?.let {
        val resourceId: Int = it.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            return it.resources.getDimensionPixelSize(resourceId)
        }
    }
    return 0
}

fun splitPrice(price_: String?): String {
    var price = price_ ?: return ""
    if (!isNumeric(price)) {
        return price
    }
    price = price.split("\\.".toRegex()).toTypedArray()[0]
    val lst = StringTokenizer(price, ".")
    var str1: String = price
    var str2 = ""
    if (lst.countTokens() > 1) {
        str1 = lst.nextToken()
        str2 = lst.nextToken()
    }
    var str3 = ""
    var i = 0
    var j = -1 + str1.length
    if (str1.isNotEmpty() && str1[-1 + str1.length] == '.') {
        j--
        str3 = "."
    }
    var k = j
    while (true) {
        if (k < 0) {
            if (str2.isNotEmpty()) str3 = "$str3.$str2"
            return str3
        }
        if (i == 3) {
            str3 = ",$str3"
            i = 0
        }
        str3 = str1[k].toString() + str3
        i++
        k--
    }
}

fun isNumeric(str: String): Boolean {
    return when (str.toIntOrNull()) {
        null -> false
        else -> true
    }
}