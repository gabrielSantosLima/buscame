package com.app.buscame.utils

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

private val PATTERN_TIME_DEFAULT = "hh:mm"
private val PATTERN_DATE_DEFAULT = "dd 'de' MMMM 'de' yyyy"
private val PATTERN_TO_YEAR = "yyyy"
private val PATTERN_TO_DAY = "dd"
private val PATTERN_TO_MONTH = "MM"

private fun formatDateByPattern(pattern: String, date: Date) : String{
    val locale = Locale("pt", "BR")
    val dateFormatSymbols = DateFormatSymbols(locale)
    val simpleDateFormat = SimpleDateFormat(pattern, dateFormatSymbols)
    return simpleDateFormat.format(date)
}

fun formatDateByDefaultPattern(date: Date) : String{
    return formatDateByPattern(PATTERN_DATE_DEFAULT,date)
}

fun formatTimeByDefaultPattern(date: Date) : String{
    return formatDateByPattern(PATTERN_TIME_DEFAULT,date)
}

fun formatToYear(date: Date) : String{
    return formatDateByPattern(PATTERN_TO_YEAR,date)
}

fun formatToDay(date: Date) : String{
    return formatDateByPattern(PATTERN_TO_DAY,date)
}

fun formatToMonth(date: Date) : String{
    return formatDateByPattern(PATTERN_TO_MONTH,date)
}
