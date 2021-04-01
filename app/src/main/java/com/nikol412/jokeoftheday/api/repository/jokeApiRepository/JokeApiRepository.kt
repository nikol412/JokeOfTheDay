package com.nikol412.jokeoftheday.api.repository.jokeApiRepository

import com.nikol412.jokeoftheday.api.JokeResponse

interface JokeApiRepository {
    suspend fun getJoke(): JokeResponse?
}