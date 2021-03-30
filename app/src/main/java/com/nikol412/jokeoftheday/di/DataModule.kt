package com.nikol412.jokeoftheday.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val dataModule = module {
    single<Gson> {
        GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .enableComplexMapKeySerialization()
            .create()
    }
}