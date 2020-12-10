package com.app.buscame.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductSearchFactory {
    private const val BASE_URL = "http://192.168.0.15:8080/api/search/"
    fun getService() : IProductSearchService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(IProductSearchService::class.java)
    }
}
