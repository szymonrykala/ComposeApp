package com.example.composeapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.composeapp.Beer
import com.example.composeapp.BeersAPI
import com.example.composeapp.DisplayTab



fun getBeers(tabType: DisplayTab): List<Beer> {
    return if (tabType == DisplayTab.SEARCH) {
        BeersAPI.getAllBeers()
    } else {
        BeersAPI.getFavoritesBears()
    }
}


@Composable
fun BeersTab(tabType: DisplayTab) {
    var beers by remember { mutableStateOf<List<Beer>>(emptyList()) }

    beers = getBeers(tabType)

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
