package com.lnight.rickandmortyfacts.data.repository

import com.lnight.rickandmortyfacts.data.data_source.remote.RickAndMortyApi
import com.lnight.rickandmortyfacts.data.data_source.remote.dto.CharactersListResponseDto
import com.lnight.rickandmortyfacts.data.data_source.remote.dto.Result
import com.lnight.rickandmortyfacts.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : ApiRepository {
    override suspend fun getCharactersList(page: Int): CharactersListResponseDto {
        return api.getCharactersList(page)
    }

    override suspend fun getCharacterById(id: Int): Result {
        return api.getCharacterById(id)
    }
}