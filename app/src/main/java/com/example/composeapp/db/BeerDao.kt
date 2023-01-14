package com.example.composeapp.db

import androidx.room.*
import com.example.composeapp.db.Constants.BEER_TABLE

@Dao
interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(beerEntity: BeerModel)

    @Update
    fun updateBeer(beerEntity: BeerModel)

    @Delete
    fun deleteBeer(beerEntity: BeerModel)

    @Query("SELECT * FROM $BEER_TABLE ORDER BY id ASC")
    fun getAllBeers(): MutableList<BeerModel>

    @Query("SELECT id FROM $BEER_TABLE ORDER BY id ASC")
    fun getAllBeersIds(): MutableList<Int>

    @Query("SELECT * FROM $BEER_TABLE WHERE id LIKE :beerId")
    fun getBeer(beerId: Int): BeerModel

}