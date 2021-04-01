package com.nikol412.jokeoftheday.api

import com.google.gson.annotations.Expose
import com.nikol412.jokeoftheday.db.model.JokeOfficial

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

fun JokeResponse.toJokeOfficial(): JokeOfficial {
    return JokeOfficial(id= this.id,
        setup = this.setup,
        punch = this.punchline,
        type = this.type,
        isStarred = false
    )
}