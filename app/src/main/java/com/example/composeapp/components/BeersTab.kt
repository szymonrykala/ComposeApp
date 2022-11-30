package com.example.composeapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.composeapp.BeersView
import com.example.composeapp.DisplayTab


@Composable
fun BeersTab(tabType: DisplayTab) {
    val beers by BeersView.beers.collectAsState()

    Box {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(
                items = (if (tabType == DisplayTab.FAVORITES) BeersView.getFavorites() else beers),
                key = { beer -> beer.id }
            ) { beer ->
                BeerCard(beer)
            }
        }
    }
}
