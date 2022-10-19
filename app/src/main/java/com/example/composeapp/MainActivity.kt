package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapp.components.BeerCard
import com.example.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Preview
@Composable
fun App() {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        mapOf(
            "text" to "search", "icon" to Icons.Filled.Search
        ),
        mapOf(
            "text" to "favourites", "icon" to Icons.Filled.Favorite
        ),
    )

    Scaffold(topBar = {
        TopAppBar(title = { Text("Have a nice beer") } )
    }, content = {
        BeersTab()
    },
        bottomBar = {
            BottomNavigation {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(selected = (selectedItem == index),
                        onClick = { selectedItem = index },
                        label = { Text(item["text"] as String) },
                        icon = {
                            Icon(
                                item["icon"] as ImageVector,
                                contentDescription = item["text"] as String
                            )
                        })
                }
            }
        })
}


@Composable
fun BeersTab() {
    var beers = BeerController()

    Box {
//        Text("Search Screen")
        Column {
            beers.getAllBeers().forEach { beer ->
                BeerCard(beer = beer)
            }
        }
    }
}
