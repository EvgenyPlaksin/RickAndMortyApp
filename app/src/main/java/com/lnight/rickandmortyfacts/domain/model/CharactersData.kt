package com.lnight.rickandmortyfacts.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharactersData(
    @PrimaryKey
    val id: Int = -1,
    val name: String,
    val image: String
)
