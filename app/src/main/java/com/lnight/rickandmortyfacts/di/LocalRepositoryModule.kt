package com.lnight.rickandmortyfacts.di

import com.lnight.rickandmortyfacts.data.repository.ApiRepositoryImpl
import com.lnight.rickandmortyfacts.data.repository.LocalRepositoryImpl
import com.lnight.rickandmortyfacts.domain.repository.ApiRepository
import com.lnight.rickandmortyfacts.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindApiRepository(
        repositoryImpl: LocalRepositoryImpl
    ): LocalRepository

}