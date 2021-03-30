package com.nikol412.jokeoftheday.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.withContext

class JokeRepository {
    private val api = JokeClient.client

    suspend fun getJoke(): JokeResponse? =
        withContext(Dispatchers.IO) {
            api.getRandomJoke().body()
        }
}