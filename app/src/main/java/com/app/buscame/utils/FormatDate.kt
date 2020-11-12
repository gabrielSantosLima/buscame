package com.app.buscame.utils

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

fun formatDateByPattern(pattern: String, date: Date) : String{
    val locale = Locale("pt", "BR")
    val dateFormatSymbols = DateFormatSymbols(locale)
    val simpleDateFormat = SimpleDateFormat(pattern, dateFormatSymbols)

    val formatedDate =  simpleDateFormat.format(date)
    return formatedDate
}
