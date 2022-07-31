package com.lnight.rickandmortyfacts.domain.use_case.get_cashed_detail

import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCashedDetailUseCase @Inject constructor(
    private val localRepository: LocalRepository
){

    suspend operator fun invoke(id: Int): CharactersData? {
        return localRepository.getCharactersById(id)
    }

}