package com.example.mystore.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mystore.ProductImage
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

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { showDialog = true },
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
    if (showDialog) {
        ProductDialog(product, onDismiss = { showDialog = false })
    }
}

@Composable
fun ProductDialog(product: Product, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Product Details") },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ProductImage(product.image)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "ID: ${product.id}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Title: ${product.title}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Price: $${product.price}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Description: ${product.description}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Category: ${product.category}")
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "Rate: ${product.rating.rate}")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Count: ${product.rating.count}")
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(text = "Close")
            }
        }
    )
}