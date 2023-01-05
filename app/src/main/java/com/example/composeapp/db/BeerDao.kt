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

    @Query("SELECT * FROM $BEER_TABLE ORDER BY id DESC")
    fun getAllBeers() : MutableList<BeerEntity>

    @Query("SELECT id FROM $BEER_TABLE ORDER BY id DESC")
    fun getAllBeersIds() : MutableList<Int>

    @Query("SELECT * FROM $BEER_TABLE WHERE id LIKE :beerId")
    fun getBeer(beerId : Int) : BeerEntity

}