package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.composeapp.db.BeerDatabase
import com.example.composeapp.db.Constants.BEER_DATABASE
import com.example.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {

//    val beersViewModel: BeersViewModel by ViewModels()

    private val beerDB : BeerDatabase by lazy {
        Room.databaseBuilder(this,BeerDatabase::class.java,BEER_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private val beersView = BeersViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    App(
                        this.beerDB
                    )
                }
            }
        }
    }

    private fun checkItem(){
        if(beerDB.doa().getAllBeers().isNotEmpty()){
            // something
        }
        else{
            // something else
        }
    }
}



