package com.lnight.rickandmortyfacts.domain.model.mappers

import com.lnight.rickandmortyfacts.data.data_source.remote.dto.CharactersListResponseDto
import com.lnight.rickandmortyfacts.data.data_source.remote.dto.Result
import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.model.CharactersListEntity

fun CharactersListResponseDto.toCharactersListEntity(): CharactersListEntity {
    val charactersData = mutableListOf<CharactersData>()
    results.forEach { result ->
        val status: Boolean? = when (result.status) {
            "Alive" -> true
            "Dead" -> false
            else -> null
        }
        charactersData.add(
            CharactersData(
                id = result.id,
                name = result.name,
                image = result.image,
                gender = result.gender,
                cityName = result.location.name,
                species = result.species,
                status = status
            )
        )
    }
    return CharactersListEntity(
        pageInfo = info,
        charactersData = charactersData
    )
}

fun Result.toCharacterData(): CharactersData {

    val status: Boolean? = when (status) {
        "Alive" -> true
        "Dead" -> false
        else -> null
    }

    return CharactersData(
        id = id,
        name = name,
        image = image,
        gender = gender,
        cityName = location.name,
        status = status,
        species = species
    )
}