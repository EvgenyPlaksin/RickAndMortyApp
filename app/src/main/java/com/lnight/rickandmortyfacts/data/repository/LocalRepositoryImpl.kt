package com.lnight.rickandmortyfacts.data.repository

import com.lnight.rickandmortyfacts.data.data_source.local.RickAndMortyDao
import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(
    private val dao: RickAndMortyDao
): LocalRepository {
    override fun getCharacters(): Flow<List<CharactersData>> {
        return dao.getAllCharacters()
    }

    override suspend fun getCharactersById(id: Int): CharactersData? {
        return dao.getCharacterById(id)
    }

    override suspend fun insertCharacter(characterData: CharactersData) {
        dao.insertCharacters(characterData)
    }
}