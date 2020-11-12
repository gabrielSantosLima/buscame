package com.app.buscame.dto

import java.util.*

data class HistoryDto(
    var id: String?,
    val term: String,
    val url: String,
    val date: Date
)

// List<HistoryDto>