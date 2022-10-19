package com.example.composeapp


data class Beer(
    val id:Int,
    val title: String,
    val subtitle: String,
    val description: String,
    var isFavorite: Boolean,
)