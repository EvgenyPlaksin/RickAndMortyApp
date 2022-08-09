package com.lnight.rickandmortyfacts.data.data_source.remote

import com.lnight.rickandmortyfacts.data.data_source.remote.dto.CharactersListResponseDto
import com.lnight.rickandmortyfacts.data.data_source.remote.dto.LocationResponseDto
import com.lnight.rickandmortyfacts.data.data_source.remote.dto.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharactersList(
        @Query("page") page: Int
    ): CharactersListResponseDto

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Result

    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") id: Int
    ): LocationResponseDto
}