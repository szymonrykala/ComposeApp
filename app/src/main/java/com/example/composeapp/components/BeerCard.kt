package com.example.composeapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.composeapp.db.BeerEntity


//@Composable
//@Preview
//fun BeerCardPreview() {
//    val b = BeerEntity(
//        id = 4,
//        name = "Buzz zupa spoko",
//        tagline = "A Real Bitter Experience.",
//        description = "A light, crisp and bitter IPA brewed with English and American hops. " + "A light, crisp and bitter IPA brewed with English and American hops" + "A small batch brewed only once.",
//        isFavorite = false,
//        image_url = "https://images.punkapi.com/v2/2.png"
//    )
//    BeerCard(beer = b)
//}


@Composable
fun BeerCard(
    beer: BeerEntity,
    onSave: (beer: BeerEntity) -> Unit ,
    onDelete: (beer: BeerEntity) -> Unit
) {
    var isFavorite by remember { mutableStateOf<Boolean>(beer.isFavorite) }

    fun toggleFavorite() {
        if (beer.isFavorite) {
            beer.isFavorite = false
            isFavorite = false
            onDelete(beer)
        } else {
            beer.isFavorite = true
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
                    beer.name, fontSize = 32.sp,
                    modifier = Modifier.fillMaxWidth(0.9F)
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

