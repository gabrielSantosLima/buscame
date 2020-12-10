package com.app.buscame.dto

data class ProductDto(
    var id: String?,
    val term : String,
    val title : String,
    val price : Double?,
    val description: String,
    val url : String,
    val image : String?
)
