package com.example.composeapp


data class Beer(
    val id:Int,
    val name: String,
    val tagline: String,
    val description: String,
    var isFavorite: Boolean = false,
    val image_url: String,
)

