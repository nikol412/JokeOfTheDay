package com.nikol412.jokeoftheday

import android.app.Application
import com.nikol412.jokeoftheday.di.dataModule
import com.nikol412.jokeoftheday.di.networkModule
import com.nikol412.jokeoftheday.di.repositoryModule
import com.nikol412.jokeoftheday.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JokeOfTheDayApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@JokeOfTheDayApp)
            modules(
                listOf(
                    dataModule,
                    networkModule,
                    viewModelModule,
                    repositoryModule
                )
            )
        }
    }
}