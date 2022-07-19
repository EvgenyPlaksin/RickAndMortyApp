package com.lnight.rickandmortyfacts.domain.model

data class DetailCharacterData(
   val id: Int,
   val name: String,
   val image: String,
   val gender: String,
   val cityName: String,
   val status: Boolean?,
   val species: String
)