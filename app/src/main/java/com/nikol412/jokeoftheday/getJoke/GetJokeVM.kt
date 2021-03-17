package com.nikol412.jokeoftheday.getJoke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikol412.jokeoftheday.api.JokeRepository
import com.nikol412.jokeoftheday.api.JokeResponse
import com.nikol412.jokeoftheday.commons.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class GetJokeVM : ViewModel() {
    val repository = JokeRepository()

//    val jokes = mutableListOf<JokeResponse?>()

    val isLoading = MutableLiveData(false)
    val jokeResponse = MutableLiveData<JokeResponse>()
//    val onNeedUpdateAdapter = SingleLiveEvent<Any>()

    init {
        fetchJoke()
    }

    fun fetchJoke() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getJoke()
            isLoading.value = false

            jokeResponse.value = result ?: return@launch
//            onNeedUpdateAdapter.call()
        }
    }
}