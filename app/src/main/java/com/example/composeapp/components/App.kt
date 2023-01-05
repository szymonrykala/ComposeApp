package com.example.composeapp

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composeapp.components.BeerCard
import com.example.composeapp.db.BeerDatabase
import com.example.composeapp.db.BeerModel
import kotlinx.coroutines.launch

enum class DisplayTab {
    SEARCH, SAVED
}

data class NavButton(
    var id: DisplayTab, var text: String, var icon: ImageVector
)

var navButtons = listOf<NavButton>(
    NavButton(DisplayTab.SEARCH, "search", Icons.Filled.Search),
    NavButton(DisplayTab.SAVED, "saved", Icons.Filled.ShoppingCart)
)


@Composable
fun App(
    beerDB: BeerDatabase,
    beersView: BeersViewModel = BeersViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    var selectedItem by remember { mutableStateOf<NavButton>(navButtons[0]) }
    val beers by beersView.beers.collectAsState()

    fun readSavedBeersIds(): MutableList<Int> {
        return beerDB.doa().getAllBeersIds()
    }

    val saveBeer: (beer: BeerModel) -> Unit = { beer ->
        beerDB.doa().insertBeer(beer)
        beersView.addToSavedList(beer.id)

    }

    val deleteBeer: (beer: BeerModel) -> Unit = { beer ->
        beerDB.doa().deleteBeer(beer)
        beersView.removeFromSavedList(beer.id)
    }

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(scaffoldState) {
        coroutineScope.launch {
            beersView.setSavedList(readSavedBeersIds())
        }
    }


    Scaffold(topBar = {
        TopAppBar(title = { Text("Have a nice beer") })
    }, bottomBar = {
        BottomNavigation {
            navButtons.forEach { item ->
                BottomNavigationItem(
                    selected = (selectedItem == item),
                    onClick = { selectedItem = item },
                    label = { Text(item.text) },
                    icon = { Icon(item.icon, item.text) }
                )
            }
        }
    }, content = {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(
                items = (when (selectedItem.id) {
                    DisplayTab.SAVED -> beerDB.doa().getAllBeers()
                    DisplayTab.SEARCH -> beers
                }),
                key = { beer -> beer.id }
            ) { beer ->
                BeerCard(beer, saveBeer, deleteBeer)
            }
        }
    })
}
