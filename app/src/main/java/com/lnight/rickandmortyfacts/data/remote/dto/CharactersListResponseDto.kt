package com.lnight.rickandmortyfacts.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CharactersListResponseDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)
