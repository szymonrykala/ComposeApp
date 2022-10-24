package com.example.composeapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.composeapp.Beer


@Composable
@Preview
fun BeerCardPreview() {
    val b = Beer(
        id = 4,
        title = "Buzz zupa spoko",
        subtitle = "A Real Bitter Experience.",
        description = "A light, crisp and bitter IPA brewed with English and American hops. " + "A light, crisp and bitter IPA brewed with English and American hops" + "A small batch brewed only once.",
        isFavorite = false,
        imageUrl = "https://images.punkapi.com/v2/2.png"
    )
    BeerCard(beer = b)
}


@Composable
fun BeerCard(beer: Beer) {
    var isFavorite by remember { mutableStateOf<Boolean>(beer.isFavorite) }

    fun toggleFavorite() {
        // TODO trigger Favorites controller
        isFavorite = !isFavorite
    }

    Card(
        elevation = 15.dp, modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(
                    beer.title, fontSize = 32.sp,
                    modifier = Modifier.fillMaxWidth(0.9F)
                )

                BeerFavIcon(isFavorite, { toggleFavorite() })
            }

            Row {
                Image(
                    painter = rememberAsyncImagePainter(beer.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(180.dp)
                        .padding(8.dp)
                )
            }

            Row {
                Text(beer.subtitle, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(5.dp))

            Row {
                Text(beer.description)
            }
        }
    }
}

