package com.example.android_6ringo

import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.helpers.toQueryParams
import com.example.android_6ringo.http.HttpClient
import com.example.android_6ringo.models.PagingOptions
import com.example.android_6ringo.models.PagingResult


class GameService(var _httpClient: HttpClient) {
    var _url = "https://6ringo.com/6ringo-api/micro-game/games"

    suspend fun paginate(options: PagingOptions = PagingOptions(orderBy = "createAt")): PagingResult<Game> {
        val url = "$_url/paginate"
        val result = _httpClient.get(url, options.toQueryParams()).bodyAs<PagingResult<Game>>()

        return result!!
    }

}