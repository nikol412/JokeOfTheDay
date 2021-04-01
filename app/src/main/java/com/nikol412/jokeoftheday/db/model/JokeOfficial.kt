package com.nikol412.jokeoftheday.db.model

import com.nikol412.jokeoftheday.api.JokeResponse
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeOfficial: RealmObject {
    @PrimaryKey
    var id: Int = 0

    var setup: String = ""
    var punchLine: String = ""
    var type: String = ""

    var isStarred: Boolean = false
    constructor(): super()

    constructor(id: Int, setup: String, punch: String, type: String, isStarred: Boolean): super() {
        this.id = id
        this.setup = setup
        this.punchLine = punch
        this.type = type
        this.isStarred = isStarred
    }

    constructor(jokeResponse: JokeResponse, isStarred: Boolean): super() {
        this.id = jokeResponse.id
        this.setup = jokeResponse.setup
        this.punchLine = jokeResponse.punchline
        this.type = jokeResponse.type
        this.isStarred = isStarred
    }

    override fun equals(other: Any?): Boolean {
        if(this !== other) return false

        if(this.isStarred != other.isStarred) return false
        if(this.id != other.id) return false
        if(this.punchLine != other.punchLine) return false
        if(this.setup != other.setup) return false
        if(this.type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 31
        result += this.isStarred.hashCode()
        result += this.punchLine.hashCode()
        result += this.setup.hashCode()
        result += this.type.hashCode()
        result += this.id.hashCode()

        return result
    }
}