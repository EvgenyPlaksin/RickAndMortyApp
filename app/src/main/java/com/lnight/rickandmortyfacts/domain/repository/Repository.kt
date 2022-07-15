package com.lnight.rickandmortyfacts.domain.repository

import com.lnight.rickandmortyfacts.data.remote.dto.CharactersListResponseDto
import com.lnight.rickandmortyfacts.data.remote.dto.Result
interface Repository {

    suspend fun getCharactersList(page: Int): CharactersListResponseDto

    suspend fun getCharacterById(id: Int): Result

}