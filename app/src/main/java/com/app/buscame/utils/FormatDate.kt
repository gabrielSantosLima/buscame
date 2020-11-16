package com.app.buscame.utils

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

private val PATTERN_TIME_DEFAULT = "hh:mm"
private val PATTERN_DATE_DEFAULT = "dd 'de' MMMM 'de' yyyy"

fun formatDateByPattern(pattern: String, date: Date) : String{
    val locale = Locale("pt", "BR")
    val dateFormatSymbols = DateFormatSymbols(locale)
    val simpleDateFormat = SimpleDateFormat(pattern, dateFormatSymbols)

    val formatedDate =  simpleDateFormat.format(date)
    return formatedDate
}

fun formatDateByDefaultPattern(date: Date) : String{
    val locale = Locale("pt", "BR")
    val dateFormatSymbols = DateFormatSymbols(locale)
    val simpleDateFormat = SimpleDateFormat(PATTERN_DATE_DEFAULT, dateFormatSymbols)

    val formatedDate =  simpleDateFormat.format(date)
    return formatedDate
}
fun formatTimeByDefaultPattern(date: Date) : String{
    val locale = Locale("pt", "BR")
    val dateFormatSymbols = DateFormatSymbols(locale)
    val simpleDateFormat = SimpleDateFormat(PATTERN_TIME_DEFAULT, dateFormatSymbols)

    val formatedDate =  simpleDateFormat.format(date)
    return formatedDate
}
