package com.example.mystore.data.database

import com.example.mystore.data.api.RetrofitInstance
import com.example.mystore.data.models.Product
import retrofit2.HttpException
import java.io.IOException

class ProductRepository(private val productDao: ProductDao) {

    suspend fun getProducts(): List<Product> {
        return try {
            val response = RetrofitInstance.api.getProducts()
            productDao.insertAll(response) // ذخیره در Room
            response
        } catch (e: IOException) { // اگر اینترنت قطع باشد
            productDao.getAllProducts()
        } catch (e: HttpException) { // اگر خطای سرور رخ دهد
            productDao.getAllProducts()
        }
    }
}

