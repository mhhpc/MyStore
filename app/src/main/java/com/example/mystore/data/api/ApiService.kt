package com.example.mystore.data.api

import com.example.mystore.data.models.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>

}