package com.example.composeapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.Beer

@Composable
@Preview
fun BeerCardPreview() {
    val b = Beer(
        id = 4,
        title = "Buzz zupa spoko",
        subtitle = "A Real Bitter Experience.",
        description = "A light, crisp and bitter IPA brewed with English and American hops. " + "A light, crisp and bitter IPA brewed with English and American hops" + "A small batch brewed only once.",
        isFavorite = false
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

    BeerCardView(beer = beer, onClickFavorite = { toggleFavorite() })
}

@Composable
fun BeerCardView(
    beer: Beer, onClickFavorite: () -> Unit
) {
    Card(elevation = 15.dp, modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Column(modifier = Modifier.fillMaxWidth(0.9F)) {
                    Text(
                        beer.title, fontSize = 32.sp
                    )
                }
                Column {
                    IconButton(onClick = { onClickFavorite() }) {
                        Icon(
                            (if (beer.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Add),
                            contentDescription = "add to favourites",
//                            TODO change icon color
                        )
                    }
                }
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
