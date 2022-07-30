package com.lnight.rickandmortyfacts.domain.use_case.get_cashed_list

import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCashedListUseCase @Inject constructor(
    private val localRepository: LocalRepository
){

    operator fun invoke(): Flow<List<CharactersData>> {
         return localRepository.getCharacters()
    }

}