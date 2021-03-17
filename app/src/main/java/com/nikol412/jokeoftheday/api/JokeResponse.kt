package com.nikol412.jokeoftheday.api

data class JokeResponse(
    val id: Int,
    val punchline: String,
    val setup: String,
    val type: String
)
