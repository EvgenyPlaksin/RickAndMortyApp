package com.lnight.rickandmortyfacts.domain.use_case.get_location

import com.lnight.rickandmortyfacts.common.Resource
import com.lnight.rickandmortyfacts.domain.model.LocationData
import com.lnight.rickandmortyfacts.domain.model.mappers.toLocationData
import com.lnight.rickandmortyfacts.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetLocationUseCase (
    private val apiRepository: ApiRepository
) {

    operator fun invoke(id: Int): Flow<Resource<LocationData>> = flow {
        try {
            emit(Resource.Loading<LocationData>())
            val data = apiRepository.getLocationById(id)
            emit(Resource.Success<LocationData>(data.toLocationData()))
        } catch (e: HttpException) {
            emit(
                Resource.Error<LocationData>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<LocationData>("Couldn't reach server, check your internet connection"))
        }
    }
}