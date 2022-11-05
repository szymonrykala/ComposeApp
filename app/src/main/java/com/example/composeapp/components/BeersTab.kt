package com.example.composeapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.composeapp.Beer
import com.example.composeapp.BeersAPI
import com.example.composeapp.DisplayTab
import kotlinx.coroutines.launch


suspend fun getBeers(tabType: DisplayTab): List<Beer?>? {
    return if (tabType == DisplayTab.SEARCH) {
        BeersAPI.getAllBeers()
    } else {
        BeersAPI.getFavoritesBears()
    }
}


@Composable
fun BeersTab(tabType: DisplayTab, scaffoldState: ScaffoldState = rememberScaffoldState()) {
    var beers by remember { mutableStateOf<List<Beer>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(scaffoldState.snackbarHostState) {
        coroutineScope.launch {
            beers = getBeers(tabType) as List<Beer>
        }

        scaffoldState.snackbarHostState.showSnackbar(
            message = "Error message",
            actionLabel = "Retry message"
        )
    }

    val scrollState = rememberScrollState()

    Box {
        Column(
            modifier = Modifier.verticalScroll(scrollState).fillMaxHeight()
        ) {
            beers.forEach { beer ->
                BeerCard(beer = beer)
            }
        }
    }
}
