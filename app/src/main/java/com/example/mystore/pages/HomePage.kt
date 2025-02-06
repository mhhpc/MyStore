package com.example.mystore.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mystore.R
import com.example.mystore.data.models.Product
import com.example.mystore.ui.theme.PurpleGrey40
import com.example.mystore.viewModel.PostViewModel

@Composable
fun HomePage(viewModel: PostViewModel = PostViewModel(), paddingValues: PaddingValues) {

    val products by viewModel.products

    if (products.isEmpty()) {
        CircularProgressIndicator()
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(products) {
                ProductCard(it)
            }
        }
    }

}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(PurpleGrey40)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Product #${product.id}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}