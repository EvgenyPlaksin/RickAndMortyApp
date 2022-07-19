package com.lnight.rickandmortyfacts.presentation.charackter_detail

import androidx.compose.ui.graphics.Color
import com.lnight.rickandmortyfacts.domain.model.DetailCharacterData

data class CharacterState(
    val isLoading: Boolean = false,
    val characterData: DetailCharacterData? = null,
    val error: String = ""
)