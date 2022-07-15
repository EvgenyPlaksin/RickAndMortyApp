package com.lnight.rickandmortyfacts.presentation.characters_list

import com.lnight.rickandmortyfacts.domain.model.CharactersListEntity

data class CharactersListState(
    val isLoading: Boolean = false,
    val charactersListEntity: CharactersListEntity? = null,
    val error: String = ""
)