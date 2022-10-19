package com.example.composeapp

// TODO API  ->>  https://api.punkapi.com/v2/beers


var beersCollection = listOf<Beer>(
    Beer(
        id = 1,
        title = "Buzz 1",
        subtitle = "A Real Bitter Experience.",
        description = "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
        isFavorite = true
    ),
    Beer(
        id = 2,
        title = "Trashy 2 Blonde",
        subtitle = "You Know You Shouldn't",
        description = "A titillating, neurotic, peroxide punk of a Pale Ale. Combining attitude, style, substance, and a little bit " +
                "of low self esteem for good measure; what would your mother say? The seductive lure of the sassy passion fruit hop proves " +
                "too much to resist. All that is even before we get onto the fact that there are no additives, preservatives, pasteurization" +
                " or strings attached. All wrapped up with the customary BrewDog bite and imaginative twist.",
        isFavorite = false,
    ),
    Beer(
        id = 3,
        title = "Berliner 3 Weisse With Yuzu - B-Sides",
        subtitle = "Japanese Citrus Berliner Weisse.",
        description = "Japanese citrus fruit intensifies the sour nature of this German classic.",
        isFavorite = false,
    ),
    Beer(
        id = 4,
        title = "Favourite 4 Buzz",
        subtitle = "A Real Bitter Experience.",
        description = "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
        isFavorite = true
    ),
    Beer(
        id = 5,
        title = "Trashy 5 Blonde",
        subtitle = "You Know You Should drink Your favourite beer",
        description = "A titillating, neurotic, peroxide punk of a Pale Ale. Combining attitude, style, substance, and a little bit" +
                " of low self esteem for good measure; what would your mother say? The seductive lure of the sassy passion fruit hop proves " +
                " or strings attached. All wrapped up with the customary BrewDog bite and imaginative twist.",
        isFavorite = true
    ),
    Beer(
        id = 6,
        title = "Favourite 6 Weisse With Yuzu - B-Sides",
        subtitle = "Japanese Citrus Berliner Weisse.",
        description = "Japanese citrus fruit intensifies the sour nature of this German classic.",
        isFavorite = true
    ),
)


class BeerController {

    public fun getAllBeers(): List<Beer> {

//        for (beer in beersCollection){
//            if (isFavorite(beer)){
//                beer.isFavorite = true
//            }
//        }
        return beersCollection
    }

    private fun isFavorite(beer: Beer): Boolean {
//        TODO: check if beer from the net is marked as our favorite beer i.e. by id
//        get an array of favorites beers ids and check if given beer's id is in this array

        val favouritesBeersIds = listOf<Int>(1, 3, 5, 7, 9)

        return favouritesBeersIds.contains(beer.id)
    }

    //    TODO You may consider creating an Favorites controller or so
    public fun toggleFavorite(beer: Beer) {
//       TODO: adding to favorites - saving in ROOM or in firebase/firestore
        if (isFavorite(beer)) {
            removeFromFavorites(beer)
        } else {
            addToFavorites(beer)
        }
//        return nothing, if something is wrong, raise a proper exception
    }

    private fun addToFavorites(beer: Beer) {
        beer.isFavorite = true
    }

    private fun removeFromFavorites(beer: Beer) {
        beer.isFavorite = false
    }

    public fun getFavoritesBears(): List<Beer> {
//        normally need to get this offline from memory or so
        return beersCollection.filter { beer -> isFavorite(beer) }
    }
}