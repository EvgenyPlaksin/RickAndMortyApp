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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf<CharacterState>(CharacterState())
    val state: State<CharacterState> = _state

    init {
        val id = savedStateHandle.get<Int>("id")
        if(id == null || id == -1) {
            _state.value = CharacterState(error = "Unknown error occurred")
        } else {
            Log.e("TAG", "id -> $id")
            getCharacterData(id)
        }
    }

    private fun getCharacterData(id: Int) {
        characterDetailUseCase(id).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CharacterState(characterData = result.data)
                }
                is Resource.Error -> {
                    _state.value = CharacterState(error = result.message ?: "Unknown error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CharacterState(isLoading = true)
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