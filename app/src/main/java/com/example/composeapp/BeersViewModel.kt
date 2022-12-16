package com.example.composeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.db.BeerEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface IBeersService {
    @GET("beers")
    fun getBeers(): Call<List<BeerEntity?>?>?
}

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.punkapi.com/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var BeersService: IBeersService = retrofit.create(IBeersService::class.java)


class BeersViewModel : ViewModel() {
    private var db = null

    private var _beers = MutableStateFlow(emptyList<BeerEntity>())
    val beers = _beers.asStateFlow()

    private var _favList = MutableStateFlow(emptyList<Int>())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch {
            loadBeersFromWeb()
        }
    }

    private suspend fun loadBeersFromWeb() {
        val response: Call<List<BeerEntity?>?>? = BeersService.getBeers()
        var respData = response?.awaitResponse()?.body()

        if (respData.isNullOrEmpty()) {
            emptyList<BeerEntity>()
        } else {
            respData.forEach { it?.isFavorite = favList.value.contains(it?.id) }
            _beers.value = (respData as List<BeerEntity>?)!!
        }
    }

    public fun setFavList(favIds:List<Int>) {
        this._favList.value = favIds
    }

    public fun addFavorite(beer: BeerEntity) {
        _favList.value = _favList.value.plus(beer.id)
        _beers.value.find { it.id == beer.id }?.isFavorite = true
    }

    public fun removeFavorite(beer: BeerEntity) {
        _favList.value = _favList.value.filter { it != beer.id }
        _beers.value.find { it.id == beer.id }?.isFavorite = false
    }

    public fun getFavorites(): List<BeerEntity> {
        return _beers.value.filter { it.isFavorite }
    }
}
