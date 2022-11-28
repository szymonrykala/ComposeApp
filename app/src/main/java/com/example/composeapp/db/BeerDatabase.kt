package com.example.composeapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BeerEntity::class], version = 1)
abstract class BeerDatabase : RoomDatabase(){
    abstract  fun doa():BeerDao
}