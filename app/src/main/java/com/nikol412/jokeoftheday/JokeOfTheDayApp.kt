package com.nikol412.jokeoftheday

import android.app.Application
import com.nikol412.jokeoftheday.di.dataModule
import com.nikol412.jokeoftheday.di.networkModule
import com.nikol412.jokeoftheday.di.repositoryModule
import com.nikol412.jokeoftheday.di.viewModelModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.security.SecureRandom

class JokeOfTheDayApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        Realm.init(this)
        initRealm()
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

    private fun initRealm() {
//        val key = ByteArray(64)
//        SecureRandom().nextBytes(key)
        val config = RealmConfiguration.Builder()
            .name("jokeOfTheDay.realm")
//            .encryptionKey(key)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
    }
}