package com.example.composeapp.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BeerFavIcon(
    isFavorite: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick() }) {
        if (isFavorite) {
            Icon(
                Icons.Filled.Star,
                contentDescription = "add to favourites",
                tint = Color.Red
            )
        } else {
            Icon(
                Icons.Outlined.Add,
                contentDescription = "remove from favourites",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}