package com.app.buscame.dto

import java.util.*

data class FavoriteDto(
    var id: String?,
    val product : ProductDto,
    val date: Date
)