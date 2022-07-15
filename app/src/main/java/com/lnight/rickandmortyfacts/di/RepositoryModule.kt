package com.lnight.rickandmortyfacts.di

import com.lnight.rickandmortyfacts.data.repository.RepositoryImpl
import com.lnight.rickandmortyfacts.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

}