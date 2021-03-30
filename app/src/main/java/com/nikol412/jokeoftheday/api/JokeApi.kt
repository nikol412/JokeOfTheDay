package com.nikol412.jokeoftheday.api

import retrofit2.Response
import retrofit2.http.GET

interface JokeApi {
    @GET("random_joke")
    suspend fun getRandomJoke(): Response<JokeResponse>
}