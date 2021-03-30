package com.nikol412.jokeoftheday.di

import com.nikol412.jokeoftheday.api.repository.JokeApiRepository
import com.nikol412.jokeoftheday.api.repository.JokeApiRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<JokeApiRepository> { JokeApiRepositoryImpl(get(named("officialJokeApi")))}
}