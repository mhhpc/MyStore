package com.example.mystore


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mystore.pages.HomePage
import com.example.mystore.pages.ProfilePage


@Composable
fun MainScreen (modifier: Modifier = Modifier){

    val navItemList = listOf(
        NavItem(stringResource(R.string.b_navigation_home), Icons.Default.ShoppingCart),
        NavItem(stringResource(R.string.b_navigation_profile), Icons.Default.Person)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = "Icon")
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                        )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex, innerPadding)
    }
}

@Composable
fun ContentScreen (modifier: Modifier = Modifier, selectedIndex : Int, paddingValues: androidx.compose.foundation.layout.PaddingValues){
    when(selectedIndex){
        0-> HomePage(paddingValues = paddingValues)
        1-> ProfilePage()
    }
}