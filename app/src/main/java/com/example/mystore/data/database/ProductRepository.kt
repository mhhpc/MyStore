package com.example.mystore.data.database

import com.example.mystore.data.api.RetrofitInstance
import com.example.mystore.data.models.Product
import retrofit2.HttpException
import java.io.IOException

class ProductRepository(private val productDao: ProductDao) {

    suspend fun getProducts(): List<Product> {
        return try {
            val response = RetrofitInstance.api.getProducts()
            productDao.insertAll(response) // Save in Room
            response
        } catch (e: IOException) { // If interntet not exist
            productDao.getAllProducts()
        } catch (e: HttpException) { // If server Error
            productDao.getAllProducts()
        }
    }
}

