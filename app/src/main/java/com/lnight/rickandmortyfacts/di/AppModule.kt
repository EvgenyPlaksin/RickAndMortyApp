package com.lnight.rickandmortyfacts.di

import androidx.room.Room
import com.lnight.rickandmortyfacts.data.data_source.local.RickAndMortyDatabase
import com.lnight.rickandmortyfacts.data.data_source.remote.RickAndMortyApi
import com.lnight.rickandmortyfacts.data.repository.ApiRepositoryImpl
import com.lnight.rickandmortyfacts.data.repository.LocalRepositoryImpl
import com.lnight.rickandmortyfacts.domain.repository.ApiRepository
import com.lnight.rickandmortyfacts.domain.repository.LocalRepository
import com.lnight.rickandmortyfacts.domain.use_case.add_character.AddCharacterUseCase
import com.lnight.rickandmortyfacts.domain.use_case.character_detail.CharacterDetailUseCase
import com.lnight.rickandmortyfacts.domain.use_case.characters_list.CharactersListUseCase
import com.lnight.rickandmortyfacts.domain.use_case.get_cashed_detail.GetCashedDetailUseCase
import com.lnight.rickandmortyfacts.domain.use_case.get_cashed_list.GetCashedListUseCase
import com.lnight.rickandmortyfacts.domain.use_case.get_location.GetLocationUseCase
import com.lnight.rickandmortyfacts.presentation.charackter_detail.CharacterDetailViewModel
import com.lnight.rickandmortyfacts.presentation.characters_list.CharactersListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://rickandmortyapi.com/api/"

val appModule = module {

    single<RickAndMortyApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            RickAndMortyDatabase::class.java,
            RickAndMortyDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build().dao
    }

    single<ApiRepository> {
        ApiRepositoryImpl(get())
    }

    single<LocalRepository> {
        LocalRepositoryImpl(get())
    }

    factory {
        AddCharacterUseCase(get())
    }

    factory {
        CharacterDetailUseCase(get())
    }

    factory {
        CharactersListUseCase(get())
    }


    factory {
        GetCashedDetailUseCase(get())
    }

    factory {
        GetCashedListUseCase(get())
    }

    factory {
        GetLocationUseCase(get())
    }

    viewModel {
        CharactersListViewModel(
            get(),
            get(),
            get()
        )
    }

    viewModel {
        CharacterDetailViewModel(
            get(),
            get(),
            get()
        )
    }
}