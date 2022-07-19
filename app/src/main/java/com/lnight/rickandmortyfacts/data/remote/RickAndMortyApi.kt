package com.lnight.rickandmortyfacts.data.remote

import com.lnight.rickandmortyfacts.data.remote.dto.CharactersListResponseDto
import com.lnight.rickandmortyfacts.data.remote.dto.Result
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
}