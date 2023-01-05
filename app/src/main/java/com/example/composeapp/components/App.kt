package com.example.composeapp

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import com.example.composeapp.components.BeerCard
import com.example.composeapp.db.BeerDatabase
import com.example.composeapp.db.BeerEntity
import kotlinx.coroutines.launch

enum class DisplayTab {
    SEARCH, SAVED
}

//val BeersView = BeersViewModel()

data class NavButton(
    var id: DisplayTab,
    var text: String,
    var icon: ImageVector
)

var navButtons = listOf<NavButton>(
    NavButton(DisplayTab.SEARCH, "search", Icons.Filled.Search),
    NavButton(DisplayTab.SAVED, "saved", Icons.Filled.ShoppingCart)
)


@Composable
fun App(
    beerDB: BeerDatabase,
    context: Context = LocalContext.current,
    beersView: BeersViewModel = BeersViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    var selectedItem by remember { mutableStateOf<NavButton>(navButtons[0]) }
    val beers by beersView.beers.collectAsState()
//    val savedBeers by remember { listOf<BeerEntity>() }
    var savedBeers = mutableListOf<BeerEntity>()


    fun loadSavedBeers() {
        savedBeers = beerDB.doa().getAllBeers()
    }

    fun readSavedBeersIds(): MutableList<Int> {
        return beerDB.doa().getAllBeersIds()
    }

    val saveBeer: (beer: BeerEntity) -> Unit = { beer ->
        beerDB.doa().insertBeer(beer)
        beersView.addToSavedList(beer.id)
        loadSavedBeers()
    }

    val deleteBeer: (beer: BeerEntity) -> Unit = { beer ->
        beerDB.doa().deleteBeer(beer)
        beersView.removeFromSavedList(beer.id)
        loadSavedBeers()
    }


    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(scaffoldState) {
        coroutineScope.launch {
//            BeersView.setFavList(listOf(1, 3, 5))
//            BeersView.loadBeersFromWeb()
            loadSavedBeers()
            beersView.setFavList(readSavedBeersIds())
        }
    }


    Scaffold(topBar = {
        TopAppBar(title = { Text("Have a nice beer") })
    }, bottomBar = {
        BottomNavigation {
//                navButtons.map {  }
            navButtons.forEach { item ->
                BottomNavigationItem(selected = (selectedItem == item),
                    onClick = {
                        selectedItem = item

                    },
                    label = { Text(item.text) },
                    icon = {
                        Icon(item.icon, item.text)
                    })
            }
        }
    },
        content = {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(
                    items = (if (selectedItem.id == DisplayTab.SAVED) savedBeers else beers),
                    key = { beer -> beer.id }
                ) { beer ->
                    BeerCard(beer, saveBeer, deleteBeer)
                }
            }
        })
}
