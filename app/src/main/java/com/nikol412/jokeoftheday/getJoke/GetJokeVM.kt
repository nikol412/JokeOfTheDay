package com.nikol412.jokeoftheday.getJoke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GetJokeVM: ViewModel() {
    val text  = MutableLiveData("Joke day")
}