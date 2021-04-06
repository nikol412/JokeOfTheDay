package com.nikol412.jokeoftheday.ui.getJoke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikol412.jokeoftheday.api.JokeResponse
import com.nikol412.jokeoftheday.api.repository.jokeApiRepository.JokeApiRepository
import com.nikol412.jokeoftheday.api.toJokeOfficial
import com.nikol412.jokeoftheday.db.model.JokeOfficial
import com.nikol412.jokeoftheday.db.repository.JokesOfficialRepository
import com.nikol412.jokeoftheday.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetJokeVM(
    private val jokeApiRepository: JokeApiRepository,
    private val jokesOfficialRepository: JokesOfficialRepository
) : BaseViewModel() {


    val jokeResponse = MutableLiveData<JokeResponse>()
    val jokesList = MutableStateFlow(listOf<JokeOfficial>())

    init {
        fetchLocalJokes()
    }

    fun fetchJoke() {
        viewModelScope.launch {
            isLoading.value = true
            val result = jokeApiRepository.getJoke()?.toJokeOfficial()
            isLoading.value = false

            val previousList = jokesList.value.toMutableList()
            result?.let {
                previousList.add(it)
                jokesList.emit(previousList)
            }
        }
    }

    fun addJokeToFavourite(position: Int) {
        jokesList.value.getOrNull(position)?.let { joke ->
            val mutableList = jokesList.value.toMutableList()
            mutableList.removeAt(position)
            jokesList.value = mutableList
            jokesOfficialRepository.addToFavourites(joke)
        }

    }


    private fun fetchLocalJokes() {
        jokesOfficialRepository.findRecentJokes().addChangeListener { data ->
            jokesList.value = data
        }
    }

    override fun onCleared() {
        jokesOfficialRepository.saveJokes(jokesList.value)
//        jokesOfficialRepository.close()

        super.onCleared()
    }

}