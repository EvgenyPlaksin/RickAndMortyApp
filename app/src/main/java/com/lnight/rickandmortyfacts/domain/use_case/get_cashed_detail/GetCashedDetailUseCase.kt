package com.lnight.rickandmortyfacts.domain.use_case.get_cashed_detail

import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.repository.LocalRepository

class GetCashedDetailUseCase (
    private val localRepository: LocalRepository
){

    suspend operator fun invoke(id: Int): CharactersData? {
        return localRepository.getCharactersById(id)
    }

}