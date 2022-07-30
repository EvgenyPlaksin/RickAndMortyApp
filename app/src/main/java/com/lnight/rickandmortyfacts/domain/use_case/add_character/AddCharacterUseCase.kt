package com.lnight.rickandmortyfacts.domain.use_case.add_character

import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.repository.LocalRepository
import javax.inject.Inject

class AddCharacterUseCase @Inject constructor(
    private val localRepository: LocalRepository
){

    suspend operator fun invoke(charactersData: CharactersData) {
        localRepository.insertCharacter(charactersData)
    }

}