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
import com.lnight.rickandmortyfacts.domain.model.CharactersData
import com.lnight.rickandmortyfacts.domain.use_case.character_detail.CharacterDetailUseCase
import com.lnight.rickandmortyfacts.domain.use_case.characters_list.CharactersListUseCase
import com.lnight.rickandmortyfacts.domain.use_case.get_cashed_detail.GetCashedDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val getCashedDetailUseCase: GetCashedDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<CharacterState>(CharacterState())
    val state: State<CharacterState> = _state

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
                }
                is Resource.Error -> {
                  viewModelScope.launch {
                      val data = getCashedDetailUseCase(id)
                      if(data == null) {
                          _state.value = CharacterState(error = result.message ?: "Unknown error occurred")
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

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

}