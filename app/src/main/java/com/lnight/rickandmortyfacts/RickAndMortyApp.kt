package com.lnight.rickandmortyfacts

import android.app.Application
import com.lnight.rickandmortyfacts.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickAndMortyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RickAndMortyApp)
            modules(appModule)
        }
    }
}