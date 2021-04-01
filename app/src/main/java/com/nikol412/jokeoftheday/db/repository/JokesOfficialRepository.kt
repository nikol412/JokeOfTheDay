package com.nikol412.jokeoftheday.db.repository

import com.nikol412.jokeoftheday.db.model.JokeOfficial
import io.realm.RealmResults

interface JokesOfficialRepository {

    fun findAllJokes(): RealmResults<JokeOfficial>

     fun findStarred(): RealmResults<JokeOfficial>

     fun saveJokes(jokes: List<JokeOfficial>)

     fun close()
}