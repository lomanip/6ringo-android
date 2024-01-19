package com.example.android_6ringo.services

import com.example.android_6ringo.BuildConfig
import com.example.android_6ringo.auth.models.User
import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.entities.ShoppingCart
import com.example.android_6ringo.entities.ShoppingCartAddModel
import com.example.android_6ringo.entities.ShoppingCartAddResult
import com.example.android_6ringo.helpers.toQueryParams
import com.example.android_6ringo.http.HttpClient
import com.example.android_6ringo.models.PagingOptions
import com.example.android_6ringo.models.PagingResult

class CountModel {
    var count:Int = 0
}
class ListItem<T> {
    var items: List<T> = listOf()
}
class ShoppingCartService(private var _httpClient: HttpClient) {
    private val _url = "${BuildConfig.SERVER_URL}/micro-shopping/carts"

    suspend fun count(user: User): Int {
        return _httpClient.get("$_url/${user.id}/count").bodyAs<CountModel>()!!.count
    }

    suspend fun delete(user: User, game: Game): Unit {
        _httpClient.delete("$_url/${user.id}/items/${game.id}")
    }

    suspend fun deleteAll(user: User): Unit {
        _httpClient.delete("$_url/${user.id}")
    }

    suspend fun list(user: User): List<ShoppingCart> {
        val result = _httpClient.get("$_url/${user.id}").bodyAs<ListItem<ShoppingCart>>()!!
        return result.items
    }

    suspend fun add(user: User, game: Game): ShoppingCartAddResult {
        val model = ShoppingCartAddModel()
        model.gameId = game.getNonNullId()
        model.quantity = 1
        val result = _httpClient.post("$_url/${user.id}/items", model)
            .bodyAs<ShoppingCartAddResult>()!!

        return result

    }


    suspend fun paginate(category: String, pageOptions: PagingOptions = PagingOptions(orderBy = "createAt")
                         ): PagingResult<Game> {
        var params = pageOptions.toQueryParams()
        if(category.isNotBlank()) {
            params = params.plus(Pair("category", category))
        }
        val url = "$_url/paginate"
        val result = _httpClient.get(url, params).bodyAs<PagingResult<Game>>()

        return result!!
    }

    suspend fun get(id: String): Game {
        val url = "$_url/$id"
        val result = _httpClient.get(url).bodyAs<Game>()
        return result!!
    }

}