package com.app.buscame.services

import com.app.buscame.dto.ImageDto
import com.app.buscame.dto.ProductDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IProductSearchService {
    @POST("image")
    fun searchByImage(@Body image: ImageDto): Call<List<ProductDto>>
    @POST("text")
    fun searchByText(@Body text: String): Call<List<ProductDto>>
}

