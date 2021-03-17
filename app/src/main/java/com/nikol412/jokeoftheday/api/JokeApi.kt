package com.nikol412.jokeoftheday.api

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface JokeApi {
    @GET("random_joke")
    suspend fun getRandomJoke(): Response<JokeResponse>
}