package com.nikol412.jokeoftheday.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object JokeClient {
//    val logging by lazy {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        return@lazy interceptor
//    }
//
//    val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(logging)
//        .build()
//    val client = Retrofit.Builder()
//        .baseUrl("https://official-joke-api.appspot.com/")
//        .addConverterFactory(GsonConverterFactory.create())
////        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .client(okHttpClient)
//        .build()
//        .create(JokeApi::class.java)
//}