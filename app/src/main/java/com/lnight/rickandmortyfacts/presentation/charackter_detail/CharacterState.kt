package com.lnight.rickandmortyfacts.presentation.charackter_detail

import com.lnight.rickandmortyfacts.domain.model.CharactersData

data class CharacterState(
    val isLoading: Boolean = false,
    val characterData: CharactersData? = null,
    val error: String = ""
)