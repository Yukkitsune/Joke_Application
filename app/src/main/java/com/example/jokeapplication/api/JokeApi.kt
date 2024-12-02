package com.example.jokeapplication.api

import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {
    @GET("joke/Any?")
    suspend fun getRandomJoke(
        @Query("blacklistFlags") blackListFlags: String = "nsfw,religious,political,racist,sexist,explicit",
        @Query("type") type: String = "twopart",
        @Query("amount") amount: Int = 10
    ): JokeResponse
}