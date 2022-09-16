package com.sts.viktor_test.utils

import java.util.*

const val DAY_START_TIME = "T00:00:00Z"
const val DAY_END_TIME = "T11:59:59Z"

fun formatDate(year : Int, month : Int, day : Int) : String{

    return year.toString() + "-" + intToFormattedDate(month + 1) + "-" + intToFormattedDate(day)
}

fun getCurrentDate() : String {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return year.toString() + "-" + intToFormattedDate(month + 1) + "-" + intToFormattedDate(day)
}

fun intToFormattedDate(value : Int) : String{
    if (value < 9){
        return "0" + value.toString()
    }

    return value.toString()
}