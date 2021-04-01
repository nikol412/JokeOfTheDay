package com.nikol412.jokeoftheday.db.repository

import com.nikol412.jokeoftheday.db.model.JokeOfficial
import io.realm.Realm
import io.realm.RealmResults

class JokesOfficialRepositoryImpl : JokesOfficialRepository {
    private var realm: Realm = Realm.getDefaultInstance()

    override fun findAllJokes(): RealmResults<JokeOfficial> {
        return realm.where(JokeOfficial::class.java)
            .findAllAsync()
    }

    override fun findStarred(): RealmResults<JokeOfficial> {
        return realm.where(JokeOfficial::class.java)
            .equalTo(JokeOfficial::isStarred.name, true)
            .findAllAsync()
    }

    override fun saveJokes(jokes: List<JokeOfficial>) {
        realm.executeTransaction { realm ->
            realm.copyToRealmOrUpdate(jokes)
        }
    }

    override fun close() {
        realm.close()
    }
}