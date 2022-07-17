package com.lnight.rickandmortyfacts.domain.use_case.character_detail

import com.lnight.rickandmortyfacts.domain.model.DetailCharacterData
import com.lnight.rickandmortyfacts.domain.model.mappers.toDetailCharacterData
import com.lnight.rickandmortyfacts.domain.repository.Repository
import com.lnight.rickandmortyfacts.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(
    private val repository: Repository
) {

     operator fun invoke(id: Int): Flow<Resource<DetailCharacterData>> = flow {
        try {
            emit(Resource.Loading<DetailCharacterData>())
            val data = repository.getCharacterById(id)
            emit(Resource.Success<DetailCharacterData>(data.toDetailCharacterData()))
        } catch (e: HttpException) {
            emit(
                Resource.Error<DetailCharacterData>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<DetailCharacterData>("Couldn't reach server, check your internet connection"))
        }
    }
}