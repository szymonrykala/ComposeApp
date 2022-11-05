package com.example.composeapp

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// TODO API  ->>  https://api.punkapi.com/v2/beers


val mapper = jacksonObjectMapper()

interface GitHubService {
    @GET("beers")
    fun getBeers(): Call<List<Beer?>?>?
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.punkapi.com/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var service = retrofit.create(GitHubService::class.java)



class BeerController {
    private val favoritesList = mutableListOf<Int>(1)

    public suspend fun getAllBeers(): List<Beer?>? {

        val response: Call<List<Beer?>?>? = service.getBeers()
        var respData = response?.awaitResponse()?.body()

        if(respData.isNullOrEmpty()){
            return emptyList<Beer>()
        }else{
            return respData.map{beer -> updateBeerFavProp(beer!!)}
        }
    }

    private fun updateBeerFavProp(beer:Beer):Beer{
        beer.isFavorite = isFavorite(beer.id)
        return beer
    }

    private fun isFavorite(id: Int): Boolean {
        return favoritesList.contains(id)
    }

    //    TODO You may consider creating an Favorites controller or so
    public fun toggleFavorite(beer: Beer) {
//       TODO: adding to favorites - saving in ROOM or in firebase/firestore
        if (isFavorite(beer.id)) {
            removeFromFavorites(beer)
        } else {
            addToFavorites(beer)
        }
    }

    private fun addToFavorites(beer: Beer) {
        favoritesList.add(beer.id)
        beer.isFavorite = true
    }

    private fun removeFromFavorites(beer: Beer) {
        favoritesList.remove(beer.id)
        beer.isFavorite = false
    }

    public suspend fun getFavoritesBears(): List<Beer> {
//        return getAllBeers().filter { beer -> isFavorite(beer.id) }
        return emptyList<Beer>()
    }

}
