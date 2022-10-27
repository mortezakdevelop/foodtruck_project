package com.texonapp.foodtruck.util

import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.util.MainApplication.Companion.applicationContext
import java.util.*

object CurrencyUtil {

    fun splitPrice(price: String?): String {
        if (price == null) return ""
        var newPrice = price
        if (!isNumeric(newPrice)) {
            return newPrice
        }
        newPrice = transformCurrency(newPrice)
        newPrice = newPrice.split("\\.".toRegex()).toTypedArray()[0]
        val lst = StringTokenizer(newPrice, ".")
        var str1: String = newPrice
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

    private fun splitPrice(price: Double?): String {
        if (price == null) return ""
        var newPrice = price.toString()
        newPrice = newPrice.split("\\.".toRegex()).toTypedArray()[0]
        val lst = StringTokenizer(newPrice, ".")
        var str1: String = newPrice
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

    fun splitPriceWithStartCurrency(price: Double?): String {
        return "RM" + " " + splitPrice(
            price
        )
    }

    fun splitPriceWithEndCurrency(price: Double?): String {
        return splitPrice(price) + " " + "RM"

    }


//    fun splitPriceWithEndCurrency(price: Double?): String {
//        return splitPrice(price) + " " + applicationContext()
//            .getString(R.string.currency)
//    }

    private fun isNumeric(str: String): Boolean {
        return when (str.toIntOrNull()) {
            null -> false
            else -> true
        }
    }

    private fun transformCurrency(price: String): String {
        return (price.toInt() * 0.1).toString()
    }

}