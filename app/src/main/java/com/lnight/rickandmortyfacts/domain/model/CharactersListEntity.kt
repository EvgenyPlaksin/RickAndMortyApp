package com.lnight.rickandmortyfacts.domain.model

import com.lnight.rickandmortyfacts.data.data_source.remote.dto.Info

data class CharactersListEntity(
    val pageInfo: Info,
    val charactersData: List<CharactersData>
)
