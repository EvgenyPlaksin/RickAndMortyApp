package com.lnight.rickandmortyfacts.domain.use_case.characters_list

import com.lnight.rickandmortyfacts.domain.model.CharactersListEntity
import com.lnight.rickandmortyfacts.domain.model.mappers.toCharactersListEntity
import com.lnight.rickandmortyfacts.domain.repository.ApiRepository
import com.lnight.rickandmortyfacts.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CharactersListUseCase (
    private val apiRepository: ApiRepository
) {

     operator fun invoke(page: Int): Flow<Resource<CharactersListEntity>> = flow {
        try {
            emit(Resource.Loading<CharactersListEntity>())
            val data = apiRepository.getCharactersList(page)
            emit(Resource.Success<CharactersListEntity>(data.toCharactersListEntity()))
        } catch (e: HttpException) {
            emit(
                Resource.Error<CharactersListEntity>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<CharactersListEntity>("Couldn't reach server, check your internet connection"))
        }
    }

}