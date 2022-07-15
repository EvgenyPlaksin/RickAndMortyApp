package com.lnight.rickandmortyfacts.data.repository

import com.lnight.rickandmortyfacts.data.remote.RickAndMortyApi
import com.lnight.rickandmortyfacts.data.remote.dto.CharactersListResponseDto
import com.lnight.rickandmortyfacts.data.remote.dto.Result
import com.lnight.rickandmortyfacts.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : Repository {
    override suspend fun getCharactersList(page: Int): CharactersListResponseDto {
        return api.getCharactersList(page)
    }

    override suspend fun getCharacterById(id: Int): Result {
        return api.getCharacterById(id)
    }
}