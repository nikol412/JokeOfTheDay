package com.nikol412.jokeoftheday.di

import com.nikol412.jokeoftheday.ui.favourites.FavouritesJokesViewModel
import com.nikol412.jokeoftheday.ui.getJoke.GetJokeVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GetJokeVM(get(), get()) }
    viewModel { FavouritesJokesViewModel() }
}