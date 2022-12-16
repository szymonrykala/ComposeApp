package com.example.composeapp

import android.content.Context
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.composeapp.components.BeerCard
import com.example.composeapp.db.BeerDatabase
import com.example.composeapp.db.Constants
import kotlinx.coroutines.launch

enum class DisplayTab {
    SEARCH, FAVORITES
}

val BeersView = BeersViewModel()

data class NavButton(
    var id: DisplayTab,
    var text: String,
    var icon: ImageVector
)

var navButtons = listOf<NavButton>(
    NavButton(DisplayTab.SEARCH, "search", Icons.Filled.Search),
    NavButton(DisplayTab.FAVORITES, "favourites", Icons.Filled.Favorite)
)


@Composable
fun App(
    beerDB: BeerDatabase,
    context: Context = LocalContext.current,
    beersView: BeersViewModel = viewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    var selectedItem by remember { mutableStateOf<NavButton>(navButtons[0]) }
    val beers by beersView.beers.collectAsState()


    Room.databaseBuilder(context,BeerDatabase::class.java, Constants.BEER_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(scaffoldState) {
        coroutineScope.launch {
//            BeersView.setFavList(listOf(1, 3, 5))
//            BeersView.loadBeersFromWeb()
        }
    }


    Scaffold(topBar = {
        TopAppBar(title = { Text("Have a nice beer") })
    }, content = {
//        BeersTab(items[selectedItem]["id"] as DisplayTab)
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(
                items = (beers),
                key = { beer -> beer.id }
            ) { beer ->
                BeerCard(beer)
            }
        }
    },
        bottomBar = {
            BottomNavigation {
//                navButtons.map {  }
                navButtons.forEach { item ->
                    BottomNavigationItem(selected = (selectedItem == item),
                        onClick = { selectedItem = item },
                        label = { Text(item.text) },
                        icon = {
                            Icon(item.icon, item.text)
                        })
                }
            }
        })
}
