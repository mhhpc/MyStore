package com.example.mystore.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.data.api.RetrofitInstance
import com.example.mystore.data.models.Product
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                _products.value = RetrofitInstance.api.getProducts()
            }catch (e: Exception){
                //Handle Error
            }
        }
    }
}