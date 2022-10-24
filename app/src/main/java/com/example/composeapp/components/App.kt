package com.example.composeapp

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapp.components.BeersTab


enum class DisplayTab {
    SEARCH, FAVORITES
}

var BeersAPI = BeerController()

@Preview
@Composable
fun App() {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        mapOf(
            "id" to DisplayTab.SEARCH,
            "text" to "search",
            "icon" to Icons.Filled.Search
        ),
        mapOf(
            "id" to DisplayTab.FAVORITES,
            "text" to "favourites",
            "icon" to Icons.Filled.Favorite
        ),
    )

    Scaffold(topBar = {
        TopAppBar(title = { Text("Have a nice beer") })
    }, content = {
        BeersTab(items[selectedItem]["id"] as DisplayTab)
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
