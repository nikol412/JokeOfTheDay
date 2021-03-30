package com.nikol412.jokeoftheday.api

import com.google.gson.annotations.Expose

data class JokeResponse(
    @Expose
    val id: Int,
    @Expose
    val punchline: String,
    @Expose
    val setup: String,
    @Expose
    val type: String
)
