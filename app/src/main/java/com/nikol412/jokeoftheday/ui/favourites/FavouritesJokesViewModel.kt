package com.nikol412.jokeoftheday.ui.favourites

import androidx.lifecycle.ViewModel
import com.nikol412.jokeoftheday.api.JokeResponse
import com.nikol412.jokeoftheday.db.model.JokeOfficial
import com.nikol412.jokeoftheday.db.repository.JokesOfficialRepository
import com.nikol412.jokeoftheday.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll

class FavouritesJokesViewModel(private val jokesOfficialRepository: JokesOfficialRepository) : BaseViewModel() {
    private val mutableFavouritesJokesList = MutableStateFlow(listOf<JokeOfficial>())
    val favouritesJokesList: StateFlow<List<JokeOfficial>> = mutableFavouritesJokesList

    init {

    }

    fun fetchJokes() {
        jokesOfficialRepository.findStarred().addChangeListener { data ->
            mutableFavouritesJokesList.value = data.toList()
        }
    }
}