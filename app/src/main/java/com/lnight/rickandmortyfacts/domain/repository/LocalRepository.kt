package com.lnight.rickandmortyfacts.domain.repository

import com.lnight.rickandmortyfacts.domain.model.CharactersData
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getCharacters(): Flow<List<CharactersData>>

    suspend fun getCharactersById(id: Int): CharactersData?

    suspend fun insertCharacter(characterData: CharactersData)

}