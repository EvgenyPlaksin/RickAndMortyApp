package com.lnight.rickandmortyfacts.domain.use_case.character_detail

import com.lnight.rickandmortyfacts.domain.model.mappers.toCharacterData
import com.lnight.rickandmortyfacts.domain.repository.ApiRepository
import com.lnight.rickandmortyfacts.common.Resource
import com.lnight.rickandmortyfacts.domain.model.CharactersData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CharacterDetailUseCase (
    private val apiRepository: ApiRepository
) {

     operator fun invoke(id: Int): Flow<Resource<CharactersData>> = flow {
        try {
            emit(Resource.Loading<CharactersData>())
            val data = apiRepository.getCharacterById(id)
            emit(Resource.Success<CharactersData>(data.toCharacterData()))
        } catch (e: HttpException) {
            emit(
                Resource.Error<CharactersData>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<CharactersData>("Couldn't reach server, check your internet connection"))
        }
    }
}