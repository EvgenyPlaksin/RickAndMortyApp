package com.lnight.rickandmortyfacts.presentation.charackter_detail

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.lnight.rickandmortyfacts.common.Resource
import com.lnight.rickandmortyfacts.domain.use_case.character_detail.CharacterDetailUseCase
import com.lnight.rickandmortyfacts.domain.use_case.get_cashed_detail.GetCashedDetailUseCase
import com.lnight.rickandmortyfacts.domain.use_case.get_location.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val getCashedDetailUseCase: GetCashedDetailUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<CharacterState>(CharacterState())
    val state: State<CharacterState> = _state

    private val _locationState = mutableStateOf<LocationState>(LocationState())
    val locationState: State<LocationState> = _locationState

    init {
        getCharacterData()
    }

    fun getCharacterData() {
        val id = savedStateHandle.get<Int>("id")
        if (id == null || id == -1) {
            _state.value = CharacterState(error = "Unknown error occurred")
            return
        }
        characterDetailUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharacterState(characterData = result.data)
                    val locationCount = result.data?.locationUrl?.length.toString().last().digitToInt() - 1
                    val locationId = result.data?.locationUrl?.takeLast(locationCount)?.toInt()
                    Log.e("TAG", "locationCount-> $locationCount, locationId -> $locationId")
                    if (locationId != null) {
                        getLocationData(locationId)
                    }
                }
                is Resource.Error -> {
                    viewModelScope.launch {
                        val data = getCashedDetailUseCase(id)
                        if (data == null) {
                            _state.value =
                                CharacterState(error = result.message ?: "Unknown error occurred")
                        } else {
                            _state.value = CharacterState(characterData = data)
                        }
                    }
                }
                is Resource.Loading -> {
                    _state.value = CharacterState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getLocationData(id: Int) {
        getLocationUseCase(id).onEach {
            when (it) {
                is Resource.Success -> {
                    _locationState.value = locationState.value.copy(
                        isLoading = false,
                        locationData = it.data
                    )
                }
                is Resource.Error -> {
                    _locationState.value = locationState.value.copy(
                        isLoading = false,
                        error = it.message ?: "Unknown error occurred"
                    )
                }
                is Resource.Loading -> {
                    _locationState.value = locationState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

}