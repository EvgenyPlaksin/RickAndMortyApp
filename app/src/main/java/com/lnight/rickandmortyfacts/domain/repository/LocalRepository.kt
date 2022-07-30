package com.lnight.rickandmortyfacts.domain.repository

import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.model.DetailCharacterData
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getCharacters(): Flow<List<CharactersData>>

//    suspend fun getCharactersById(id: Int): DetailCharacterData?

    suspend fun insertCharacter(characterData: CharactersData)

}