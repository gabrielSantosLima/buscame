package com.app.buscame.dto

import java.io.File

data class ProductDto(
    val term : String,
    val title : String,
    val price : Double,
    val description: String,
    val url : String,
    val image : File?
)