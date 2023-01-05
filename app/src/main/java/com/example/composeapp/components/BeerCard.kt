package com.example.composeapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.composeapp.db.BeerModel


@Composable
fun BeerCard(
    beer: BeerModel, onSave: (beer: BeerModel) -> Unit, onDelete: (beer: BeerModel) -> Unit
) {
    var isFavorite by remember { mutableStateOf<Boolean>(beer.isSaved) }

    fun toggleFavorite() {
        if (beer.isSaved) {
            beer.isSaved = false
            isFavorite = false
            onDelete(beer)
        } else {
            beer.isSaved = true
            isFavorite = true
            onSave(beer)
        }
    }

    Card(
        elevation = 15.dp, modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(
                    beer.name, fontSize = 32.sp, modifier = Modifier.fillMaxWidth(0.9F)
                )
                BeerFavIcon(isFavorite) { toggleFavorite() }
            }

            Row {
                Image(
                    painter = rememberAsyncImagePainter(beer.image_url),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(180.dp)
                        .padding(8.dp)
                )
            }

            Row {
                Text(beer.tagline, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Text(beer.description)
            }
        }
    }
}

