package com.example.mystore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.mystore.data.database.MystoreDatabase
import com.example.mystore.data.database.ProductRepository
import com.example.mystore.ui.theme.MyStoreTheme
import com.example.mystore.viewModel.PostViewModel
import com.example.mystore.viewModel.PostViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = MystoreDatabase.getDatabase(this)
        val repository = ProductRepository(database.productDao())

        val viewModelFactory = PostViewModelFactory(repository)
        val viewModel: PostViewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MyStoreTheme {
                MainScreen(viewModel)
            }
        }
    }
}