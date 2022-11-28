package com.example.composeapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composeapp.db.Constants.BEER_TABLE

@Entity(tableName = BEER_TABLE)
data class BeerEntity (
    @PrimaryKey(autoGenerate = false)
    val beerId :Int,

    @ColumnInfo(name = "beer_title")
    val beerTitle:String,

    @ColumnInfo(name = "beer_desc")
    val beerDesc : String
)