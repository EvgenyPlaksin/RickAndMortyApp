package com.lnight.rickandmortyfacts.domain.model.mappers

import com.lnight.rickandmortyfacts.data.remote.dto.CharactersListResponseDto
import com.lnight.rickandmortyfacts.data.remote.dto.Result
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
    return DetailCharacterData(
        id = id,
        name = name,
        image = image,
        gender = gender,
        cityName = location.name
    )
}