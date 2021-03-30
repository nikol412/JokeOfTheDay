package com.nikol412.jokeoftheday.ui.favourites

import androidx.lifecycle.ViewModel
import com.nikol412.jokeoftheday.api.JokeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavouritesJokesViewModel : ViewModel() {
    private val mutableFavouritesJokesList = MutableStateFlow(listOf<JokeResponse>())
    val favouritesJokesList: StateFlow<List<JokeResponse>> = mutableFavouritesJokesList

    init {

    }

    fun fetchJokes() {
        val temp = (1 until 11).map { JokeResponse(it, "$it".repeat(it), "$it".repeat(it), "$it") }
        mutableFavouritesJokesList.value = temp
    }
}