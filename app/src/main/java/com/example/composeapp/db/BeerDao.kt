package com.example.composeapp.db

import androidx.room.*
import com.example.composeapp.db.Constants.BEER_TABLE

@Dao
interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(beerEntity: BeerEntity)

    @Update
    fun updateBeer(beerEntity: BeerEntity)

    @Delete
    fun deleteBeer(beerEntity: BeerEntity)

    @Query("SELECT * FROM $BEER_TABLE ORDER BY beerId DESC")
    fun getAllBeers() : MutableList<BeerEntity>

    @Query("SELECT * FROM $BEER_TABLE WHERE beerId LIKE :id")
    fun getBeer(id : Int) : BeerEntity

}