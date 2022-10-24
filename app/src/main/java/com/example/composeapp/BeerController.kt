package com.example.composeapp

// TODO API  ->>  https://api.punkapi.com/v2/beers

var beersCollection = listOf(
    mapOf<String, Any>(
        "id" to 1,
        "title" to "Buzz 1",
        "subtitle" to "A Real Bitter Experience.",
        "description" to "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
        "imageUrl" to "https://images.punkapi.com/v2/2.png"
    ),
    mapOf<String, Any>(
        "id" to 2,
        "title" to "Trashy 2 Blonde",
        "subtitle" to "You Know You Shouldn't",
        "description" to "A titillating, neurotic, peroxide punk of a Pale Ale. Combining attitude, style, substance, and a little bit " +
                "of low self esteem for good measure; what would your mother say? The seductive lure of the sassy passion fruit hop proves " +
                "too much to resist. All that is even before we get onto the fact that there are no additives, preservatives, pasteurization" +
                " or strings attached. All wrapped up with the customary BrewDog bite and imaginative twist.",
        "imageUrl" to "https://images.punkapi.com/v2/2.png"
    ),
    mapOf<String, Any>(
        "id" to 3,
        "title" to "Berliner 3 Weisse With Yuzu - B-Sides",
        "subtitle" to "Japanese Citrus Berliner Weisse.",
        "description" to "Japanese citrus fruit intensifies the sour nature of this German classic.",
        "imageUrl" to "https://images.punkapi.com/v2/2.png"
    ),
    mapOf<String, Any>(
        "id" to 4,
        "title" to "Favourite 4 Buzz",
        "subtitle" to "A Real Bitter Experience.",
        "description" to "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
        "imageUrl" to "https://images.punkapi.com/v2/2.png"
    ),
    mapOf<String, Any>(
        "id" to 5,
        "title" to "Trashy 5 Blonde",
        "subtitle" to "You Know You Should drink Your favourite beer",
        "description" to "A titillating, neurotic, peroxide punk of a Pale Ale. Combining attitude, style, substance, and a little bit" +
                " of low self esteem for good measure; what would your mother say? The seductive lure of the sassy passion fruit hop proves " +
                " or strings attached. All wrapped up with the customary BrewDog bite and imaginative twist.",
        "imageUrl" to "https://images.punkapi.com/v2/2.png"
    ),
    mapOf<String, Any>(
        "id" to 6,
        "title" to "Favourite 6 Weisse With Yuzu - B-Sides",
        "subtitle" to "Japanese Citrus Berliner Weisse.",
        "description" to "Japanese sdfsdfdsfsdret citrus fruit intensifies the sour nature of this German classic.",
        "imageUrl" to "https://images.punkapi.com/v2/2.png"
    ),
)


class BeerController {

    private val favoritesList = mutableListOf<Int>(1)

    private fun createBeer(data:Map<String,Any>):Beer{
        return Beer(
            data["id"] as Int,
            data["title"] as String,
            data["subtitle"] as String,
            data["description"] as String,
            isFavorite = isFavorite(data["id"] as Int),
            imageUrl = data["imageUrl"] as String
        )
    }

    public fun getAllBeers(): List<Beer> {
        return beersCollection.map { data->createBeer(data) }
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
//        return nothing, if something is wrong, raise a proper exception
    }

    private fun addToFavorites(beer: Beer) {
        favoritesList.add(beer.id)
        beer.isFavorite = true
    }

    private fun removeFromFavorites(beer: Beer) {
        favoritesList.remove(beer.id)
        beer.isFavorite = false
    }

    public fun getFavoritesBears(): List<Beer> {
//        normally need to get this offline - from memory

        return getAllBeers().filter { beer -> isFavorite(beer.id) }
    }
}