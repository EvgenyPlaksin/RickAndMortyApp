package com.lnight.rickandmortyfacts.domain.repository

import com.lnight.rickandmortyfacts.data.data_source.remote.dto.CharactersListResponseDto
import com.lnight.rickandmortyfacts.data.data_source.remote.dto.Result
interface ApiRepository {

    suspend fun getCharactersList(page: Int): CharactersListResponseDto

    suspend fun getCharacterById(id: Int): Result

}