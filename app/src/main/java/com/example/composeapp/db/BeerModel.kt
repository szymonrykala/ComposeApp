package com.example.composeapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composeapp.db.Constants.BEER_TABLE

@Entity(tableName = BEER_TABLE)
data class BeerModel(
    @PrimaryKey(autoGenerate = false) val id: Int,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "tagline") val tagline: String,

    @ColumnInfo(name = "isSaved") var isSaved: Boolean = false,

    @ColumnInfo(name = "image_url") val image_url: String,
)