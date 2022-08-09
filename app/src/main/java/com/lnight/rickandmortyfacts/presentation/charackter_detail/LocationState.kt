package com.lnight.rickandmortyfacts.presentation.charackter_detail

import com.lnight.rickandmortyfacts.domain.model.LocationData

data class LocationState(
    val isLoading: Boolean = false,
    val error: String = "",
    val locationData: LocationData? = null
)
