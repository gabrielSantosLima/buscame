package com.app.buscame.services

import com.app.buscame.dto.ProductDto
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductSearchService {

    @POST("image")
    fun searchByImage(@Body image: RequestBody): Call<List<ProductDto>>

    @POST("text")
    fun searchByText(@Query("text") text: String): Call<List<ProductDto>>
}

