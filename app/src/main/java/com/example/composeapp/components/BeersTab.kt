package com.example.composeapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composeapp.BeerController

@Composable
fun BeersTab() {
    var beers = BeerController()

    val scrollState = rememberScrollState()

    Box {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            beers.getAllBeers().forEach { beer ->
                BeerCard(beer = beer)
            }
        }
    }
}
