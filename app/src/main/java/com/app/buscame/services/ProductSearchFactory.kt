package com.app.buscame.services

import retrofit2.Retrofit

class ProductSearchFactory {
    companion object{
        private val BASE_URL = "http://localhost:8080/api/search/"
        fun getService() : IProductSearchService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(IProductSearchService::class.java)
        }
    }
}
