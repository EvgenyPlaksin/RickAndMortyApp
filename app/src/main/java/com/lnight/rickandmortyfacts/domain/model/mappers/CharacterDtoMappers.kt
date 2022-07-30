package com.lnight.rickandmortyfacts.domain.model.mappers

import com.lnight.rickandmortyfacts.data.data_source.remote.dto.CharactersListResponseDto
import com.lnight.rickandmortyfacts.data.data_source.remote.dto.Result
import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.model.CharactersListEntity
import com.lnight.rickandmortyfacts.domain.model.DetailCharacterData

fun CharactersListResponseDto.toCharactersListEntity(): CharactersListEntity {
    val charactersData = mutableListOf<CharactersData>()
    results.forEach { result ->
        charactersData.add(
            CharactersData(
                id = result.id,
                name = result.name,
                image = result.image
            )
        )
    }
    return CharactersListEntity(
        pageInfo = info,
        charactersData = charactersData
    )
}

fun Result.toDetailCharacterData(): DetailCharacterData {

    val status: Boolean? = when (status) {
        "Alive" -> true
        "Dead" -> false
        else -> null
    }

    return DetailCharacterData(
        id = id,
        name = name,
        image = image,
        gender = gender,
        cityName = location.name,
        status = status,
        species = species
    )
}