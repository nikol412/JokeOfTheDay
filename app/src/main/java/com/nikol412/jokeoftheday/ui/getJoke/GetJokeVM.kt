package com.nikol412.jokeoftheday.ui.getJoke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol412.jokeoftheday.api.JokeRepository
import com.nikol412.jokeoftheday.api.JokeResponse
import kotlinx.coroutines.launch

class GetJokeVM : ViewModel() {
    val repository = JokeRepository()

    val isLoading = MutableLiveData(false)
    val jokeResponse = MutableLiveData<JokeResponse>()

    init {
        fetchJoke()
    }

    fun fetchJoke() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getJoke()
            isLoading.value = false

            jokeResponse.value = result ?: return@launch
        }
    }

}