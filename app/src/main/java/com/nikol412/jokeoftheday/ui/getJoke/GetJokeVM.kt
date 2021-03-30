package com.nikol412.jokeoftheday.ui.getJoke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol412.jokeoftheday.api.repository.JokeApiRepositoryImpl
import com.nikol412.jokeoftheday.api.JokeResponse
import com.nikol412.jokeoftheday.api.repository.JokeApiRepository
import com.nikol412.jokeoftheday.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class GetJokeVM(private val jokeApiRepository: JokeApiRepository) : BaseViewModel() {


    val jokeResponse = MutableLiveData<JokeResponse>()

    init {
        fetchJoke()
    }

    fun fetchJoke() {
        viewModelScope.launch {
            isLoading.value = true
            val result = jokeApiRepository.getJoke()
            isLoading.value = false

            jokeResponse.value = result ?: return@launch
        }
    }

}