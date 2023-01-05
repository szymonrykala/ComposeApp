package com.example.composeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.db.BeerModel
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
    fun getBeers(): Call<List<BeerModel?>?>?
}

var retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.punkapi.com/v2/")
    .addConverterFactory(GsonConverterFactory.create()).build()

var BeersService: IBeersService = retrofit.create(IBeersService::class.java)


class BeersViewModel : ViewModel() {
    private var _beers = MutableStateFlow(emptyList<BeerModel>())
    val beers = _beers.asStateFlow()

    private var _savedList = MutableStateFlow(emptyList<Int>())
    private val savedList = _savedList.asStateFlow()

    init {
        viewModelScope.launch {
            loadBeersFromWeb()
        }
    }

    private suspend fun loadBeersFromWeb() {
        try {
            val response: Call<List<BeerModel?>?>? = BeersService.getBeers()
            var respData = response?.awaitResponse()?.body()

            if (respData.isNullOrEmpty()) {
                emptyList<BeerModel>()
            } else {
                respData.forEach { it?.isSaved = savedList.value.contains(it?.id) }
                _beers.value = (respData as List<BeerModel>?)!!
            }
        } catch (e: Exception) {
            _beers.value = emptyList<BeerModel>()
        }
    }

    public fun setSavedList(favIds: List<Int>) {
        this._savedList.value = favIds
    }

    public fun addToSavedList(beerId: Int) {
        _savedList.value = _savedList.value.plus(beerId)
        _beers.value.find { it.id == beerId }?.isSaved = true
    }

    public fun removeFromSavedList(beerId: Int) {
        _savedList.value = _savedList.value.filter { it != beerId }
        _beers.value.find { it.id == beerId }?.isSaved = false
    }
}
