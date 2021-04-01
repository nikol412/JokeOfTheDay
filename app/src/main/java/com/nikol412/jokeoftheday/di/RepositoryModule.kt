package com.nikol412.jokeoftheday.di

import com.nikol412.jokeoftheday.api.repository.jokeApiRepository.JokeApiRepository
import com.nikol412.jokeoftheday.api.repository.jokeApiRepository.JokeApiRepositoryImpl
import com.nikol412.jokeoftheday.db.repository.JokesOfficialRepository
import com.nikol412.jokeoftheday.db.repository.JokesOfficialRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<JokeApiRepository> { JokeApiRepositoryImpl(get(named("officialJokeApi"))) }

    single<JokesOfficialRepository> { JokesOfficialRepositoryImpl() }
}