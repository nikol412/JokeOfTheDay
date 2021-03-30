package com.nikol412.jokeoftheday.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

open class BaseViewModel: ViewModel(), KoinComponent {
    val isLoading = MutableLiveData(false)
}