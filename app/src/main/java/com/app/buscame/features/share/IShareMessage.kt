package com.app.buscame.features.share

import com.app.buscame.dto.ProductDto

interface IShareMessage {
    fun shareMessage(text: String)
    fun shareProduct(productDto: ProductDto)
}