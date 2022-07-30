package com.lnight.rickandmortyfacts.di

import android.app.Application
import androidx.room.Room
import com.lnight.rickandmortyfacts.data.data_source.local.RickAndMortyDao
import com.lnight.rickandmortyfacts.data.data_source.local.RickAndMortyDatabase
import com.lnight.rickandmortyfacts.data.data_source.remote.RickAndMortyApi
import com.lnight.rickandmortyfacts.data.repository.LocalRepositoryImpl
import com.lnight.rickandmortyfacts.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyDao(app: Application): RickAndMortyDao {
        return Room.databaseBuilder(
            app,
            RickAndMortyDatabase::class.java,
            RickAndMortyDatabase.DATABASE_NAME
        ).build().dao
    }

}