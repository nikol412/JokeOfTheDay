package com.nikol412.jokeoftheday.api.repository

import com.nikol412.jokeoftheday.api.JokeApi
import com.nikol412.jokeoftheday.api.JokeResponse
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext

class JokeApiRepositoryImpl(private val api: JokeApi): JokeApiRepository {
//    private val api = JokeClient.client

    override suspend fun getJoke(): JokeResponse? =
        withContext(Dispatchers.IO) {
            api.getRandomJoke().body()
        }
}