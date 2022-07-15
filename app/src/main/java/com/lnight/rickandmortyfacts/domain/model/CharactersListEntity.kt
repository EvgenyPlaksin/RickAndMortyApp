package com.lnight.rickandmortyfacts.domain.model

import com.lnight.rickandmortyfacts.data.remote.dto.Info

data class CharactersListEntity(
    val pageInfo: Info,
    val charactersData: List<CharactersData>
)
